package com.server.demo.data;

import java.util.Map;
import java.util.Optional;

import com.server.demo.data.commands.GetListCommandHandler;
import com.server.demo.data.common.BaseCommandHandler;

public class CommandsMap {
    public static final Map<String, Class<? extends BaseCommandHandler<?, ?>>> commands = Map.of(
            "GetList", GetListCommandHandler.class);

    public static Optional<Class<? extends BaseCommandHandler<?, ?>>> get(String commandName) {
        return Optional.ofNullable(commands.get(commandName));
    }
}
