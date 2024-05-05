package com.server.demo.common.errors;

public class UniqueConstraintError extends BaseResponseError {
    public UniqueConstraintError(String fieldName, String fieldValue) {
        super(generateTitle(fieldName, fieldValue));
    }

    private static String generateTitle(String fieldName, String fieldValue) {
        return String.format("Field '%s' must have unique value. Got value: %s ", fieldName, fieldValue);
    }
}
