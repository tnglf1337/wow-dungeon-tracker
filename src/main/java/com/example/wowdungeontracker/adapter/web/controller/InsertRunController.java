package com.example.wowdungeontracker.adapter.web.controller;

import com.example.wowdungeontracker.adapter.database.CharacterRepository;
import com.example.wowdungeontracker.adapter.database.RunRepository;
import com.example.wowdungeontracker.adapter.web.form.RunForm;
import com.example.wowdungeontracker.domain.Run;
import com.example.wowdungeontracker.service.RunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class InsertRunController {

    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    RunRepository runRepository;

    @GetMapping("/insert")
    public String insertRun(Model model,
                            @CookieValue("loggedUser") String loggedUser) {
        model.addAttribute("dungeonList", RunService.dungeonList());
        model.addAttribute("usersCharacters", characterRepository.findByUserName(loggedUser));

        return "insert.html";
    }

    @PostMapping("/add_run")
    public String addRun(RunForm runForm, @CookieValue("loggedUser") String loggedUser) {

        Run r = new Run(null,
                runForm.charName(),
                runForm.key(),
                runForm.dungeon(),
                runForm.drop(),
                runForm.gearscore(),
                runForm.finished(),
                runForm.cleared(),
                LocalDate.now().toString(),
                loggedUser);

        runRepository.save(r);

        return "redirect:/home";
    }

}
