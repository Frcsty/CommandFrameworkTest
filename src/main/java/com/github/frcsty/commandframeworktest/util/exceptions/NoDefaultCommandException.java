package com.github.frcsty.commandframeworktest.util.exceptions;

import org.jetbrains.annotations.NotNull;

public final class NoDefaultCommandException extends RuntimeException {
    public NoDefaultCommandException(@NotNull final String message) {
        super(message);
    }
}
