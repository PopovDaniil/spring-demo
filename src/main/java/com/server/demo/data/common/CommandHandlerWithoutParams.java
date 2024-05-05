package com.server.demo.data.common;

import org.springframework.jdbc.core.simple.JdbcClient;

import com.server.demo.table.Table;

public abstract class CommandHandlerWithoutParams<Result> extends BaseCommandHandler<Void, Result> {
    public CommandHandlerWithoutParams(JdbcClient db, Table tableEntity) {
        super(db, tableEntity);
    }

    public abstract Result handle();
}
