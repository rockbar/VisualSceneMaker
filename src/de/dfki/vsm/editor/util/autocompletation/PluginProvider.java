package de.dfki.vsm.editor.util.autocompletation;

import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PluginProvider {
    //Todo make close to register

    private static PluginProvider instance;
    private HashMap<String, ArrayList> pluginProviders;

    private PluginProvider(){
        pluginProviders = new HashMap<>();
    }

    public static PluginProvider getInstance(){
        if(instance == null){
            instance = new PluginProvider();
        }
        return instance;
    }

    public void registerProvider(String characterName, ArrayList<String> availableCompletions){
        pluginProviders.put(characterName, availableCompletions);
    }

    public void unregisterProvider(String characterName){
        if(pluginProviders.containsKey(characterName)){
            pluginProviders.remove(characterName);
        }
    }


    public static CompletionProvider getProvider(){
        return new PluginCompletionProvider(getInstance().pluginProviders);
    }


}
