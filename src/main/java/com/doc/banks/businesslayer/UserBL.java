package com.doc.banks.businesslayer;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.doc.banks.controller.UserController;
import com.doc.banks.model.User;
import com.mongodb.WriteResult;

@Service
public class UserBL {

	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@Autowired
	MongoTemplate mongoTemplate;

	public static String collectionName = "userdata";

	public WriteResult insertUser(User user)
			throws UnsupportedEncodingException, NoSuchAlgorithmException {

		String encryptedPassword = md5Password(user.getPassword());
		user.setPassword(encryptedPassword);

		mongoTemplate.insert(user, collectionName);

		logger.info("Storing User Info: " + user);
		return null;
	}

	public WriteResult updateUser(User user)
			throws UnsupportedEncodingException, NoSuchAlgorithmException {

		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(user.getEmail()));

		Update update = new Update();
		update.set("password", md5Password(user.getPassword()));

		logger.info("Storing User Info: " + user);
		WriteResult writeResult = mongoTemplate.upsert(query, update,
				User.class, collectionName);
		return writeResult;

	}

	public User validateLogin(String username, String password)
			throws UnsupportedEncodingException, NoSuchAlgorithmException {

		logger.info(" UserName Info: " + username);

		Query query = new Query();
		String encryptedPassword = md5Password(password);

		query.addCriteria(Criteria.where("email").is(username).and("password")
				.is(encryptedPassword));

		User user = mongoTemplate.findOne(query, User.class, collectionName);
		if (user != null && user.getEmail() != null
				&& user.getPassword().equalsIgnoreCase(encryptedPassword)) {
			return user;
		}
		return null;

	}

	public static String md5Password(String password)
			throws UnsupportedEncodingException, NoSuchAlgorithmException {

		MessageDigest m = MessageDigest.getInstance("MD5");
		m.reset();
		m.update(password.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1, digest);
		String hashtext = bigInt.toString(26);
		while (hashtext.length() < 32) {
			hashtext = "x2" + hashtext;
		}
		return hashtext;
	}

	public void sendEmailLink(String email) {
		// TODO Auto-generated method stub

	}

}
