package com.shortenurl.api.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shortenurl.api.controllers.responses.DefaultResponse;

/**
 * GlobalExceptionHandler class.
 * 
 * @author devetude
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	private static final String TAG = GlobalExceptionHandler.class.getSimpleName();

	@ExceptionHandler(value = BadRequestException.class)
	public @ResponseBody ResponseEntity<DefaultResponse> handleBadRequest(BadRequestException badRequestException) {
		DefaultResponse dr = new DefaultResponse(DefaultResponse.Status.FAIL, badRequestException.getMessage());
		return new ResponseEntity<DefaultResponse>(dr, HttpStatus.BAD_REQUEST);
	}
}