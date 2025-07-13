package com.yixu.Command.MainCommand;

import com.yixu.Alter.AlterSession;
import com.yixu.Command.SubCommand.SubCommand;
import com.yixu.CustomSummonAlter;
import com.yixu.Util.Message.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class MainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            MessageUtil.sendMessage(sender, "commands.usage");
            return true;
        }

        String subCommand = args[0].toLowerCase();

        switch (subCommand) {
            case "reload":
                // TODO: Add reload logic
                MessageUtil.sendMessage(sender, "commands.reload-complete");
                return true;

            case "alter":
                try {
                    return handleAlterCommand(sender, args);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            default:
                MessageUtil.sendMessage(sender, "commands.unknown-subcommand");
                return true;
        }
    }

    private boolean handleAlterCommand(CommandSender sender, String[] args) throws IOException {

        if (args.length < 3) {
            MessageUtil.sendMessage(sender, "commands.usage");
            return true;
        }

        Player player = (Player) sender;

        String subCommand1 = args[1].toLowerCase();
        String subCommand2 = args[2].toLowerCase();

        SubCommand subCommandTab = new SubCommand(sender);

        if (subCommand1.equals("main") && subCommand2.equals("setting")) {
            subCommandTab.runMain_AlterSetting();
            MessageUtil.sendMessage(sender, "main_alter.pointer_select_started");
            return true;
        }

        if (subCommand1.equals("sub") && subCommand2.equals("setting")) {
            subCommandTab.runSub_AlterSetting();
            MessageUtil.sendMessage(sender, "sub_alter.pointer_select_started");
            return true;
        }

        AlterSession alterSession = CustomSummonAlter.getAlterSession();

        if (subCommand1.equals("main") && subCommand2.equals("confirm")) {

            if (
                    alterSession.getPlayerAlterStatus(player.getUniqueId()) == null ||
                            !alterSession.getPlayerAlterStatus(player.getUniqueId()).equals("main_alter_setting")
            ) {
                MessageUtil.sendMessage(sender, "main_alter.pointer_confirm_error");
                return true;
            }

            subCommandTab.runMain_AlterConfirm();
            MessageUtil.sendMessage(sender, "main_alter.pointer_confirm_succeed");
            return true;
        }

        if (subCommand1.equals("sub") && subCommand2.equals("confirm")) {

            if (
                    alterSession.getPlayerAlterStatus(player.getUniqueId()) == null ||
                            !alterSession.getPlayerAlterStatus(player.getUniqueId()).equals("sub_alter_setting")
            ) {
                MessageUtil.sendMessage(sender, "sub_alter.pointer_confirm_error");
                return true;
            }

            subCommandTab.runSub_AlterConfirm();
            MessageUtil.sendMessage(sender, "sub_alter.pointer_confirm_succeed");
            return true;
        }

        if (subCommand1.equals("all") && subCommand2.equals("confirm")) {

            if (alterSession.getMainAlterLocation(player.getUniqueId()) == null) {
                MessageUtil.sendMessage(player, "all_alter.main_alter_output_error");
                return true;
            }

            if (alterSession.getSubAlterLocations(player.getUniqueId()) == null) {
                MessageUtil.sendMessage(player, "all_alter.sub_alter_output_error");
                return true;
            }

            subCommandTab.runAll_AlterConfirm();
            MessageUtil.sendMessage(sender, "sub_alter.all_alter_output_succeed");
            return true;

        }

        MessageUtil.sendMessage(sender, "commands.unknown-subcommand");
        return true;
    }
}
