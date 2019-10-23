package com.mantufo.listsapi.mantufo.listsapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/db")
public class DbRenewController {

    @GetMapping("/login")
    public String login() {
        return "db-login";
    }

    @PostMapping("/logout")
    public String logout() {

        return "redirect:/db/login";
    }

    @GetMapping("/renew")
    public String renewDb() {
        return "test";
    }
}
