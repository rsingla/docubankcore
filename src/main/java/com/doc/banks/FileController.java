package com.doc.banks;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.doc.banks.model.Data;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/v1/")
public class FileController {

	private static final Logger logger = LoggerFactory
			.getLogger(FileController.class);

	
	@Autowired
	MongoTemplate mongoTemplate;

	public static String collectionName = "filedata";

	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/file", method = RequestMethod.GET)
	public String file() throws IOException {
		Path logFile = Paths.get("/tmp/miketyson.txt");
		
		File temp = File.createTempFile("tempfile", ".tmp" );
		 
 	    String absolutePath = temp.getAbsolutePath();
 	    System.out.println("File path : " + absolutePath);

 	    String filePath = absolutePath.
 	    	     substring(0,absolutePath.lastIndexOf(File.separator));

 	    System.out.println("File path : " + filePath);

		BufferedReader fileContent =Files.newBufferedReader(logFile);
		
		System.out.println(fileContent);

		Date date = new Date();
	

		System.out.println(mongoTemplate.getDb());
		mongoTemplate.save(Data.builder(fileContent, date), collectionName);

		return "home";
	}

}