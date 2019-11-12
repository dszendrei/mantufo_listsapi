package com.mantufo.listsapi.mantufo.listsapi.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConf {

    public @Bean
    MongoDatabase getMongoClient() {
        String userName = System.getenv("dbUserName");
        String password = System.getenv("dbPassword");
        String dbUrlAndPort = System.getenv("dbUrlAndPort");
        String dbName = System.getenv("dbName");
        MongoClientURI mongoClientURI = new MongoClientURI("mongodb://" + userName
                + ":" + password + "@" + dbUrlAndPort + "/" + dbName + "?retryWrites=false");
        MongoClient mongoClient = new MongoClient(mongoClientURI);
        return mongoClient.getDatabase(dbName);
    }
}
