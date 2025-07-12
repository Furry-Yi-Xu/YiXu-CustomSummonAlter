package com.yixu;

import com.yixu.Alter.AlterSession;
import com.yixu.Command.CommandManager;
import com.yixu.Command.MainCommand.MainTabCompleter;
import com.yixu.Config.ConfigManager;
import com.yixu.Event.EventManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileNotFoundException;

public final class CustomSummonAlter extends JavaPlugin {

    private static CustomSummonAlter instance;
    private static AlterSession alterSession = new AlterSession();

    public CustomSummonAlter() {
        super();
    }

    @Override
    public void onEnable() {

        instance = this;

        AlterSession alterSession = new AlterSession();

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

    public static AlterSession getAlterSession() {
        return alterSession;
    }
}
