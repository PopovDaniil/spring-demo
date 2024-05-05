package com.server.demo.data.errors;

import com.server.demo.common.errors.BaseResponseError;

public class CommandExecutingError extends BaseResponseError {

    public CommandExecutingError(String errorName) {
        super(generateTitle(errorName));
    }

    private static String generateTitle(String errorName) {
        return String.format("Error executing command: %s", errorName);
    }
}
