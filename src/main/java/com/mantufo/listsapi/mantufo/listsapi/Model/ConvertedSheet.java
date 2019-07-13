package com.mantufo.listsapi.mantufo.listsapi.Model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static com.mantufo.listsapi.mantufo.listsapi.Model.Coordinate.LETTERS;

@Getter
public class ConvertedSheet {

    private List<Row> listOfRows;
    private List<Cell> headers;
    private String sheetName;

    public ConvertedSheet(String name, String range, List<List<String>> values) {
        this.sheetName = name;
        listOfRows = new ArrayList<>();
        headers = new ArrayList<>();
        String[] startAndEnd = range.split(":");
        StringBuilder startX = new StringBuilder();
        StringBuilder startY = new StringBuilder();

        for (char c : startAndEnd[0].toCharArray()) {
            if (LETTERS.contains(String.valueOf(c))) {
                startX.append(c);
            } else {
                startY.append(c);
            }
        }

        int intStartX = Coordinate.calculateIntXCoordinate(startX.toString());
        int intStartY = Integer.valueOf(startY.toString());

        for (int i = 0; i < values.get(0).size(); i++) {
            headers.add(new Cell(new Coordinate(intStartX + i, intStartY), values.get(0).get(i)));
        }

        for (int i = 1; i < values.size(); i++) {
            List<Cell> listOfCells = new ArrayList<>();
            for (int j = 0; j < values.get(i).size(); j++) {
                listOfCells.add(new Cell(new Coordinate(intStartX + j, intStartY + i), values.get(i).get(j)));
            }
            listOfRows.add(new Row(i, listOfCells));
        }
    }
}
