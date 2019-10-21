package com.mantufo.listsapi.mantufo.listsapi.controller;

import com.mantufo.listsapi.mantufo.listsapi.model.ConvertedSheet;
import com.mantufo.listsapi.mantufo.listsapi.service.ConvertedSheetService;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@RestController
@RequestMapping("/listsdb")
public class DbController {

    @Autowired
    ConvertedSheetService convertedSheetService;

    @CrossOrigin
    @GetMapping("/{worksheet}/{range}")
    public Document getConvertedSheets(@PathVariable(value = "worksheet", required = false) String worksheet,
                                       @PathVariable(value = "range", required = false) String range) throws IOException {
        ConvertedSheet sheet = convertedSheetService.getSheet(worksheet, range);
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("mant_lists");
        database.drop();
        database.createCollection(sheet.getSheetName());
        Document document = new Document();
        document.put("sheetName", sheet.getSheetName());
        document.put("headers", sheet.getHeaders());
        document.put("listOfRows", sheet.getListOfRows());
        MongoCollection<Document> collection = database.getCollection(sheet.getSheetName());
        collection.insertOne(document);
        FindIterable<Document> documents = collection.find();
        MongoCursor<Document> docIter = documents.iterator();
        return docIter.next();
    }
}
