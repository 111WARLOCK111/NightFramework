NightFramework
==============

Easy to code Bukkit Framework for plugin creating and command coding.

Examples
===

MyPlugin.java
```java
package com.test.myplugin;

import com.nightboo.nightframework.NightPlugin;


public class MyPlugin extends NightPlugin {
    public MyPlugin()
    {
        super("MyPlugin"); // Use this as name of your plugin.
    }

    @Override
    public void onRegister()
    {
        // Remember to use ONLY this function to register your commands/events.
        registerCommand(new TestCommand(this)); // Register your command like this
    }
}
```

TestCommand.java
```java
package com.test.testcommand;

import com.nightboo.nightframework.NightPlugin;
import com.nightboo.nightframework.NightScript;
import com.nightboo.nightframework.Util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Goto extends NightScript {
    public Goto(NightPlugin plugin)
    {
        super(plugin);

        MinimumArgs = 1;
        MaximumArgs = 1;
    }

    // sender: A player or Console/Anything that can send a command.
    // args: Arguements seperated by space.
    // full: All of arguements joint together in a string.
    @Override
    public void onCall(CommandSender sender, String[] args, String full)
    {
        Player ply = Util.findPlayer(this, args[0]);

        if (ply == null) {
            sender.sendMessage(ChatColor.RED + "Invalid player\"" + args[0] + "\"!");
            return;
        }

        if (ply == sender) {
            sender.sendMessage(ChatColor.RED + "You may not test yourself!");
            return;
        }

        ((Player) sender).teleport(ply);

        Util.sendPublic(this, ((Player) sender).getDisplayName() + " teleported to " + ply.getDisplayName());
        Util.sendPrivate(this, ply, ((Player) sender).getDisplayName() + " teleported to You");
    }
}
```
