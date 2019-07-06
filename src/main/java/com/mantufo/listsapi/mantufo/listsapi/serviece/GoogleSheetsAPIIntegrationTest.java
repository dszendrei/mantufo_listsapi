package com.mantufo.listsapi.mantufo.listsapi.serviece;
import org.junit.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static org.junit.Assert.assertEquals;

public class GoogleSheetsAPIIntegrationTest {
    public static String SPREADSHEET_ID = "1ICM830TsC433d3FB6P1bFMZ7_zvo6a39RFUMbm6GtSI";

    @Test
    public void test() throws GeneralSecurityException, IOException {
        GoogleSheetsService service = new GoogleSheetsService(
                GoogleSheetsUtil.getSheetsService());
        assertEquals("Egyetem", service.getRange(SPREADSHEET_ID, "A1").getValues().get(0).get(0));
    }
}
