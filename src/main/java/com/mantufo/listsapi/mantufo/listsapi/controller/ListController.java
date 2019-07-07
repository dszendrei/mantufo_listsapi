package com.mantufo.listsapi.mantufo.listsapi.controller;

import com.mantufo.listsapi.mantufo.listsapi.Model.Cell;
import com.mantufo.listsapi.mantufo.listsapi.Model.Coordinate;
import com.mantufo.listsapi.mantufo.listsapi.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lists")
public class ListController {

    @Autowired
    ListService listService;

    @GetMapping("/{arg}")
    public String getAvatar(@PathVariable(value = "arg", required = false) String arg) {
        System.out.println(arg);
        Cell cell = listService.getCellByCoordinate(new Coordinate(0, 0));
        //ListService.refillListOfCells();
        return cell.getValue();
    }
}
