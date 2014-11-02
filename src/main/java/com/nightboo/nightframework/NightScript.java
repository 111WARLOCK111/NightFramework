package com.nightboo.nightframework;

import com.nightboo.nightframework.NightPlugin;
import org.bukkit.command.CommandSender;

/**
 * Created by !!!WARLOCK!!! on 10/8/2014.
 */

public class NightScript {
    public NightScript(NightPlugin plugin) {
        Name              =        this.getClass().getSimpleName();
        Permission        =        Name;

        MinimumArgs       =        0;
        MaximumArgs       =        0;

        ConsoleUsage      =        false;

        this.plugin       =        plugin;
    }

    public void onCall(CommandSender player, String[] args, String full) { }

    public NightPlugin  plugin;

    public String       Name;
    public String       Permission;
    public String[]     Aliases;

    public Integer      MinimumArgs;
    public Integer      MaximumArgs;

    public Boolean      ConsoleUsage;
}
