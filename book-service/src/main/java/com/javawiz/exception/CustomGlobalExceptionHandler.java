package com.javawiz.exception;

import java.io.IOException;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.javawiz.model.ErrorMessage;

@RestControllerAdvice
public class CustomGlobalExceptionHandler {

	@ExceptionHandler(BookNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorMessage springHandleNotFound(Exception ex, WebRequest request) throws IOException {
		return ErrorMessage.builder().statusCode(HttpStatus.NOT_FOUND.value()).timestamp(new Date()).message(ex.getMessage()).build();
	}

	@ExceptionHandler(BookUnSupportedFieldPatchException.class)
	public ErrorMessage springUnSupportedFieldPatch(Exception ex, WebRequest request) throws IOException {
		return ErrorMessage.builder().statusCode(HttpStatus.METHOD_NOT_ALLOWED.value()).message(ex.getMessage()).build();
	}
}