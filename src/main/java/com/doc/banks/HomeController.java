package com.doc.banks;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchEvent.Modifier;
import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.doc.banks.util.Data;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	MongoTemplate mongoTemplate;

	public static String collectionName = "filedata";

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		System.out.println(mongoTemplate.getDb());

		return "home";
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/file", method = RequestMethod.GET)
	public String file(Locale locale, Model model) throws IOException {
		logger.info("Welcome home! The client locale is {}.", locale);
		System.out.println();
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
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		System.out.println(mongoTemplate.getDb());
		//mongoTemplate.save(Data.builder(fileContent, date), collectionName);

		return "home";
	}

}
