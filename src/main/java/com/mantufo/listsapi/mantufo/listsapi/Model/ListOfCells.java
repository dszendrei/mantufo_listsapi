package com.mantufo.listsapi.mantufo.listsapi.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
@AllArgsConstructor
public class ListOfCells {

    private List<Cell> listOfCells;
    private String sheetName;

    public ListOfCells(String name, String range, List<List<String>> values) {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String[] startAndEnd = range.split(":");
        StringBuilder startX = new StringBuilder();
        StringBuilder startY = new StringBuilder();
        StringBuilder endX = new StringBuilder();
        StringBuilder endY = new StringBuilder();

        for (char c : startAndEnd[0].toCharArray()) {
            if (letters.contains(String.valueOf(c))) {
                startX.append(c);
            } else {
                startY.append(c);
            }
        }

        for (char c : startAndEnd[1].toCharArray()) {
            if (letters.contains(String.valueOf(c))) {
                endX.append(c);
            } else {
                endY.append(c);
            }
        }

        int intStartX = Coordinate.calculateIntXCoordinate(startX.toString());
        int intStartY = Integer.valueOf(startY.toString());

        int intEndX = Coordinate.calculateIntXCoordinate(endX.toString());
        int intEndY = Integer.valueOf(endY.toString());

        for (List<String> row : values) {

        }
    }
}
