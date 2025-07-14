package com.yixu.Command.SubCommand;

import com.yixu.Alter.AlterSessionManager;
import com.yixu.CustomSummonAlter;
import com.yixu.Util.File.ExportLocationToFile;
import com.yixu.Util.Permission.PermissionCheck;
import com.yixu.Util.Permission.PermissionNodes;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class SubCommand {

    private final CommandSender sender;
    private final AlterSessionManager alterSessionManager;

    public SubCommand(CommandSender sender) {
        this.sender = sender;
        this.alterSessionManager = CustomSummonAlter.getAlterSession();
    }

    private Player getPlayer() {
        return (Player) sender;
    }

    public void runMain_AlterSetting() {

        if (!PermissionCheck.checkPlayerWithPermission(sender, PermissionNodes.MAIN_ALTER_SETTING)) {
            return;
        }

        Player player = getPlayer();

        alterSessionManager.clearMainAlterLocations(player.getUniqueId());
        alterSessionManager.setPlayerAlterStatus(player.getUniqueId(), "main_alter_setting");
    }

    public void runSub_AlterSetting() {

        if (!PermissionCheck.checkPlayerWithPermission(sender, PermissionNodes.SUB_ALTER_SETTING)) {
            return;
        }

        Player player = getPlayer();

        alterSessionManager.clearSubAlterLocations(player.getUniqueId());
        alterSessionManager.setPlayerAlterStatus(player.getUniqueId(), "sub_alter_setting");
    }

    public void runMain_AlterConfirm() {

        if (!PermissionCheck.checkPlayerWithPermission(sender, PermissionNodes.MAIN_ALTER_CONFIRM)) {
            return;
        }

        Player player = getPlayer();

        alterSessionManager.setPlayerAlterStatus(player.getUniqueId(), "main_alter_confirm");
    }

    public void runSub_AlterConfirm() {

        if (!PermissionCheck.checkPlayerWithPermission(sender, PermissionNodes.SUB_ALTER_CONFIRM)) {
            return;
        }

        Player player = getPlayer();

        alterSessionManager.setPlayerAlterStatus(player.getUniqueId(), "sub_alter_confirm");
    }

    public void runAll_AlterConfirm() throws IOException {

        if (!PermissionCheck.checkPlayerWithPermission(sender, PermissionNodes.ALL_ALTER_CONFIRM)) {
            return;
        }

        Player player = getPlayer();

        ExportLocationToFile exportLocationToFile = new ExportLocationToFile();
        exportLocationToFile.exportAlterLocationsToFile(player);

    }

}
