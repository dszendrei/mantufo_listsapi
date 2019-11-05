package com.mantufo.listsapi.mantufo.listsapi.controller;

import com.mantufo.listsapi.mantufo.listsapi.model.ConvertedSheetDb;
import com.mantufo.listsapi.mantufo.listsapi.service.ConvertedSheetService;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.ReplaceOptions;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/db")
public class DbRenewController {

    @Autowired
    ConvertedSheetService convertedSheetService;

    @GetMapping("/login")
    public String login() {
        return "db-login";
    }

    @GetMapping("/renew")
    public String renewDb() {
        return "db-renew";
    }

    @PostMapping("/renew")
    public String renewDbForm(@RequestParam Map<String, String> body) throws IOException {
        body.remove("_csrf");
        body.remove("username");
        body.remove("password");
        Set<Map.Entry<String, String>> entrySet = body.entrySet();
        List<String> sheetNames = entrySet.stream().filter(entry -> entry.getValue().equals("on"))
                .map(Map.Entry::getKey).collect(Collectors.toList());
        for (String name : sheetNames) {
            renewDbCollection(name);
        }
        return "db-renew";
    }

    private void renewDbCollection(String worksheet) throws IOException {
        ConvertedSheetDb sheet = convertedSheetService.getSheetDb(worksheet);
        String userName = System.getenv("dbUserName");
        String password = System.getenv("dbPassword");
        String dbUrlAndPort = System.getenv("dbUrlAndPort");
        String dbName = System.getenv("dbName");
        MongoClientURI mongoClientURI = new MongoClientURI("mongodb://" + userName
                + ":" + password + "@" + dbUrlAndPort + "/" + dbName + "?retryWrites=false");
        MongoClient mongoClient = new MongoClient(mongoClientURI);
        MongoDatabase database = mongoClient.getDatabase(dbName);
        MongoCursor<String> collectionNameIterator = database.listCollectionNames().iterator();
        boolean collectionExists = false;
        while (collectionNameIterator.hasNext()) {
            if (collectionNameIterator.next().equals(sheet.getSheetName())) {
                collectionExists = true;
            }
        }
        if (!collectionExists) {
            database.createCollection(sheet.getSheetName());
        }
        MongoCollection<Document> collection = database.getCollection(sheet.getSheetName());
        Document document = new Document();
        document.append("sheetName", sheet.getSheetName());
        document.append("headers", sheet.getHeaders());
        document.append("listOfRows", sheet.getListOfRows());
        collection.replaceOne(
                new Document().append("sheetName", document.get("sheetName")), document,
                new ReplaceOptions().upsert(true));
    }
}
