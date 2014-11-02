package com.nightboo.nightframework;

import com.nightboo.nightframework.NightScript;
import org.bukkit.entity.Player;

import java.util.logging.Level;

/**
 * Created by !!!WARLOCK!!! on 10/8/2014.
 */
public class Util {
    /** Find a player */
    public static Player findPlayer(NightScript script, String find)
    {
        script.plugin.log("THIS SUCKS");
        for (Player ply : script.plugin.getServer().getOnlinePlayers()) {
            if (ply.getName().equalsIgnoreCase(find)) {
                return ply;
            } else if (ply.getName().toLowerCase().contains(find.toLowerCase())) {
                return ply;
            }
        }
        script.plugin.log("INDEEEED");
        return null;
    }

    /** Checks whether the array contains findout string */
    public static boolean contains(String[] aliases, String find)
    {
        if (aliases == null || find == null) return false;

        for (String str : aliases) {
            if (str.equalsIgnoreCase(find))
                return true;
        }

        return false;
    }

    /** Checks whether a script has private checkout or not */
    public static boolean isPrivate(NightScript script)
    {
        return script.plugin.getConfig().getBoolean(script.plugin.Name + ".Print.Private." + script.Name);
    }

    /** Checks whether a script has public checkout or not */
    public static boolean isPublic(NightScript script)
    {
        return script.plugin.getConfig().getBoolean(script.plugin.Name + ".Print.Global." + script.Name);
    }

    /** Checks whether a script has op checkout or not */
    public static boolean isOp(NightScript script)
    {
        return script.plugin.getConfig().getBoolean(script.plugin.Name + ".Print.Op." + script.Name);
    }

    public static void sendPublic(NightScript script, String message)
    {
        if (isPublic(script)) {
            script.plugin.getServer().broadcastMessage(message);
        } else if (isOp(script)) {
            for (Player ply : script.plugin.getServer().getOnlinePlayers()) {
                if (ply.isOp()) {
                    ply.sendMessage(message);
                }
            }
        }

        script.plugin.getLogger().log(Level.INFO, message);
    }

    public static void sendPrivate(NightScript script, Player player, String message)
    {
        if (isPrivate(script)) {
            player.sendMessage(message);
        }

        script.plugin.getLogger().log(Level.INFO, "[PRIVATE to " + player.getName() + "] " + message);
    }
}
