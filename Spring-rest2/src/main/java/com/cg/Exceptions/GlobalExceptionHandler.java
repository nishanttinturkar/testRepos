package com.cg.Exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.Exceptions.ExceptionResponse;
import com.cg.Exceptions.NoSuchEmployeeException;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(DuplicateEmpIdException.class)
	public ResponseEntity<ExceptionResponse> handleDuplicateEmpIdException(DuplicateEmpIdException e) {
		ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode("CONFLICT");
        response.setErrorMessage(e.getMessage());
        response.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.CONFLICT);
		
	}
	
	@ExceptionHandler(value=NoSuchEmployeeException.class)
	public void handleNoSuchEmployeeException(NoSuchEmployeeException e) {
		ExceptionResponse response=new ExceptionResponse();
		response.setErrorCode("BAD REQUEST");
		response.setErrorMessage(e.getMessage());
	     response.setTimeStamp(LocalDateTime.now());
		
	}
}
