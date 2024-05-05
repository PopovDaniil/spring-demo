package com.server.demo.data.common;

import java.util.Map;

import org.springframework.jdbc.core.simple.JdbcClient;

import com.server.demo.table.Table;

public abstract class CommandHandlerParametrized<Result> extends BaseCommandHandler<Void, Result> {
    public CommandHandlerParametrized(JdbcClient db, Table tableEntity) {
        super(db, tableEntity);
    }

    public abstract Result handle(Map<String, String> params);
}
