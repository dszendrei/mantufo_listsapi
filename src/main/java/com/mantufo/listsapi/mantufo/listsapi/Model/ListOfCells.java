package com.mantufo.listsapi.mantufo.listsapi.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListOfCells {

    private List<Cell> listOfCells = new ArrayList<>();
    private String sheetName;

}
