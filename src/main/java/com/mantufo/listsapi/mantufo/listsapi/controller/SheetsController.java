package com.mantufo.listsapi.mantufo.listsapi.controller;

import com.mantufo.listsapi.mantufo.listsapi.model.ConvertedSheet;
import com.mantufo.listsapi.mantufo.listsapi.service.ConvertedSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/lists")
public class SheetsController {

    @Autowired
    ConvertedSheetService convertedSheetService;

    @CrossOrigin
    @GetMapping("/{worksheet}/{range}")
    public ConvertedSheet getConvertedSheets(@PathVariable(value = "worksheet", required = false) String worksheet,
                                             @PathVariable(value = "range", required = false) String range) throws IOException {
        return convertedSheetService.getSheet(worksheet, range);
    }
}
