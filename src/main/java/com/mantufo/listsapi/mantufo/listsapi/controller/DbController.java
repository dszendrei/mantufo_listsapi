package com.mantufo.listsapi.mantufo.listsapi.controller;

import com.mantufo.listsapi.mantufo.listsapi.model.enums.SheetNames;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.*;
import org.bson.Document;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/listsdb")
public class DbController {

    @CrossOrigin
    @GetMapping("/{worksheet}/{range}")
    public Document getConvertedSheets(@PathVariable(value = "worksheet", required = false) String worksheet) {
        String userName = System.getenv("dbUserName");
        String password = System.getenv("dbPassword");
        String dbUrlAndPort = System.getenv("dbUrlAndPort");
        String dbName = System.getenv("dbName");
        MongoClientURI mongoClientURI = new MongoClientURI("mongodb://" + userName
                + ":" + password + "@" + dbUrlAndPort + "/" + dbName + "?retryWrites=false");
        MongoClient mongoClient = new MongoClient(mongoClientURI);
        MongoDatabase database = mongoClient.getDatabase(dbName);
        String sheetName = SheetNames.valueOf(worksheet.toUpperCase()).toString();
        MongoCollection<Document> collection = database.getCollection(sheetName);
        FindIterable<Document> dbDocuments = collection.find();
        MongoCursor<Document> retDocIter = dbDocuments.iterator();
        Document retDoc = null;
        while (retDocIter.hasNext()) {
            Document nextDoc = retDocIter.next();
            if (nextDoc.get("sheetName").equals(sheetName)) {
                retDoc = nextDoc;
                break;
            }
        }
        return retDoc;
    }
}
