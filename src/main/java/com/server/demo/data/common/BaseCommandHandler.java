package com.server.demo.data.common;

import org.springframework.jdbc.core.simple.JdbcClient;

import com.server.demo.table.Table;

public abstract class BaseCommandHandler<Params, Result> {
    protected abstract String getCommandName();

    protected final JdbcClient db;

    protected final Table tableEntity;

    public BaseCommandHandler(JdbcClient db, Table tableEntity) {
        this.db = db;
        this.tableEntity = tableEntity;
    }
}
