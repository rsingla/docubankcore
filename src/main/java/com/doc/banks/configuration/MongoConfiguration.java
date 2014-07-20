package com.doc.banks.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
public class MongoConfiguration extends AbstractMongoConfiguration {

	public static String databaseName = "docubankdb";

	/**
	 * 
	 * db.createUser( { "user" : "docudbuser", "pwd":
	 * "dWJ1bnR1ZG9jdW1vbmdvZGI=", "roles" : [ "readWrite" ] }, { w: "majority"
	 * , wtimeout: 5000 } );
	 * 
	 db.userdata.ensureIndex( { email: 1 }, { unique: true } )
	 * 
	 * @return
	 * @throws Exception
	 */
	public @Bean MongoDbFactory mongoDbFactory() throws Exception {
		UserCredentials userCredentials = new UserCredentials("docudbuser",
				"dWJ1bnR1ZG9jdW1vbmdvZGI=");
		return new SimpleMongoDbFactory(mongo(), databaseName, userCredentials);
	}

	@Bean
	public GridFsTemplate gridFsTemplate() throws Exception {
		return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public @Bean MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongoDbFactory());
	}

	@Override
	protected String getDatabaseName() {
		return databaseName;
	}

	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient("127.0.0.1");
	}
}
