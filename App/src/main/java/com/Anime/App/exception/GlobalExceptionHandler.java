package com.Anime.App.exception;

import com.Anime.App.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private ApiResponse apiResponse;

    //Id not found exception
    @ExceptionHandler
    public ResponseEntity<ApiResponse> invalidIdException(
            IdNotFoundException exception
            ){
        apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
        apiResponse.setDescription(HttpStatus.NOT_FOUND.value() + " "  + HttpStatus.NOT_FOUND.getReasonPhrase() + " -> " + exception.getMessage()  );
        apiResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
    }

    //Internal server error exception
    @ExceptionHandler
    public ResponseEntity<ApiResponse> internalServerException(Exception exception){
        ApiResponse response=new ApiResponse();
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setTimestamp(LocalDateTime.now());
        response.setDescription("An unexpected error occurred: " + exception.getMessage());
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Invalid credentials exception
    @ExceptionHandler
    public ResponseEntity<ApiResponse> InvalidCredentialException(InvalidCredentialsException invalidCredentialsException){
        ApiResponse response=new ApiResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setTimestamp(LocalDateTime.now());
        response.setDescription(HttpStatus.NOT_FOUND.value() + " "  + HttpStatus.NOT_FOUND.getReasonPhrase() + " -> " + invalidCredentialsException.getMessage());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

}
