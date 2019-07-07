package com.mantufo.listsapi.mantufo.listsapi.service;
import com.mantufo.listsapi.mantufo.listsapi.Model.Cell;
import com.mantufo.listsapi.mantufo.listsapi.Model.Coordinate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class GoogleSheetsAPIIntegrationTest {
    private static String SPREADSHEET_ID = "1ICM830TsC433d3FB6P1bFMZ7_zvo6a39RFUMbm6GtSI";
    GoogleSheetsService service;

    @BeforeEach
    public void setUp() throws GeneralSecurityException, IOException {
        service = new GoogleSheetsService(GoogleSheetsUtil.getSheetsService());
    }

    @Test
    public void testOneCell() throws IOException {
        assertEquals("Egyetem", service.getRange(SPREADSHEET_ID, "A1").getValues().get(0).get(0));
    }

    @Test
    public void testHeaders() throws IOException {
        assertArrayEquals(new String[] {"Egyetem", "Tárgy neve", "Tárgy kódja", "Tárgyfelelős neve", "Tanszék", "Link"},
                service.getRange(SPREADSHEET_ID, "A1:F1").getValues().get(0).toArray());
    }

    @Test
    public void test2DRangeSection() throws IOException {
        List<List<Object>> values = service.getRange(SPREADSHEET_ID, "A2:B3").getValues();
        List<Cell> cells = values.stream()
                .flatMap(list -> list.stream().map(cell -> new Cell(new Coordinate(0, 0), String.valueOf(cell))))
                .collect(Collectors.toList());
        cells.forEach(System.out::println);

        IntStream.rangeClosed('A', 'Z').mapToObj(var -> (char) var).forEach(System.out::println);

        //assertArrayEquals(new String[][] {{"BME", "Űrdinamika"},{"BME", "Űrtechnológia"}}, service.getRange(SPREADSHEET_ID, "A2:B3").getValues().toArray());
    }
}
