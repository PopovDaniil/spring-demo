package com.server.demo.data.common;

import java.util.HashMap;

public class Command {
    private String name;
    private HashMap<String, String> params;

    public Command() {
    }

    public Command(String name) {
        this.name = name;
    }

    public Command(String name, HashMap<String, String> params) {
        this.name = name;
        this.params = params;
    }

    public String getName() {
        return this.name;
    }

    public HashMap<String, String> getParams() {
        return this.params;
    };

}
