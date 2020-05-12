package com.github.frcsty.commandframeworktest.commands.objects;

import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public abstract class ICommand {

    protected final Options      options = new Options();
    private final   String       command;
    private         List<String> permissions;
    private         String       usage;
    private         int          requiredArgs = 0;
    private         boolean      def = false;
    private         boolean      playerOnly = false;

    protected ICommand(final String command) {
        this.command = command;
    }

    public String getCommand() {
        return this.command;
    }

    public String getUsage() {
        return this.usage;
    }

    public List<String> getPermissions() {
        return this.permissions;
    }

    public int getRequiredArgs() {
        return this.requiredArgs;
    }

    public boolean isDefault() {
        return this.def;
    }

    public boolean isPlayerOnly() {
        return this.playerOnly;
    }

    public abstract boolean execute(final CommandSender sender, final String[] args);

    protected final class Options {

        public Options permissions(final String... values) {
            permissions = Arrays.asList(values);
            return this;
        }

        public Options usage(final String value) {
            usage = value;
            return this;
        }

        public Options def(final boolean value) {
            def = value;
            return this;
        }

        public Options playerOnly(final boolean value) {
            playerOnly = value;
            return this;
        }

        public Options requiredArgs(final int value) {
            requiredArgs = value;
            return this;
        }

    }

}

