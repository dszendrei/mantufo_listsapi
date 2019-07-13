package com.mantufo.listsapi.mantufo.listsapi.controller;

import com.mantufo.listsapi.mantufo.listsapi.Model.ConvertedSheet;
import com.mantufo.listsapi.mantufo.listsapi.service.ConvertedSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/lists")
public class SheetsController {

    @Autowired
    ConvertedSheetService convertedSheetService;

    @GetMapping("/{worksheet}/{range}")
    public ConvertedSheet getConvertedSheets(@PathVariable(value = "worksheet", required = false) String worksheet,
                                             @PathVariable(value = "range", required = false) String range) throws IOException {
        return convertedSheetService.getSheet(worksheet, range);
    }
}
