package com.imjcker.ddns.common;

import lombok.Data;

@Data
public class Api<T> {
    private int code;
    private String message;
    private T data;

    public Api(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public Api(String message, T data) {
        this.code = 200;
        this.message = message;
        this.data = data;
    }
    public Api(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public Api(int code, T data) {
        this.code = code;
        this.message = "success";
        this.data = data;
    }
    public Api(T data) {
        this.code = 200;
        this.message = "success";
        this.data = data;
    }
    public Api() {
        this.code = 200;
        this.message = "success";
    }

    public static <T> Api<T> success() {
        return new Api<>();
    }

    public static <T> Api<T> success(T data) {
        return new Api<>(200,  data);
    }

    public static <T> Api<T> success(String message, T data) {
        return new Api<>(message, data);
    }

    public static <T> Api<T> fail(int code, String message) {
        return new Api<>(code, message);
    }

    public static <T> Api<T> fail(String message) {
        return new Api<>(600, message);
    }

    public static <T> Api<T> fail() {
        return new Api<>(600, "System error");
    }
}
