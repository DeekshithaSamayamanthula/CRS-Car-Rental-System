package com.carrentalsystem.main.exception;

public class InvalidIdException extends Exception {
private static final long serialVersionUID = -562934416888787124L;
	
	private String message;

	public InvalidIdException(String message) {
		
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
