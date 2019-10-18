package com.mantufo.listsapi.mantufo.listsapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Cell {
    private Coordinate coordinate;
    private String value;
}
