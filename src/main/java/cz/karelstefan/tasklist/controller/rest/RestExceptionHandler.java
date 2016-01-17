package cz.karelstefan.tasklist.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice(basePackages = {"cz.karelstefan.tasklist.controller.rest"})
public class RestExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public RestError exception(Exception exception, WebRequest request) {
        return new RestError(exception.getMessage());
    }

    public class RestError {
        private String error;

        public RestError(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}