/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dfki.vsm.xtension.stickman;

import de.dfki.action.sequence.TimeMark;
import de.dfki.action.sequence.Word;
import de.dfki.action.sequence.WordTimeMarkSequence;
import de.dfki.stickman.StickmanStage;
import de.dfki.stickman.animationlogic.Animation;
import de.dfki.stickman.animationlogic.AnimationLoader;
import de.dfki.util.xml.XMLUtilities;
import de.dfki.util.ios.IOSIndentWriter;
import de.dfki.vsm.model.config.ConfigFeature;
import de.dfki.vsm.model.project.PluginConfig;
import de.dfki.vsm.model.scenescript.ActionFeature;
import de.dfki.vsm.runtime.activity.AbstractActivity;
import de.dfki.vsm.runtime.activity.SpeechActivity;
import de.dfki.vsm.runtime.activity.executor.ActivityExecutor;
import de.dfki.vsm.runtime.activity.manager.ActivityManager;
import de.dfki.vsm.runtime.activity.manager.ActivityWorker;
import de.dfki.vsm.runtime.project.RunTimeProject;
import de.dfki.vsm.util.log.LOGConsoleLogger;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Patrick Gebhard
 */
public class StickmanExecutor extends ActivityExecutor {

    // The stickman stage window
    private static StickmanStage mStickmanStage;
    // The singelton logger instance
    private final LOGConsoleLogger mLogger = LOGConsoleLogger.getInstance();
    // The tworld listener
    private StickmanListener mListener;
    // The map of processes
    private final HashMap<String, Process> mProcessMap = new HashMap();
    // The client thread list
    private final HashMap<String, StickmanHandler> mClientMap = new HashMap();
    // The map of activity worker
    private final HashMap<String, ActivityWorker> mActivityWorkerMap = new HashMap();
    // The execution id
    private int sId = 0;

    // Construct the executor
    public StickmanExecutor(final PluginConfig config, final RunTimeProject project) {
        // Initialize the plugin
        super(config, project);
    }

    // Accept some socket
    public void accept(final Socket socket) {
        // Make new client thread 
        final StickmanHandler client = new StickmanHandler(socket, this);
        // Add the client to list
        // TODO: Get some reasonable name for references here!
        mClientMap.put(client.getName(), client);
        // Start the client thread
        client.start();
        //
        mLogger.warning("Accepting " + client.getName() + "");
    }

    @Override
    public final String marker(final long id) {
        // Stickman style bookmarks
        return "$" + id;
    }

    @Override
    public void execute(AbstractActivity activity, ActivityManager scheduler) {
        // get action information
        final String actor = activity.getActor();
        final String name = activity.getName();
        final LinkedList<ActionFeature> features = activity.getFeatureList();

        mLogger.message("Execute Actor " + actor + ", command " + name);

        Animation stickmanAnimation = new Animation();
        String animId = "";

        if (name.equalsIgnoreCase("Speak")) {
            if (activity instanceof SpeechActivity) {
                SpeechActivity sa = (SpeechActivity) activity;

                // create a new word time mark sequence based on the current utterance blocks
                WordTimeMarkSequence wts = new WordTimeMarkSequence(sa.getTextOnly("$"));

                LinkedList blocks = sa.getBlocks();
                for (final Object item : blocks) {
                    if (!item.toString().contains("$")) {
                        wts.add(new Word(item.toString()));
                    } else {
                        wts.add(new TimeMark(item.toString()));
                    }
                }

                stickmanAnimation = AnimationLoader.getInstance().loadEventAnimation(mStickmanStage.getStickman(actor), "Speaking", 3000, true);
                stickmanAnimation.mParameter = wts;
                animId = stickmanAnimation.mID;
            }
        }

        // send command to platform 
        synchronized (mActivityWorkerMap) {
            // send command to platform 
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            IOSIndentWriter iosw = new IOSIndentWriter(out);
            boolean r = XMLUtilities.writeToXMLWriter(stickmanAnimation, iosw);

            try {
                broadcast(new String(out.toByteArray(), "UTF-8").replace("\n", " "));
            } catch (UnsupportedEncodingException exc) {
                mLogger.warning(exc.getMessage());
            }

            // organize wait for feedback
            ActivityWorker cAW = (ActivityWorker) Thread.currentThread();
            mActivityWorkerMap.put(animId, cAW);

            // wait until we got feedback
            mLogger.warning("ActivityWorker " + animId + " waiting ....");

            while (mActivityWorkerMap.containsValue(cAW)) {
                try {
                    mActivityWorkerMap.wait();
                } catch (InterruptedException exc) {
                    mLogger.failure(exc.toString());
                }
            }

            mLogger.warning("ActivityWorker " + animId + "  done ....");
        }
        // Return when terminated
    }

    @Override
    public void launch() {
        // Create the connection
        mListener = new StickmanListener(8000, this);
        // Start the connection
        mListener.start();

        // Get the plugin configuration
        for (ConfigFeature cf : mConfig.getEntryList()) {
            mLogger.message("Stickman Plugin Config: " + cf.getKey() + " = " + cf.getValue());
        }

        final String host = mConfig.getProperty("smhost");
        final String port = mConfig.getProperty("smport");

        // Start the StickmanStage client application 
        mLogger.message("Starting StickmanStage Client Application ...");
        mStickmanStage = StickmanStage.getNetworkInstance(host, Integer.parseInt(port));

        // TODO - read config
        StickmanStage.addStickman("susanne");
        StickmanStage.addStickman("patrick");

        // wait for stickman stage 
        while (mClientMap.isEmpty()) {
            mLogger.message("Waiting for StickmanStage");
            try {
                Thread.sleep(1000);
            } catch (final InterruptedException exc) {
                mLogger.failure("Error while waiting ...");
            }
        }
    }

    @Override
    public void unload() {
        // clear the stage
        StickmanStage.clearStage();
        // Abort the client threads
        for (final StickmanHandler client : mClientMap.values()) {
            client.abort();
            // Join the client thread
            try {
                client.join();
            } catch (final Exception exc) {
                mLogger.failure(exc.toString());
                // Print some information 
                mLogger.message("Joining client thread");
            }
        }
        // Clear the map of clients 
        mClientMap.clear();
        // Abort the server thread
        try {
            mListener.abort();
            // Join the client thread
            mListener.join();
            // Print some information 
            mLogger.message("Joining server thread");
        } catch (final Exception exc) {
            mLogger.failure(exc.toString());
        }
    }

    // Handle some message
    public void handle(final String message, final StickmanHandler client) {
        mLogger.warning("Handling " + message + "");

        if (message.contains("#ANIM#end#")) {

            int start = message.lastIndexOf("#") + 1;
            String animId = message.substring(start);

            synchronized (mActivityWorkerMap) {
                if (mActivityWorkerMap.containsKey(animId)) {
                    mActivityWorkerMap.remove(animId);
                }
                mActivityWorkerMap.notifyAll();
            }
        }
    }

// Broadcast some message
    private void broadcast(final String message) {
        for (final StickmanHandler client : mClientMap.values()) {
            client.send(message);
        }
    }

}