package com.mantufo.listsapi.mantufo.listsapi.controller;

import com.mantufo.listsapi.mantufo.listsapi.model.ConvertedSheetDb;
import com.mantufo.listsapi.mantufo.listsapi.service.ConvertedSheetService;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.*;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/listsdb")
public class DbController {

    @Autowired
    ConvertedSheetService convertedSheetService;

    @CrossOrigin
    @GetMapping("/{worksheet}/{range}")
    public Document getConvertedSheets(@PathVariable(value = "worksheet", required = false) String worksheet,
                                       @PathVariable(value = "range", required = false) String range) throws IOException {
        ConvertedSheetDb sheet = convertedSheetService.getSheetDb(worksheet, range);
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
        FindIterable<Document> dbDocuments = collection.find();
        MongoCursor<Document> retDocIter = dbDocuments.iterator();
        Document retDoc = null;
        while (retDocIter.hasNext()) {
            Document nextDoc = retDocIter.next();
            if (nextDoc.get("sheetName").equals(document.get("sheetName"))) {
                retDoc = nextDoc;
                break;
            }
        }
        return retDoc;
    }
}
