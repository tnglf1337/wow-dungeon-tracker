package com.example.wowdungeontracker.adapter.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatisticsController {
    @GetMapping("/detailed")
        public String detail() {
        return "detailed.html";
    }
}
