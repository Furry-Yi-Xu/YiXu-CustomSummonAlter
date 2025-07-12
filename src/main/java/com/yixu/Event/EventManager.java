package com.yixu.Event;

import com.yixu.Event.CraftEngine.CustomBlockInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class EventManager {

    public static void init(Plugin plugin) {

        PluginManager pluginManager = plugin.getServer().getPluginManager();

        if (pluginManager.getPlugin("CraftEngine") != null) {
            pluginManager.registerEvents(new CustomBlockInteractEvent(), plugin);
        }

    }

}
