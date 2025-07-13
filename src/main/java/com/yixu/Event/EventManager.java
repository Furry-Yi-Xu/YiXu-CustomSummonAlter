package com.yixu.Event;

import com.yixu.Event.Vanilla.CustomBlockInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class EventManager {

    public static void init(Plugin plugin) {

        PluginManager pluginManager = plugin.getServer().getPluginManager();

        pluginManager.registerEvents(new CustomBlockInteractEvent(), plugin);

    }

}
