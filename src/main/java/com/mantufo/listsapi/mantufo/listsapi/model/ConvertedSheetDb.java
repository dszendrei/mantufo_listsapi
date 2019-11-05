package com.mantufo.listsapi.mantufo.listsapi.model;

import com.mantufo.listsapi.mantufo.listsapi.model.enums.SheetNames;
import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.mantufo.listsapi.mantufo.listsapi.model.Coordinate.LETTERS;

@Getter
public class ConvertedSheetDb {
    private String sheetName;
    private List<String> headers;
    private Map<String, Map<String, String>> listOfRows;

    public ConvertedSheetDb(String name, List<List<String>> values) {
        this.sheetName = SheetNames.valueOf(name.toUpperCase()).toString();
        listOfRows = new LinkedHashMap<>();
        headers = new ArrayList<>();

        headers.addAll(values.get(0));

        for (int i = 1; i < values.size(); i++) {
            Map<String, String> listOfCells = new LinkedHashMap<>();
            for (int j = 0; j < values.get(i).size(); j++) {
                listOfCells.put(Coordinate.calculateStringXCoordinate(j + 1) + (i + 1), values.get(i).get(j));
            }
            int numOfEmptyCell = headers.size() - listOfCells.size();
            if (numOfEmptyCell > 0 && !listOfCells.isEmpty()) {
                for (int k = 0; k < numOfEmptyCell; k++) {
                    listOfCells.put(Coordinate
                            .calculateStringXCoordinate(listOfCells.size() + 1 + k) + (i + 1), " ");
                }
            }
            listOfRows.put("" + (i + 1), listOfCells);
        }
    }
}
