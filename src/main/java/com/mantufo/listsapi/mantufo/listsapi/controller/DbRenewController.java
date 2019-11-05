package com.mantufo.listsapi.mantufo.listsapi.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/db")
public class DbRenewController {

    @GetMapping("/login")
    public String login() {
        return "db-login";
    }

    @GetMapping("/renew")
    public String renewDb() {
        return "db-renew";
    }

    @PostMapping(value = "/renew",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String renewDbForm(@RequestParam Map<String, String> body) {
        body.remove("_csrf");
        Set<Map.Entry<String, String>> entrySet = body.entrySet();
        entrySet.forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
        return "db-renew";
    }
}
