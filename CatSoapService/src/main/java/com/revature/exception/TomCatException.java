package com.revature.exception;

public class TomCatException extends Exception {

	
	private static final long serialVersionUID = -5394552135173998369L;

	private String message = "He's not allowed in this house. :(";
	
	public TomCatException() {
		super();
	}

	public TomCatException(String message) {
		super(message);
	}
	
}
