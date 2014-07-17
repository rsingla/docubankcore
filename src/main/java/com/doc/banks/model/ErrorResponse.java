package com.doc.banks.model;

import com.doc.banks.constants.ErrorConstants;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {

	private String code;
	private String message;
	@JsonProperty("exception_details")
	private String exceptionDetails;

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

	public String getExceptionDetails() {
		return exceptionDetails;
	}

	public void setExceptionDetails(String exceptionDetails) {
		this.exceptionDetails = exceptionDetails;
	}

	public static ErrorResponse builder(ErrorConstants errorConstant,
			Exception ex) {

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setCode(errorConstant.getCode());
		errorResponse.setMessage(errorConstant.getMessage());
		errorResponse.setExceptionDetails(ex.getLocalizedMessage());
		return errorResponse;

	}
}
