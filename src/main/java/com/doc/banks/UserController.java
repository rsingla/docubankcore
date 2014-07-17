package com.doc.banks;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.hibernate.validator.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.doc.banks.businesslayer.UserBL;
import com.doc.banks.model.User;
import com.mongodb.WriteResult;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping("/v1/")
public class UserController extends ExceptionController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@Autowired
	UserBL userBL;

	public static String collectionName = "userdata";

	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	@RequestMapping(value = "/registeruser", method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
	public WriteResult registerUser(@RequestBody User user) throws IOException,
			NoSuchAlgorithmException {

		if (user != null) {
			return userBL.insertUser(user);
		}

		return null;
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/register/email/{email}", method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
	public String register(@PathVariable String email) throws IOException {
		userBL.sendEmailLink(email);

		return "home";
	}

	@RequestMapping(value = "/validatelogin/username/{username}/password/{password}", method = RequestMethod.GET, consumes = { "application/json" }, produces = { "application/json" })
	public boolean validateLogin(@PathVariable @Email String username,
			@PathVariable String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		Boolean value = false;
		User user = userBL.validateLogin(username, password);
		if (user != null) {
			value = true;
		}
		return value;

	}

}
