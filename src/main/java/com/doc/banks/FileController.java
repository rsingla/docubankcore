package com.doc.banks;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/v1/")
public class FileController extends ExceptionController {

	private static final Logger logger = LoggerFactory
			.getLogger(FileController.class);

	@Autowired
	MongoTemplate mongoTemplate;

	public static String collectionName = "filedata";

	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
	public String processUpload(@RequestParam("file") MultipartFile file)
			throws IOException {
		System.out.println(file.getContentType());
		System.out.println(file.getInputStream());
		return "File '" + file.getOriginalFilename()
				+ "' uploaded successfully";
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/file", method = RequestMethod.GET)
	public String file() throws IOException {

		File temp = File.createTempFile("tempfile", ".tmp");

		String absolutePath = temp.getAbsolutePath();
		System.out.println("File path : " + absolutePath);

		String filePath = absolutePath.substring(0,
				absolutePath.lastIndexOf(File.separator));

		System.out.println("File path : " + filePath);

		Date date = new Date();

		System.out.println(mongoTemplate.getDb());
		// mongoTemplate.save(Data.builder(fileContent, date), collectionName);

		return "home";
	}

}
