package com.doc.banks.businesslayer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import com.doc.banks.controller.FileController;
import com.doc.banks.exceptions.FileDoesntExistException;
import com.mongodb.gridfs.GridFSDBFile;

@Service
public class FileBL {

	private static final Logger logger = LoggerFactory
			.getLogger(FileController.class);

	@Autowired
	GridFsTemplate gridFsTemplate;

	public GridFSDBFile retrieveFile(String filename) throws IOException,
			FileDoesntExistException {
		logger.info("File Information : " + filename);
		Query query = new Query();
		query.addCriteria(Criteria.where("filename").is(filename));
		GridFSDBFile gridFSDBFile = null;
		try {
			gridFSDBFile = gridFsTemplate.findOne(query);

		} catch (Exception ex) {
			throw new FileDoesntExistException("File doesnt exist");
		}

		if (gridFSDBFile == null)
			throw new FileDoesntExistException("File doesnt exist");

		return gridFSDBFile;
	}

}
