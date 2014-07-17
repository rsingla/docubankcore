package com.doc.banks.constants;

import com.doc.banks.model.ErrorResponse;

public enum ErrorConstants {

   MONGO_DB_CONNECTION("DOCBANKS-5001", "Mongo Db Connection is not available"),
   MONGO_DUPLICATE_KEY("DOCBANKS-5002", "Duplicate data existing in database");

	String code;
	String message;

	ErrorConstants(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public String getCode() {
		return code;
	}

	public ErrorResponse getErrorResponse(ErrorConstants errorConstant) {
		return ErrorResponse.builder(errorConstant);
	}

}
