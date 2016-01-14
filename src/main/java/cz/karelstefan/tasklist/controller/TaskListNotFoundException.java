package cz.karelstefan.tasklist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Task list not found")
public class TaskListNotFoundException extends RuntimeException {

    public TaskListNotFoundException() {
    }

    public TaskListNotFoundException(String message) {
        super(message);
    }

    public TaskListNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskListNotFoundException(Throwable cause) {
        super(cause);
    }

    public TaskListNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
