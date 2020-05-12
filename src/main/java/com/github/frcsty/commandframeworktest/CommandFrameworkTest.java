package com.github.frcsty.commandframeworktest;

import com.github.frcsty.commandframeworktest.commands.objects.ICommandHandler;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class CommandFrameworkTest extends JavaPlugin
{

    @Override
    public void onEnable()
    {
        registerCommand("testcommand", new ICommandHandler());
    }

    @Override
    public void onDisable()
    {
        // Plugin shutdown logic
    }

    private void registerCommand(final String command, final CommandExecutor executor)
    {
        final PluginCommand c = getCommand(command);

        if (c != null)
        {
            c.setExecutor(executor);
        }
    }

}
