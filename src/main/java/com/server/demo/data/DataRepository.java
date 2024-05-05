package com.server.demo.data;

import java.lang.reflect.InvocationTargetException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import com.server.demo.data.common.*;
import com.server.demo.data.errors.*;
import com.server.demo.field.Field;
import com.server.demo.table.Table;

@Repository
public class DataRepository {
    JdbcClient client;

    DataRepository(JdbcClient client) {
        this.client = client;
    }

    public void createTable(Table tableEntity) {
        ArrayList<String> fields = new ArrayList<>();
        for (Field field : tableEntity.fields) {
            fields.add(field.getName() + " VARCHAR");
        }
        ;

        String query = String.format("CREATE TABLE %s (%s)", tableEntity.getFullName(), String.join(", ", fields));
        this.client.sql(query).update();
    }

    public void dropTable(Table tableEntity) {
        String query = String.format("DROP TABLE %s", tableEntity.getFullName());
        this.client.sql(query).update();
    }

    public Object executeCommand(Table table, Command command) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, NoSuchElementException {
        String commandName = command.getName();
        var commandHandlerClass = CommandsMap.get(commandName).orElseThrow();
        var commandHandler = commandHandlerClass
                .getDeclaredConstructor(new Class[] { JdbcClient.class, Table.class })
                .newInstance(client, table);

        try {
            if (commandHandler instanceof CommandHandlerWithoutParams) {
                return ((CommandHandlerWithoutParams<?>) commandHandler).handle();
            } else if (commandHandler instanceof CommandHandlerParametrized) {
                return ((CommandHandlerParametrized<?>) commandHandler).handle(command.getParams());
            }
        } catch (Exception e) {
            return new CommandExecutingError(e.getMessage());
        }
        throw new InvalidParameterException();
    }
}
