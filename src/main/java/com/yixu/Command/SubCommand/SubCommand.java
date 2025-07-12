package com.yixu.Command.SubCommand;

import com.yixu.Alter.AlterSession;
import com.yixu.CustomSummonAlter;
import com.yixu.Util.Permission.PermissionCheck;
import com.yixu.Util.Permission.PermissionNodes;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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

        alterSession.clearMain_alter_Location();
        alterSession.setPlayerAlterStatus(player.getUniqueId(), "main_alter_setting");
    }

    public void runSub_AlterSetting() {

        if (!PermissionCheck.checkPlayerWithPermission(sender, PermissionNodes.SUB_ALTER_SETTING)) {
            return;
        }

        Player player = getPlayer();

        alterSession.clearSub_alter_Location();
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
}
