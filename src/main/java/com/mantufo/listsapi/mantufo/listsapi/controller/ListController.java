package com.mantufo.listsapi.mantufo.listsapi.controller;

import com.google.api.services.sheets.v4.model.GetSpreadsheetByDataFilterRequest;
import com.mantufo.listsapi.mantufo.listsapi.Model.Cell;
import com.mantufo.listsapi.mantufo.listsapi.Model.Coordinate;
import com.mantufo.listsapi.mantufo.listsapi.serviece.ListServiece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lists")
public class ListController {

    @Autowired
    ListServiece listServiece;

    @GetMapping("/{arg}")
    public String getAvatar(@PathVariable(value = "arg", required = false) String arg) {
        System.out.println(arg);
        Cell cell = listServiece.getCellByCoordinate(new Coordinate(0, 0));
        //ListServiece.refillListOfCells();
        return cell.getValue();
    }
}
