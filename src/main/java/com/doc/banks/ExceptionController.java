package com.doc.banks;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
		return ErrorResponse.builder(ErrorConstants.MONGO_DB_CONNECTION);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DataAccessResourceFailureException.class)
	@ResponseBody
	public ErrorResponse dbIssue(HttpServletRequest req,
			DataAccessResourceFailureException ex) {
		System.out.println(ex.getLocalizedMessage());
		return ErrorResponse.builder(ErrorConstants.MONGO_DB_CONNECTION);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DuplicateKeyException.class)
	@ResponseBody
	public ErrorResponse duplicateKeyException(HttpServletRequest req,
			DuplicateKeyException ex) {
		System.out.println(ex.getLocalizedMessage());
		return ErrorResponse.builder(ErrorConstants.MONGO_DUPLICATE_KEY);
	}

}
