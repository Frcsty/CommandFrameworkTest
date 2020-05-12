package com.github.frcsty.commandframeworktest.commands.command;

import com.github.frcsty.commandframeworktest.commands.objects.ICommand;
import com.github.frcsty.commandframeworktest.util.Color;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestSubCommand extends ICommand
{

    public TestSubCommand()
    {
        super("test sub");
        options.permissions("commands.test.sub").playerOnly(true);

    }

    @Override
    public boolean execute(final CommandSender sender, final String[] args)
    {
        final Player player = (Player) sender;

        player.sendMessage(Color.colorize("&aThis is a test sub command."));
        return true;
    }

}