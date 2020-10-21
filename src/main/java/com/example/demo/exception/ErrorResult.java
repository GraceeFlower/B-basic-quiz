package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResult {

    private String timestamp;
    private int status;
    private String error;
    private String message;

    public ErrorResult(String message) {
        this.message = message;
    }
}
