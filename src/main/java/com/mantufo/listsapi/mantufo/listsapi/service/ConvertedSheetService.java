package com.mantufo.listsapi.mantufo.listsapi.service;
import com.mantufo.listsapi.mantufo.listsapi.Model.ConvertedSheet;
import com.mantufo.listsapi.mantufo.listsapi.Model.Enum.SheetNames;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
public class ConvertedSheetService {
    static final String SPREADSHEET_ID = "1ICM830TsC433d3FB6P1bFMZ7_zvo6a39RFUMbm6GtSI";
    private GoogleSheetsService service = new GoogleSheetsService(GoogleSheetsUtil.getSheetsService());

    public ConvertedSheetService() throws GeneralSecurityException, IOException {
    }

    public ConvertedSheet getSheet(String worksheet, String range) throws IOException {
        List<List<String>> values = service.getValues(SPREADSHEET_ID, SheetNames.valueOf(worksheet.toUpperCase()) + range);
        return new ConvertedSheet(worksheet, range, values);
    }
}
