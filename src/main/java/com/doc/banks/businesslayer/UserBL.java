package com.doc.banks.businesslayer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.doc.banks.UserController;
import com.doc.banks.model.User;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;

@Service
public class UserBL {

	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@Autowired
	MongoTemplate mongoTemplate;

	public static String collectionName = "userdata";

	public String insertUser(User user) throws UnsupportedEncodingException,
			NoSuchAlgorithmException {

		Query query = new Query();
		query.addCriteria(Criteria.where("user.email").is(user.getEmail()));
		System.out.println(user.getPassword());
		 String encryptedPassword = md5Password(user.getPassword());
		System.out.println(encryptedPassword);

		Update update = new Update();
		update.set("user", user);

		logger.info("Storing User Info: " + user);
		mongoTemplate.upsert(query, update, collectionName);
		return "Saved";

	}

	public User validateLogin(String username, String password) {

		logger.info(" UserName Info: " + username);

		Query query = new Query();
		query.addCriteria(Criteria.where("user.user_name").is(username)
				.and("user.password").is(password));

		User user = mongoTemplate.findOne(query, User.class);
		System.out.println("query2 - " + query.toString());
		System.out.println("userTest - " + user);

		return user;

	}

	public static String md5Password(String password)
			throws UnsupportedEncodingException, NoSuchAlgorithmException {

		MessageDigest m = MessageDigest.getInstance("MD5");
		m.reset();
		m.update(password.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1, digest);
		String hashtext = bigInt.toString(26);
		// Now we need to zero pad it if you actually want the full 32 chars.
		while (hashtext.length() < 32) {
			hashtext = "x2" + hashtext;
		}
		return hashtext;
	}

	public void sendEmailLink(String email) {
		// TODO Auto-generated method stub

	}
	
	public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		System.out.println(md5Password("1234abcd"));
	}

}
