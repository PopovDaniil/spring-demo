package com.server.demo.data.commands.params;

import java.util.Map;

import com.server.demo.data.common.CommandParams;

public class AddCommandParams extends CommandParams {
    public Map<String, String> data;

    public AddCommandParams(Map<String, String> data) {
        this.data = data;
    }
}