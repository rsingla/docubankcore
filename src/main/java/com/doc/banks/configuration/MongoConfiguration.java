package com.doc.banks.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
public class MongoConfiguration extends AbstractMongoConfiguration {

  /**
   * db.createUser( { "user" : "docudbuser", "pwd": "dWJ1bnR1ZG9jdW1vbmdvZGI=", "roles" : [ "readWrite" ] }, { w:
   * "majority" , wtimeout: 5000 } ); db.userdata.ensureIndex( { email: 1 }, { unique: true } )
   * @return
   * @throws Exception
   */
  // public @Bean MongoDbFactory mongoDbFactory() throws Exception {
  // UserCredentials userCredentials = new UserCredentials("docudbuser", "dWJ1bnR1ZG9jdW1vbmdvZGI=");
  // return new SimpleMongoDbFactory(, databaseName, userCredentials);
  // }

  @Override
  public String getDatabaseName() {
    return "docubankdb";
  }

  @Override
  @Bean
  public Mongo mongo() throws Exception {
    return new MongoClient(Collections.singletonList(new ServerAddress("127.0.0.1", 27017)),
        Collections.singletonList(MongoCredential.createCredential("docubankdb", "docudbuser",
            "dWJ1bnR1ZG9jdW1vbmdvZGI=".toCharArray())));
  }

  @Bean
  public GridFsTemplate gridFsTemplate() throws Exception {
    return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
  }

  /**
   * @return
   * @throws Exception
   */
  public @Bean MongoTemplate mongoTemplate() throws Exception {
    return new MongoTemplate(mongoDbFactory());
  }

}
