package com.doc.banks.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.doc.banks.exceptions.FileDoesntExistException;

@RunWith(MockitoJUnitRunner.class)
public class FileControllerTest {
	
	@InjectMocks
	FileController fileController= new FileController();
	
	@Mock
	MongoTemplate mongoTemplate;
	
	@Mock
	GridFsTemplate gridFsTemplate;

	@Mock
	HttpServletResponse httpResponseServlet;
	
	@Mock
	Query query;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test(expected=FileDoesntExistException.class)
	public void exceptionExpected() throws IOException, FileDoesntExistException {
		String filename = "README.md";
		
		fileController.retrieveUpload(filename, httpResponseServlet);
	}

}
