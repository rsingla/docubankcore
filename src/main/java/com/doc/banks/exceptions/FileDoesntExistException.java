package com.doc.banks.exceptions;

public class FileDoesntExistException extends Exception {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String message = null;
	
	public FileDoesntExistException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
