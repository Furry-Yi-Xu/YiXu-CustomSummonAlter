package com.yixu;

import com.yixu.Alter.AlterSessionManager;
import com.yixu.Command.CommandManager;
import com.yixu.Command.MainCommand.MainTabCompleter;
import com.yixu.Config.ConfigManager;
import com.yixu.Event.EventManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileNotFoundException;

public final class CustomSummonAlter extends JavaPlugin {

    private static CustomSummonAlter instance;
    private static AlterSessionManager alterSessionManager = new AlterSessionManager();

    public CustomSummonAlter() {
        super();
    }

    @Override
    public void onEnable() {

        instance = this;

        AlterSessionManager alterSessionManager = new AlterSessionManager();

        try {
            ConfigManager.init(this);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        EventManager.init(this);

        getCommand("yixu-customsummonalter").setExecutor(new CommandManager());
        getCommand("yixu-customsummonalter").setTabCompleter(new MainTabCompleter());

        getLogger().info("YiXu-CustomSummonAlter 插件已加载！");

    }

    @Override
    public void onDisable() {

        getLogger().info("YiXu-CustomSummonAlter 插件已卸载！");

    }

    public static CustomSummonAlter getInstance() {
        return instance;
    }

    public static AlterSessionManager getAlterSession() {
        return alterSessionManager;
    }
}
