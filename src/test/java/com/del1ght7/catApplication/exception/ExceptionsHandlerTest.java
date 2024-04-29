package com.del1ght7.catApplication.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ExceptionsHandlerTest {

    @InjectMocks
    private ExceptionsHandler exceptionsHandler;

    @Test
    void handleInternalServerError() {
        RuntimeException exception = new RuntimeException("Internal Server Error");

        ErrorResponse response = exceptionsHandler.handleInternalServerError(exception);

        assertEquals("Internal Server Error", response.getMessage());
    }

    @Test
    void handleBadRequestException() {
        Exception exception = new HttpClientErrorException(HttpStatus.BAD_REQUEST);

        ErrorResponse response = exceptionsHandler.handleBadRequestException(exception);

        assertEquals("400 error, BAD REQUEST", response.getMessage());
    }

    @Test
    void handleMethodNotAllowed() {
        Exception exception = new HttpRequestMethodNotSupportedException("GET");

        ErrorResponse response = exceptionsHandler.handleMethodNotAllowed(exception);

        assertEquals("405 error, METHOD NOT ALLOWED", response.getMessage());
    }

    @Test
    void handlerFoundException() {
        Exception exception = new NoHandlerFoundException("GET", "/cats", null);

        ErrorResponse response = exceptionsHandler.handlerFoundException(exception);

        assertEquals("404 error, NOT FOUND", response.getMessage());
    }
}
