package com.doc.banks.constants;


public enum ErrorConstants {

   MONGO_DB_CONNECTION("DOCBANKS-5001", "Mongo Db Connection is not available"),
   MONGO_DUPLICATE_KEY("DOCBANKS-5002", "Duplicate data existing in database"),
   FILE_UPLOAD_EXCEPTION("DOCBANKS-5003", "Exception occured during file upload"),
   MULTIPART_EXCEPTION("DOCBANKS-5004", "Exception occured during file upload/ Multipart failure to recognized"),
   FILE_DOESNT_EXIST_EXCEPTION("DOCBANKS-5005", "File Doesnt exist in the database, Please check the file name");

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

}
