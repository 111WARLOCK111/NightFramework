package com.nightboo.nightframework;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;

/**
 * Created by !!!WARLOCK!!! on 10/10/2014.
 */

public class NightPlugin extends JavaPlugin {
    public HashMap<String, NightScript> Commands;
    private CommandExecutor             Executor;

    public String                       Name;

    public NightPlugin(String name)
    {
        super();

        Name = name;
    }

    @Override
    public void onEnable() {
        Long time = System.currentTimeMillis();

        log("V" + this.getDescription().getVersion(), Level.FINE);
        log("Initializing...");

        saveDefaultConfig();

        Commands = new HashMap<String, NightScript>();

        Executor = new NightFramework(this);

        onRegister();

        reloadConfig();

        log("Loading done. (" + (System.currentTimeMillis() - time) + "ms loadtime)");
    }

    public void onRegister()
    {
        // Add your registers here by overriding.
    }

    public void registerCommand(NightScript command)
    {
        Commands.put(command.Name, command);

        registerCommand(command.getClass().getSimpleName(), command.Aliases);
    }

    public void registerCommand(String name, String[] Aliases)
    {
        getCommand(name).setExecutor(Executor);

        if (Aliases != null) {
            getCommand(name).setAliases(Arrays.asList(Aliases));

            for (String alias : Aliases) {
                getCommand(alias).setExecutor(Executor);
            }
        }
    }

    public void log(String message)
    {
        log(message, Level.INFO);
    }

    public void log(String message, Level log)
    {
        getLogger().log(log, message);
    }
}
