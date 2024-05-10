package com.server.demo.data.common;

import java.util.Map;

import org.springframework.jdbc.core.simple.JdbcClient;

import com.server.demo.table.Table;

public abstract class CommandHandlerParametrized<Params extends CommandParams, Result>
        extends BaseCommandHandler<Params, Result> {
    public CommandHandlerParametrized(JdbcClient db, Table tableEntity) {
        super(db, tableEntity);
    }

    public abstract Result handle(Params params);

    public abstract Params paramsFromMap(Map<String, String> paramsMap);

    public Result handleAndTransformParams(Map<String, String> paramsMap) {
        Params transformed = this.paramsFromMap(paramsMap);
        return this.handle(transformed);
    }
}
