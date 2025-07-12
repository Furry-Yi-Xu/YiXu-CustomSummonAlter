package com.yixu.Event.CraftEngine;

import com.yixu.Alter.AlterSession;
import com.yixu.CustomSummonAlter;
import com.yixu.Util.Message.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CustomBlockInteractEvent implements Listener {

    @EventHandler
    public void onCustomBlockInteractEvent(net.momirealms.craftengine.bukkit.api.event.CustomBlockInteractEvent event) {

        Player player = event.getPlayer();
        Location location = event.location();
        AlterSession alterSession = CustomSummonAlter.getAlterSession();

        if (alterSession.getPlayerAlterStatus(player.getUniqueId()).equals("main_alter_setting")) {
            alterSession.setMain_alter_Location(player.getUniqueId(), location);
            MessageUtil.sendMessage(player, "main_alter.pointer_select_succeed");
            return;
        }

        if (alterSession.getPlayerAlterStatus(player.getUniqueId()).equals("sub_alter_setting")) {
            if (alterSession.addSub_alter_Location(player.getUniqueId(), location)) {
                MessageUtil.sendMessage(player, "sub_alter.pointer_select_exist");
                return;
            }
            MessageUtil.sendMessage(player, "sub_alter.pointer_select_succeed");
            return;
        }

    }

}
