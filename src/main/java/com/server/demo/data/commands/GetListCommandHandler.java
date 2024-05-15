package com.server.demo.data.commands;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.JdbcClient;

import com.server.demo.data.common.CommandHandlerWithoutParams;
import com.server.demo.table.Table;

public class GetListCommandHandler extends CommandHandlerWithoutParams<List<Map<String, Object>>> {

    public GetListCommandHandler(JdbcClient db, Table tableEntity) {
        super(db, tableEntity);
    }

    @Override
    public List<Map<String, Object>> handle() {
        return this.db.sql(String.format("SELECT * FROM %s", tableEntity.getFullName())).query().listOfRows();
    }
}
