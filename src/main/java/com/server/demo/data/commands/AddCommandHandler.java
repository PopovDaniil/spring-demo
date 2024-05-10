package com.server.demo.data.commands;

import java.util.Arrays;
import java.util.Map;

import org.springframework.jdbc.core.simple.JdbcClient;

import com.server.demo.data.commands.params.AddCommandParams;
import com.server.demo.data.common.CommandHandlerParametrized;
import com.server.demo.table.Table;

public class AddCommandHandler extends CommandHandlerParametrized<AddCommandParams, Integer> {

    public AddCommandHandler(JdbcClient db, Table tableEntity) {
        super(db, tableEntity);
    }

    @Override
    public Integer handle(AddCommandParams params) {
        String head = String.format("INSERT INTO %s (%s) VALUES ", tableEntity.getFullName(),
                tableEntity.getFieldsList());
        String[] values = new String[params.data.size()];
        Arrays.fill(values, "?");
        String valuesStr = String.join(", ", values);
        return this.db.sql(head + '(' + valuesStr + ')').params(params.data.values().toArray())
                .update();
    }

    @Override
    protected String getCommandName() {
        return "Create";
    }

    @Override
    public AddCommandParams paramsFromMap(Map<String, String> paramsMap) {
        return new AddCommandParams(paramsMap);
    }

    // private String paramToSql(String name, Object value) {
    // return this.db.
    // }

}
