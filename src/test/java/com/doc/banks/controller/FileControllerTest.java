package com.doc.banks.controller;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.doc.banks.FileController;

@RunWith(MockitoJUnitRunner.class)
public class FileControllerTest {
	
	@InjectMocks
	FileController fileController= new FileController();
	
	@Mock
	MongoTemplate mongoTemplate;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws IOException {
		fileController.file();
	}

}
