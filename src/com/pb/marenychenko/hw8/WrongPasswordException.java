package com.pb.marenychenko.hw8;

public class WrongPasswordException extends Exception {

    private String message;
    public WrongPasswordException() {
        super("Ошибка с паролем");
        this.message = "Ошибка с паролем";
    }
    public WrongPasswordException(String message) {
        super(message);
        this.message = "Ошибка с паролем:" + message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}