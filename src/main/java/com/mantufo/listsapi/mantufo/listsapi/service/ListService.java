package com.mantufo.listsapi.mantufo.listsapi.service;

import com.mantufo.listsapi.mantufo.listsapi.Model.Cell;
import com.mantufo.listsapi.mantufo.listsapi.Model.Coordinate;
import com.mantufo.listsapi.mantufo.listsapi.Model.ListOfCells;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListService {

    @Autowired
    ListOfCells listOfCells;

    public List<Cell> getCellsByValue(String value) {
        return listOfCells.getListOfCells().stream().filter(c -> c.getValue().equals(value)).collect(Collectors.toList());
    }

    public Cell getCellByCoordinate(Coordinate coordinate) {
        return listOfCells.getListOfCells().stream().filter(c -> c.getCoordinate().equals(coordinate)).findFirst().orElse(null);
    }

    public static void refillListOfCells() {

    }
}
