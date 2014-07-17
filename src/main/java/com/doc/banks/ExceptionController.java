package com.doc.banks;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;

import com.doc.banks.constants.ErrorConstants;
import com.doc.banks.model.ErrorResponse;
import com.mongodb.MongoServerSelectionException;

@RestController
public class ExceptionController {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MongoServerSelectionException.class)
	public ErrorResponse dbConnectivityIssue(HttpServletRequest req,
			MongoServerSelectionException ex) {
		System.out.println(ex.getLocalizedMessage());
		return ErrorResponse.builder(ErrorConstants.MONGO_DB_CONNECTION,ex);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DataAccessResourceFailureException.class)
	public ErrorResponse dbIssue(HttpServletRequest req,
			DataAccessResourceFailureException ex) {
		System.out.println(ex.getLocalizedMessage());
		return ErrorResponse.builder(ErrorConstants.MONGO_DB_CONNECTION,ex);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DuplicateKeyException.class)
	public ErrorResponse duplicateKeyException(HttpServletRequest req,
			DuplicateKeyException ex) {
		System.out.println(ex.getLocalizedMessage());
		return ErrorResponse.builder(ErrorConstants.MONGO_DUPLICATE_KEY,ex);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(FileUploadException.class)
	public ErrorResponse fileUploadException(HttpServletRequest req,
			FileUploadException ex) {
		System.out.println(ex.getLocalizedMessage());
		return ErrorResponse.builder(ErrorConstants.FILE_UPLOAD_EXCEPTION,ex);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MultipartException.class)
	public ErrorResponse multipartException(HttpServletRequest req,
			MultipartException ex) {
		System.out.println(ex.getLocalizedMessage());
		return ErrorResponse.builder(ErrorConstants.MULTIPART_EXCEPTION, ex);
	}

}
