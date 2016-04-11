package de.dfki.vsm.model.project;

import de.dfki.vsm.model.ModelObject;
import de.dfki.vsm.util.ios.IOSIndentWriter;
import de.dfki.vsm.util.log.LOGDefaultLogger;
import de.dfki.vsm.util.xml.XMLParseAction;
import de.dfki.vsm.util.xml.XMLParseError;
import de.dfki.vsm.util.xml.XMLUtilities;
import de.dfki.vsm.util.xml.XMLWriteError;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.w3c.dom.Element;

/**
 * @author Gregor Mehlmann
 */
public final class ProjectConfig implements ModelObject {

    // The singelton logger instance
    private final LOGDefaultLogger mLogger
            = LOGDefaultLogger.getInstance();
    // The name of the project  
    private String mProjectName;
    // The list of plugin configurations
    private final ArrayList<PluginConfig> mPluginList;
    // The list of device configurations
//    private final ArrayList<DeviceConfig> mDeviceList;
    // The list of agent configurations
    private final ArrayList<AgentConfig> mAgentList;

    // Construct an empty project
    public ProjectConfig() {
        // Initialize The Project Name
        mProjectName = new String();
        // Initialize The Plugin List
        mPluginList = new ArrayList<>();
        // Initialize The Player List
//        mDeviceList = new ArrayList<>();
        // Initialize The Agent List
        mAgentList = new ArrayList<>();
    }

    // Construct an empty project
    public ProjectConfig(final String name,
            final ArrayList<PluginConfig> plugins,
            //            final ArrayList<DeviceConfig> devices,
            final ArrayList<AgentConfig> agents) {
        // Initialize The Project Name
        mProjectName = name;
        // Initialize The Plugin List
        mPluginList = plugins;
        // Initialize The Player List
//        mDeviceList = devices;
        // Initialize The Agent List
        mAgentList = agents;
    }

    // Get the name of the project
    public final String getProjectName() {
        return mProjectName;
    }

    // Set the name of the project
    public final void setProjectName(final String name) {
        mProjectName = name;
    }

    public final AgentConfig getAgentConfig(final String name) {
        for (final AgentConfig config : mAgentList) {
            if (config.getAgentName().equals(name)) {
                return config;
            }
        }
        return null;
    }

    public final PluginConfig getPluginConfig(final String name) {
        for (final PluginConfig config : mPluginList) {
            if (config.getPluginName().equals(name)) {
                return config;
            }
        }
        return null;
    }

//    public final DeviceConfig getDeviceConfig(final String name) {
//        for (final DeviceConfig config : mDeviceList) {
//            if (config.getDeviceName().equals(name)) {
//                return config;
//            }
//        }
//        return null;
//    }
    // Get the list of agent configurations
    public final ArrayList<AgentConfig> getAgentConfigList() {
        return mAgentList;
    }

    // Get the list of agent names (added PG - 8.4.2016)
    public final ArrayList<String> getAgentNames() {
        ArrayList<String> agentNames = new ArrayList<>();
        for (AgentConfig ac : getAgentConfigList()) {
            agentNames.add(ac.getAgentName());
        }
        return agentNames;
    }

//    // Get the list of device configurations
//    public ArrayList<DeviceConfig> getDeviceConfigList() {
//        return mDeviceList;
//    }
    // Get the list of plugin configurations
    public ArrayList<PluginConfig> getPluginConfigList() {
        return mPluginList;
    }

    // Write the project configuration as XML
    @Override
    public final void writeXML(final IOSIndentWriter stream) throws XMLWriteError {
        stream.println("<Project name=\"" + mProjectName + "\">");
        stream.push();

        // Write The Plugins As XML
        stream.println("<Plugins>").push();
        for (final PluginConfig plugin : mPluginList) {
            plugin.writeXML(stream);
            //stream.endl();
        }
        stream.pop().println("</Plugins>");

//        // Write The Players As XML
//        stream.println("<Devices>").push();
//        for (final DeviceConfig player : mDeviceList) {
//            player.writeXML(stream);
//            //stream.endl();
//        }
//        stream.pop().println("</Devices>");
        // Write The Agents As XML
        stream.println("<Agents>").push();
        for (final AgentConfig agent : mAgentList) {
            agent.writeXML(stream);
            //stream.endl();
        }
        stream.pop().println("</Agents>");
        //
        stream.pop().print("</Project>").flush();
    }

    public void cleanPlayerList() {
//        mDeviceList.clear();
    }

    // Parse the project coonfiguration from XML
    @Override
    public final void parseXML(final Element element) throws XMLParseError {
        // Get The Type Of The Config
        final String tag = element.getTagName();
        // Check The Type Of The Config
        if (tag.equals("Project")) {
            // Get The Project Name
            mProjectName = element.getAttribute("name");
            // Parse The Individual Entries
            XMLParseAction.processChildNodes(element, new XMLParseAction() {
                @Override
                public void run(final Element element) throws XMLParseError {
                    // Get The Tag Name
                    final String tag = element.getTagName();
                    // Check The Tag Name
                    if (tag.equals("Plugins")) {
                        XMLParseAction.processChildNodes(element, "Plugin", new XMLParseAction() {
                            @Override
                            public void run(Element element) throws XMLParseError {
                                // Create A New Project Plugin
                                final PluginConfig plugin = new PluginConfig();
                                // And Parse The Project Plugin 
                                plugin.parseXML(element);
                                // And Add It To The Plugin List
                                mPluginList.add(plugin);
                            }
                        });
                    } //                    else if (tag.equals("Devices")) {
                    //                        XMLParseAction.processChildNodes(element, "Device", new XMLParseAction() {
                    //                            @Override
                    //                            public void run(Element element) throws XMLParseError {
                    //                                // Create A New Project Player
                    //                                final DeviceConfig device = new DeviceConfig();
                    //                                // And Parse The Project Player 
                    //                                device.parseXML(element);
                    //                                // And Add It To The Player List
                    //                                mDeviceList.add(device);
                    //                            }
                    //                        });
                    //                    } 
                    else if (tag.equals("Agents")) {
                        XMLParseAction.processChildNodes(element, "Agent", new XMLParseAction() {
                            @Override
                            public void run(Element element) throws XMLParseError {
                                // Create A New Project Player
                                final AgentConfig agent = new AgentConfig();
                                // And Parse The Project Player 
                                agent.parseXML(element);
                                // And Add It To The Player List
                                mAgentList.add(agent);
                            }
                        });
                    }
                }
            });
        }
    }

    // Get the string representation 
    @Override
    public final String toString() {
        // Create a new byte array stream buffer
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        // Try to write the project to the stream
        XMLUtilities.writeToXMLStream(this, buffer);
        // Return the stream string representation
        try {
            return buffer.toString("UTF-8");
        } catch (final UnsupportedEncodingException exc) {
            return exc.getMessage();
        }
    }

    // Get a copy of the project configuration
    @Override
    public ProjectConfig getCopy() {
        // TODO: Use copies of the lists
        return new ProjectConfig(mProjectName, null, null);
    }
}
