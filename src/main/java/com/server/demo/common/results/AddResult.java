package com.server.demo.common.results;

import java.io.Serializable;

import com.server.demo.common.errors.BaseResponseError;

public class AddResult<T> implements Serializable {
    public T data;
    public BaseResponseError error;

    public AddResult(T data) {
        this.data = data;
    }

    public AddResult(BaseResponseError error) {
        this.error = error;
    }

    public AddResult(T data, BaseResponseError errors) {
        this.data = data;
        this.error = errors;
    }
}
