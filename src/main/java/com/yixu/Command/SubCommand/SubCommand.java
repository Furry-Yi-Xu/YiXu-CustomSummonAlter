package com.yixu.Command.SubCommand;

import com.yixu.Alter.AlterSession;
import com.yixu.CustomSummonAlter;
import com.yixu.Util.File.ExportLocationToFile;
import com.yixu.Util.Permission.PermissionCheck;
import com.yixu.Util.Permission.PermissionNodes;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class SubCommand {

    private final CommandSender sender;
    private final AlterSession alterSession;

    public SubCommand(CommandSender sender) {
        this.sender = sender;
        this.alterSession = CustomSummonAlter.getAlterSession();
    }

    private Player getPlayer() {
        return (Player) sender;
    }

    public void runMain_AlterSetting() {

        if (!PermissionCheck.checkPlayerWithPermission(sender, PermissionNodes.MAIN_ALTER_SETTING)) {
            return;
        }

        Player player = getPlayer();

        alterSession.clearMainAlterLocations(player.getUniqueId());
        alterSession.setPlayerAlterStatus(player.getUniqueId(), "main_alter_setting");
    }

    public void runSub_AlterSetting() {

        if (!PermissionCheck.checkPlayerWithPermission(sender, PermissionNodes.SUB_ALTER_SETTING)) {
            return;
        }

        Player player = getPlayer();

        alterSession.clearSubAlterLocations(player.getUniqueId());
        alterSession.setPlayerAlterStatus(player.getUniqueId(), "sub_alter_setting");
    }

    public void runMain_AlterConfirm() {

        if (!PermissionCheck.checkPlayerWithPermission(sender, PermissionNodes.MAIN_ALTER_CONFIRM)) {
            return;
        }

        Player player = getPlayer();

        alterSession.setPlayerAlterStatus(player.getUniqueId(), "main_alter_confirm");
    }

    public void runSub_AlterConfirm() {

        if (!PermissionCheck.checkPlayerWithPermission(sender, PermissionNodes.SUB_ALTER_CONFIRM)) {
            return;
        }

        Player player = getPlayer();

        alterSession.setPlayerAlterStatus(player.getUniqueId(), "sub_alter_confirm");
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
