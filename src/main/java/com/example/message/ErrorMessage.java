package com.example.message;

import lombok.Data;

@Data
public class ErrorMessage implements IMessage {
    private String type = "error";
    private String message;

    public ErrorMessage(String message) {
        this.message=message;
    }
}
