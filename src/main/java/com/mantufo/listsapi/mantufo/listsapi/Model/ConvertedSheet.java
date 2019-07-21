package com.mantufo.listsapi.mantufo.listsapi.Model;

import com.mantufo.listsapi.mantufo.listsapi.Model.Enum.SheetNames;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static com.mantufo.listsapi.mantufo.listsapi.Model.Coordinate.LETTERS;

@Getter
public class ConvertedSheet {
    private String sheetName;
    private List<Cell> headers;
    private List<Row> listOfRows;

    public ConvertedSheet(String name, String range, List<List<String>> values) {
        this.sheetName = SheetNames.valueOf(name.toUpperCase()).toString();
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
            int numOfEmptyCell = headers.size() - listOfCells.size();
            if (numOfEmptyCell > 0 && !listOfCells.isEmpty()) {
                for (int k = 0; k < numOfEmptyCell; k++) {
                    listOfCells.add(new Cell(new Coordinate(
                           listOfCells.get(listOfCells.size() - 1).getCoordinate().getX() + 1,
                           listOfCells.get(listOfCells.size() - 1).getCoordinate().getY()), " "));
                }
            }
            listOfRows.add(new Row(i, listOfCells));
        }
    }
}
