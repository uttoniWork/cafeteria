package br.inatel.cafeteria.adapter.dto;

import java.time.LocalDateTime;

public class ExceptionDescription {

    private String message;
    private LocalDateTime exceptionTime;

    public ExceptionDescription(String message, LocalDateTime exceptionTime) {
        this.message = message;
        this.exceptionTime = exceptionTime;
    }

    public ExceptionDescription() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getExceptionTime() {
        return exceptionTime;
    }

    public void setExceptionTime(LocalDateTime exceptionTime) {
        this.exceptionTime = exceptionTime;
    }
}
