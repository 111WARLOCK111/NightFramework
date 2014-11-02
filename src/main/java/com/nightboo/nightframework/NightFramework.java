package com.nightboo.nightframework;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;

/**
 * Created by !!!WARLOCK!!! on 10/8/2014.
 */

// Works only at nights :D

public class NightFramework implements CommandExecutor {
    private NightPlugin plugin;

    public NightFramework(NightPlugin plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdlabel, String[] args)
    {
        for (NightScript script : plugin.Commands.values()) {
            if ((script.Name.equalsIgnoreCase(cmdlabel)) || (Util.contains(script.Aliases, cmdlabel))) {
                if (!script.ConsoleUsage && !(sender instanceof Player)) {
                    sender.sendMessage(ChatColor.RED + "You may not use this command from console!");
                    return true;
                }

                if (!sender.hasPermission("Transportation.command." + script.Permission)) {
                    sender.sendMessage(ChatColor.RED + "You don't have permission to \"" + script.Name + "\"!");
                    return true;
                }

                if (args == null && script.MinimumArgs > 0) {
                    return sendUsage(sender, script);
                }

                if (script.MinimumArgs > 0 && args.length < script.MinimumArgs) {
                    return sendUsage(sender, script);
                }

                if (script.MaximumArgs > 0 && args.length > script.MaximumArgs) {
                    return sendUsage(sender, script);
                }

                StringBuilder builder = new StringBuilder();

                for (String append : args) {
                    builder.append(append);
                }

                script.onCall(sender, args, builder.toString());
            }
        }
        return true;
    }

    public boolean sendUsage(CommandSender sender, NightScript script)
    {
        return sendUsage(sender, script.Name);
    }

    public boolean sendUsage(CommandSender sender, String command)
    {
        sender.sendMessage(ChatColor.RED + "Invalid arguments!");
        sender.sendMessage(ChatColor.GOLD + command + ": " + ChatColor.GREEN + plugin.getCommand(command).getUsage());
        return true;
    }
}
