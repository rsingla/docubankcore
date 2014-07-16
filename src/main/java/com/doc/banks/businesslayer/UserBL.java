package com.doc.banks.businesslayer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

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

		logger.info("Storing User Info: " + user);
		mongoTemplate.save(user);
		return "Saved";

	}

	public User validateLogin(String username, String password) {

		logger.info(" UserName Info: " + username);

		Query query = new Query();
		query.addCriteria(Criteria.where("user_name").is(username)
				.and("password").is(password));

		User user = mongoTemplate.findOne(query, User.class);
		System.out.println("query2 - " + query.toString());
		System.out.println("userTest - " + user);

		return user;

	}

}
