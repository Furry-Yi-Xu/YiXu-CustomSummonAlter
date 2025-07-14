package com.yixu.Event.Vanilla;

import com.yixu.Alter.AlterSessionManager;
import com.yixu.CustomSummonAlter;
import com.yixu.Util.Message.MessageUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;

public class PlayerInteractEvent implements Listener {

    @EventHandler
    public void onPlayerInteractEvent(org.bukkit.event.player.PlayerInteractEvent event) {

        Action action = event.getAction();

        if (!action.isRightClick()) {
            return;
        }

        Block block = event.getClickedBlock();

        if (block == null || block.getType() == Material.AIR) {
            return;
        }

        Player player = event.getPlayer();
        Location location = event.getClickedBlock().getLocation();
        AlterSessionManager alterSessionManager = CustomSummonAlter.getAlterSession();

        if (alterSessionManager.getPlayerAlterStatus(player.getUniqueId()) == null) {
            return;
        }

        if (alterSessionManager.getPlayerAlterStatus(player.getUniqueId()).equals("main_alter_setting")) {
            alterSessionManager.addMainAlterLocation(player.getUniqueId(), location);
            MessageUtil.sendMessage(player, "main_alter.pointer_select_succeed");
            return;
        }

        if (alterSessionManager.getPlayerAlterStatus(player.getUniqueId()).equals("sub_alter_setting")) {
            if (alterSessionManager.addSubAlterLocation(player.getUniqueId(), location)) {
                MessageUtil.sendMessage(player, "sub_alter.pointer_select_exist");
                return;
            }
        }

    }

}
