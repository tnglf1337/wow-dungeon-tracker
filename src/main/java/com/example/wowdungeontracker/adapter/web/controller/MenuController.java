package com.example.wowdungeontracker.adapter.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MenuController {

    @GetMapping("/about")
    public String about() {
        return "about.html";
    }

    @GetMapping("/report")
    public String report() {
        return "report.html";
    }

    @PostMapping("/report_message")
    public String reportMsg(@RequestParam("reportMessage") String reportMessage) {

        //TO-Do Implementation

        return "redirect:/report";
    }
}
