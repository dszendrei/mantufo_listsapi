package com.mantufo.listsapi.mantufo.listsapi.service;
import com.mantufo.listsapi.mantufo.listsapi.model.ConvertedSheet;
import com.mantufo.listsapi.mantufo.listsapi.model.ConvertedSheetDb;
import com.mantufo.listsapi.mantufo.listsapi.model.enums.SheetNames;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
public class ConvertedSheetService {
    static final String SPREADSHEET_ID = "1ICM830TsC433d3FB6P1bFMZ7_zvo6a39RFUMbm6GtSI";
    private static final String RANGE = "A1:G100";
    private GoogleSheetsService service = new GoogleSheetsService(GoogleSheetsUtil.getSheetsService());

    public ConvertedSheetService() throws GeneralSecurityException, IOException {
    }

    public ConvertedSheet getSheet(String worksheet, String range) throws IOException {
        List<List<String>> values = service.getValues(SPREADSHEET_ID, SheetNames.convertSheetNameToGetValues(worksheet) + range);
        return new ConvertedSheet(worksheet, range, values);
    }

    public ConvertedSheetDb getSheetDb(String worksheet) throws IOException {
        List<List<String>> values = service.getValues(SPREADSHEET_ID, SheetNames.convertSheetNameToGetValues(worksheet) + RANGE);
        return new ConvertedSheetDb(worksheet, values);
    }
}
