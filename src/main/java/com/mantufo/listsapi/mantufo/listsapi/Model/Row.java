package com.mantufo.listsapi.mantufo.listsapi.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Row {
    int index;
    List<Cell> listOfCells;
}
