package com.mantufo.listsapi.mantufo.listsapi.service;
import com.mantufo.listsapi.mantufo.listsapi.model.Cell;
import com.mantufo.listsapi.mantufo.listsapi.model.Coordinate;
import com.mantufo.listsapi.mantufo.listsapi.model.enums.SheetNames;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.stream.Collectors;

import static com.mantufo.listsapi.mantufo.listsapi.service.ConvertedSheetService.SPREADSHEET_ID;
import static org.junit.jupiter.api.Assertions.*;

public class GoogleSheetsAPIIntegrationTest {
    GoogleSheetsService service;

    @BeforeEach
    public void setUp() throws GeneralSecurityException, IOException {
        service = new GoogleSheetsService(GoogleSheetsUtil.getSheetsService());
    }

    @Test
    public void testOneCell() throws IOException {
        assertEquals("Egyetem", service
                .getRange(SPREADSHEET_ID, SheetNames.convertSheetNameToGetValues(SheetNames.TANTARGYAK_ITTHON) + "A1").getValues().get(0).get(0));
        assertEquals("Ajánlott szakok:", service
                .getRange(SPREADSHEET_ID, SheetNames.convertSheetNameToGetValues(SheetNames.SZAKOK) + "A1").getValues().get(0).get(0));
        assertEquals("Verseny neve", service
                .getRange(SPREADSHEET_ID, SheetNames.convertSheetNameToGetValues(SheetNames.VERSENYEK) + "A1").getValues().get(0).get(0));
        assertEquals("Diploma", service
                .getRange(SPREADSHEET_ID, SheetNames.convertSheetNameToGetValues(SheetNames.EUROPAI_DIPLOMAK) + "A1").getValues().get(0).get(0));
        assertEquals("Események amikre mutató linket édemes berakni a honlapba:", service
                .getRange(SPREADSHEET_ID, SheetNames.convertSheetNameToGetValues(SheetNames.ESEMENYEK) + "A1").getValues().get(0).get(0));
        assertEquals("Kutatócsoportok és cégek (Űrkatalógus 2016)", service
                .getRange(SPREADSHEET_ID, SheetNames.convertSheetNameToGetValues(SheetNames.KUTATOCSOPORTOK_ES_CEGEK) + "A1").getValues().get(0).get(0));
        assertEquals("Potenciális partnerek", service
                .getRange(SPREADSHEET_ID, SheetNames.convertSheetNameToGetValues(SheetNames.PARTNEREK) + "A1").getValues().get(0).get(0));
        assertEquals("Mikor", service
                .getRange(SPREADSHEET_ID, SheetNames.convertSheetNameToGetValues(SheetNames.EDDIGI_MEGJELENESEINK) + "A1").getValues().get(0).get(0));
    }

    @Test
    public void testHeaders() throws IOException {
        assertArrayEquals(new String[] {"Egyetem", "Tárgy neve", "Tárgy kódja", "Tárgyfelelős neve", "Tanszék", "Link"},
                service.getRange(SPREADSHEET_ID, SheetNames.convertSheetNameToGetValues(SheetNames.TANTARGYAK_ITTHON) + "A1:F1").getValues().get(0).toArray());
    }

    @Test
    public void test2DRangeSection() throws IOException {
        String range = "A2:B3";
        List<List<Object>> values = service.getRange(SPREADSHEET_ID, range).getValues();
        List<Cell> cells = values.stream()
                .flatMap(list -> list.stream().map(cell -> new Cell(new Coordinate(0, 0), String.valueOf(cell))))
                .collect(Collectors.toList());
        cells.forEach(System.out::println);

        //assertArrayEquals(new String[][] {{"BME", "Űrdinamika"},{"BME", "Űrtechnológia"}}, service.getRange(SPREADSHEET_ID, "A2:B3").getValues().toArray());
    }
}
