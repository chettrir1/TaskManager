package com.techsales.taskmanager.data.error;

public class FailedResponseException extends Throwable {

    private final boolean error;

    public FailedResponseException(boolean error, String message) {
        super(message);
        this.error = error;
    }
}
