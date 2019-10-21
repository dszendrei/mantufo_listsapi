package com.mantufo.listsapi.mantufo.listsapi.controller;

import com.mantufo.listsapi.mantufo.listsapi.model.ConvertedSheet;
import com.mantufo.listsapi.mantufo.listsapi.service.ConvertedSheetService;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/lists")
public class SheetsController {

    @Autowired
    ConvertedSheetService convertedSheetService;

    @CrossOrigin
    @GetMapping("/{worksheet}/{range}")
    public ConvertedSheet getConvertedSheets(@PathVariable(value = "worksheet", required = false) String worksheet,
                                             @PathVariable(value = "range", required = false) String range) throws IOException {
        ConvertedSheet sheet = convertedSheetService.getSheet(worksheet, range);
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("mant_lists");
        database.drop();
        database.createCollection(sheet.getSheetName());
        MongoCursor<String> collectionNameIterator = database.listCollectionNames().iterator();
        while (collectionNameIterator.hasNext()) {
            System.out.println(collectionNameIterator.next());
        }
        BasicDBObject headers = new BasicDBObject();
        headers.put("headers", sheet.getHeaders());
        BasicDBObject listOfRows = new BasicDBObject();
        listOfRows.put("listOfRows", sheet.getListOfRows());
        FindIterable<Document> cursor = database.getCollection(sheet.getSheetName()).find();
        MongoCursor<Document> cursorIterator = cursor.iterator();
        while (cursorIterator.hasNext()) {
            Document doc = cursorIterator.next();
            System.out.println(doc.toJson());
        }
        return sheet;
    }
}
