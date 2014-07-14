package com.doc.banks;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.doc.banks.businesslayer.UserBL;
import com.doc.banks.model.User;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/v1/")
public class UserController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@Autowired
	UserBL userBL;

	public static String collectionName = "userdata";

	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/users", method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
	public String registerUser(@RequestBody User user) throws IOException {

		if(user != null) {
			userBL.insertUser(user);
		}

		return "home";
	}

}
