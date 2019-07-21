package com.mantufo.listsapi.mantufo.listsapi.service;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class GoogleSheetsService {
    private Sheets sheetsService;

    public GoogleSheetsService(Sheets sheetsService) {
        this.sheetsService = sheetsService;
    }

    public ValueRange getRange(String sheetId, String range) throws IOException {
        return sheetsService.spreadsheets().values().get(sheetId, range).execute();
    }

    public List<List<String>> getValues(String sheetId, String range) throws IOException {
        return getRange(sheetId, range).getValues()
                .stream().map(l -> l.stream().map(Object::toString).collect(toList())).collect(toList());
    }
}
