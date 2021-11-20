package com.pb.marenychenko.hw8;

public class WrongLoginException extends Exception {

    private String message;
    public WrongLoginException() {
        super("Ошибка с логином");
        this.message = "Ошибка с логином";
    }
    public WrongLoginException(String message) {
        super(message);
        this.message = "Ошибка с логином:" + message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}