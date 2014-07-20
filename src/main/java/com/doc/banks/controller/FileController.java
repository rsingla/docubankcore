package com.doc.banks.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.doc.banks.businesslayer.FileBL;
import com.doc.banks.exceptions.FileDoesntExistException;
import com.doc.banks.model.FileDetail;
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

	@Autowired
	FileBL fileBL;

	public static String collectionName = "filedata";

	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
	public String processUpload(@RequestParam("file") MultipartFile file)
			throws IOException {
		logger.info(file.getContentType());

		logger.info("Input Stream File: " + file.getInputStream());

		gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(),
				file.getContentType());

		return "File '" + file.getOriginalFilename()
				+ "' uploaded successfully";
	}

	@RequestMapping(value = "/getfile/{filename}/", method = RequestMethod.GET, produces = "*/*", consumes = "*/*")
	public void retrieveUpload(@PathVariable String filename,
			HttpServletResponse response) throws IOException,
			FileDoesntExistException {
		logger.info("File Information : " + filename);

		try {
			IOUtils.copy(fileBL.retrieveFile(filename).getInputStream(),
					response.getOutputStream());
			response.flushBuffer();
		} catch (Exception ex) {
			throw new FileDoesntExistException("File doesnt exist");
		}

	}

	/**
	 * Method is to GET All files
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/getallfiles", method = RequestMethod.GET, produces = { "application/json" })
	public List<FileDetail> getallfiles() throws IOException {
		Query query = new Query();
		List<GridFSDBFile> gridFSDbFileList = gridFsTemplate.find(query);

		List<FileDetail> fileDetailList = new ArrayList<>();

		for (GridFSDBFile gridFSDBFile : gridFSDbFileList) {
			fileDetailList.add(FileDetail.builder(gridFSDBFile.getFilename(),
					gridFSDBFile.getUploadDate(), gridFSDBFile.getLength(),
					gridFSDBFile.getContentType()));
		}

		return fileDetailList;
	}

}
