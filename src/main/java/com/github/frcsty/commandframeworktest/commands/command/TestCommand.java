package com.github.frcsty.commandframeworktest.commands.command;

import com.github.frcsty.commandframeworktest.commands.objects.ICommand;
import com.github.frcsty.commandframeworktest.util.Color;
import org.bukkit.command.CommandSender;

public class TestCommand extends ICommand
{

    public TestCommand()
    {
        super("test");
        options.def(true).permissions("commands.test");

    }

    @Override
    public boolean execute(final CommandSender sender, final String[] args)
    {
        sender.sendMessage(Color.colorize("&aThis is a test command."));

        return true;
    }

}
