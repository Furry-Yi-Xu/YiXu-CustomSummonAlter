package com.yixu.Command.MainCommand;

import com.yixu.Alter.AlterSession;
import com.yixu.Command.SubCommand.SubCommand;
import com.yixu.CustomSummonAlter;
import com.yixu.Util.Message.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
                return handleAlterCommand(sender, args);

            default:
                MessageUtil.sendMessage(sender, "commands.unknown-subcommand");
                return true;
        }
    }

    private boolean handleAlterCommand(CommandSender sender, String[] args) {

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

        if (subCommand1.equals("main") && subCommand2.equals("confirm")) {

            AlterSession alterSession = CustomSummonAlter.getAlterSession();

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

            AlterSession alterSession = CustomSummonAlter.getAlterSession();

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

        MessageUtil.sendMessage(sender, "commands.unknown-subcommand");
        return true;
    }
}
