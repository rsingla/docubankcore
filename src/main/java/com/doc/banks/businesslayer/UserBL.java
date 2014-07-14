package com.doc.banks.businesslayer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doc.banks.UserController;
import com.doc.banks.model.User;

@Service
public class UserBL {

	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@Autowired
	MongoTemplate mongoTemplate;

	public static String collectionName = "userdata";

	public String insertUser(User user) {
		
		mongoTemplate.save(user);
		
		return "Saved";
		// TODO Auto-generated method stub
		
	}
	
}
