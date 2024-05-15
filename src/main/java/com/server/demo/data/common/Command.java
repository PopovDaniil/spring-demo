package com.server.demo.data.common;

import java.util.Map;

public class Command {
    private String name;
    private Map<String, String> params;

    public Command() {
    }

    public Command(String name) {
        this.name = name;
    }

    public Command(String name, Map<String, String> params) {
        this.name = name;
        this.params = params;
    }

    public String getName() {
        return this.name;
    }

    public Map<String, String> getParams() {
        return this.params;
    };

}
