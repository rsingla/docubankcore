package com.doc.banks;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.doc.banks.constants.ErrorConstants;
import com.doc.banks.model.ErrorResponse;
import com.mongodb.MongoServerSelectionException;

@Controller
public class ExceptionController {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MongoServerSelectionException.class)
	@ResponseBody
	public ErrorResponse dbConnectivityIssue(HttpServletRequest req,MongoServerSelectionException ex) {
		System.out.println(ex.getLocalizedMessage());
		return ErrorResponse.builder(ErrorConstants.MONGO_DB_CONNECTION);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DataAccessResourceFailureException.class)
	@ResponseBody
	public ErrorResponse dbIssue(HttpServletRequest req,DataAccessResourceFailureException ex) {
		System.out.println(ex.getLocalizedMessage());
		return ErrorResponse.builder(ErrorConstants.MONGO_DB_CONNECTION);
	}
	
	
	
	

}
