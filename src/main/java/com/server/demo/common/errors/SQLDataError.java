package com.server.demo.common.errors;

public class SQLDataError extends BaseResponseError {

    public SQLDataError(String text, String sql) {
        super(generateTitle(text, sql));
    }

    private static String generateTitle(String text, String sql) {
        return String.format("Error executing sql query: %s - %s", sql, text);
    }
}
