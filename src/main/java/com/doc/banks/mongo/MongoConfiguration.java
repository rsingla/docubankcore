package com.doc.banks.mongo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.Mongo;

@Configuration
public class MongoConfiguration {

	public @Bean MongoDbFactory mongoDbFactory() throws Exception {
		@SuppressWarnings("deprecation")
		Mongo mongo = new Mongo();
		UserCredentials userCredentials = new UserCredentials("docudbuser", "dWJ1bnR1ZG9jdW1vbmdvZGI=");
		return new SimpleMongoDbFactory(mongo, "docubankdb",
				userCredentials);
	}

	public @Bean MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongoDbFactory());
	}
}
