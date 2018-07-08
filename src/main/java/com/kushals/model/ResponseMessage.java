package com.kushals.model;

/**
 * Custom Response model 
 * @author Kushal
 *
 */
public class ResponseMessage {

	String message;

	public ResponseMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

}
