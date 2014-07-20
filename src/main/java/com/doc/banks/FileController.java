package com.doc.banks;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.gridfs.GridFSDBFile;

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

	@Autowired
	GridFsTemplate gridFsTemplate;

	public static String collectionName = "filedata";

	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
	public String processUpload(@RequestParam("file") MultipartFile file)
			throws IOException {
		System.out.println(file.getContentType());
		System.out.println(file.getInputStream());

		gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(),
				file.getContentType());

		return "File '" + file.getOriginalFilename()
				+ "' uploaded successfully";
	}

	@RequestMapping(value = "/getfile/{filename}/filetype/{filetype}", method = RequestMethod.GET, produces = "*/*", consumes = "*/*")
	public void retrieveUpload(@PathVariable String filename,
			@PathVariable String filetype, HttpServletResponse response)
			throws IOException {
		String completeFile = filename.concat("." + filetype);
		System.out.println(completeFile);
		Query query = new Query();
		query.addCriteria(Criteria.where("filename").is(completeFile));

		GridFSDBFile gridFSDbFile = gridFsTemplate.findOne(query);

		IOUtils.copy(gridFSDbFile.getInputStream(), response.getOutputStream());
		response.flushBuffer();
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
