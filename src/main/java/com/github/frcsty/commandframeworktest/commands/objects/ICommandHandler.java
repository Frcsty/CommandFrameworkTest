package com.github.frcsty.commandframeworktest.commands.objects;

import com.github.frcsty.commandframeworktest.commands.command.TestCommand;
import com.github.frcsty.commandframeworktest.commands.command.TestSubCommand;
import com.github.frcsty.commandframeworktest.util.Color;
import com.github.frcsty.commandframeworktest.util.exceptions.NoDefaultCommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ICommandHandler implements CommandExecutor
{

    private final Set<ICommand> commands;
    private final ICommand      defaultCommand;

    public ICommandHandler()
    {
        commands = Stream.of(new TestCommand(), new TestSubCommand()).collect(Collectors.toSet());
        defaultCommand = commands.stream()
                                 .filter(ICommand::isDefault)
                                 .findAny().orElseThrow(() -> new NoDefaultCommandException("There is no default command present in the plugin."));
    }

    @Override
    public boolean onCommand(@NotNull final CommandSender sender, @NotNull final org.bukkit.command.Command bukkitCommand, @NotNull final String label, @NotNull final String[] args)
    {
        if (args.length == 0)
        {
            defaultCommand.execute(sender, args);
            return true;
        }

        final Optional<ICommand> optionalCommand = commands.stream().filter(cmd -> cmd.getCommand().equalsIgnoreCase(args[0])).findAny();

        if (!optionalCommand.isPresent())
        {
            sender.sendMessage(Color.colorize("&cUnknown Command.")); //plugin.getConfig().getString("settings.unknown-command")));
            return true;
        }

        final ICommand command = optionalCommand.get();

        if (command.isPlayerOnly() && !(sender instanceof Player))
        {
            sender.sendMessage(Color.colorize("&cThis command can not be executed in console!")); //plugin.getConfig().getString("settings.player-only")));
            return true;
        }

        if (!command.getPermissions().isEmpty() && command.getPermissions().stream().noneMatch(sender::hasPermission))
        {
            sender.sendMessage(Color.colorize("&cYou do not have permission to execute this command Jimbo.")); // plugin.getConfig().getString("settings.no-permission")));
            return true;
        }

        if (args.length < command.getRequiredArgs() + 1)
        {
            sender.sendMessage(Color.colorize("&cInvalid Command Usage! Use /<command> " + args[0] + " " + command.getUsage())); //plugin.getConfig().getString("settings.command-usage").replace("{usage}", args[0] + " " + command.getUsage())));
            return true;
        }

        final boolean result = command.execute(sender, Arrays.copyOfRange(args, 1, args.length));

        if (!result)
        {
            sender.sendMessage(Color.colorize("&cInvalid Command Usage! Use /<command> " + args[0] + " " + command.getUsage())); //plugin.getConfig().getString("settings.command-usage").replace("{usage}", args[0] + " " + command.getUsage())));
        }

        return true;
    }

}
