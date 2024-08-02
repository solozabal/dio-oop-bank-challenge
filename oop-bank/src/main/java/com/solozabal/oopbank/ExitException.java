package com.solozabal.oopbank;

public class ExitException extends RuntimeException {
    private final int status;

    public ExitException(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}