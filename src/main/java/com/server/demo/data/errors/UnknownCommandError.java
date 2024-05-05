package com.server.demo.data.errors;

import com.server.demo.common.errors.BaseResponseError;

public class UnknownCommandError extends BaseResponseError {

    public UnknownCommandError(String commandName) {
        super(generateTitle(commandName));
    }

    private static String generateTitle(String commandName) {
        return String.format("Unknown command name: %s", commandName);
    }
}
