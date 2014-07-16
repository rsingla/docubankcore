package com.doc.banks.model;

import com.doc.banks.constants.ErrorConstants;

public class ErrorResponse {

	private String code;
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static ErrorResponse builder(ErrorConstants errorConstant) {

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setCode(errorConstant.getCode());
		errorResponse.setMessage(errorConstant.getMessage());

		return errorResponse;

	}

}
