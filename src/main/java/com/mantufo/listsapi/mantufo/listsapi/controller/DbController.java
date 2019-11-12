package com.mantufo.listsapi.mantufo.listsapi.controller;

import com.mantufo.listsapi.mantufo.listsapi.model.enums.SheetNames;
import com.mongodb.client.*;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/listsdb")
public class DbController {

    @Autowired
    MongoDatabase database;

    @GetMapping("/{worksheet}")
    public Document getConvertedSheets(@PathVariable(value = "worksheet", required = false) String worksheet) {
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
