package com.doc.banks.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;

import com.doc.banks.constants.ErrorConstants;
import com.doc.banks.exceptions.FileDoesntExistException;
import com.doc.banks.model.ErrorResponse;
import com.mongodb.MongoServerSelectionException;

@RestController
public class ExceptionController {

	private static final Logger logger = LoggerFactory
			.getLogger(FileController.class);
	
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(MongoServerSelectionException.class)
	public ErrorResponse dbConnectivityIssue(HttpServletRequest req,
			MongoServerSelectionException ex) {
		logger.error(ex.getLocalizedMessage());
		return ErrorResponse.builder(ErrorConstants.MONGO_DB_CONNECTION, ex);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DataAccessResourceFailureException.class)
	public ErrorResponse dbIssue(HttpServletRequest req,
			DataAccessResourceFailureException ex) {
		logger.error(ex.getLocalizedMessage());
		return ErrorResponse.builder(ErrorConstants.MONGO_DB_CONNECTION, ex);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DuplicateKeyException.class)
	public ErrorResponse duplicateKeyException(HttpServletRequest req,
			DuplicateKeyException ex) {
		logger.error(ex.getLocalizedMessage());
		return ErrorResponse.builder(ErrorConstants.MONGO_DUPLICATE_KEY, ex);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(FileUploadException.class)
	public ErrorResponse fileUploadException(HttpServletRequest req,
			FileUploadException ex) {
		logger.error(ex.getLocalizedMessage());
		return ErrorResponse.builder(ErrorConstants.FILE_UPLOAD_EXCEPTION, ex);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MultipartException.class)
	public ErrorResponse multipartException(HttpServletRequest req,
			MultipartException ex) {
		logger.error(ex.getLocalizedMessage());
		return ErrorResponse.builder(ErrorConstants.MULTIPART_EXCEPTION, ex);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(FileDoesntExistException.class)
	public ErrorResponse fileDoesntExistException(HttpServletRequest req,
			FileDoesntExistException ex) {
		logger.error(ex.getLocalizedMessage());
		return ErrorResponse.builder(
				ErrorConstants.FILE_DOESNT_EXIST_EXCEPTION, ex);
	}

}
