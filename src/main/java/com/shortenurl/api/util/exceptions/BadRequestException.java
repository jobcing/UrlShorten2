package com.shortenurl.api.util.exceptions;

/**
 * BadRequestException class.
 * 
 * @author devetude
 */
public class BadRequestException extends RuntimeException {
	public BadRequestException(String message) {
		super(message);
	}
}