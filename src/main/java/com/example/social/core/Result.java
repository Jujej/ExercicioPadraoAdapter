
package com.example.social.core;

import java.util.Optional;

public class Result<T> {
    private final boolean success;
    private final T data;
    private final String errorMessage;

    private Result(boolean success, T data, String errorMessage) {
        this.success = success;
        this.data = data;
        this.errorMessage = errorMessage;
    }

    public static <T> Result<T> ok(T data) { return new Result<>(true, data, null); }
    public static <T> Result<T> fail(String msg) { return new Result<>(false, null, msg); }

    public boolean isSuccess() { return success; }
    public Optional<T> getData() { return Optional.ofNullable(data); }
    public Optional<String> getErrorMessage() { return Optional.ofNullable(errorMessage); }
}
