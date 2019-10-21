package com.mantufo.listsapi.mantufo.listsapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Row {
    //int index;
    List<String> listOfCells;
}
