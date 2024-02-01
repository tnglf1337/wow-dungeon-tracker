package com.example.wowdungeontracker.adapter.web.controller;

import com.example.wowdungeontracker.adapter.database.CharacterRepository;
import com.example.wowdungeontracker.adapter.web.form.NewCharForm;
import com.example.wowdungeontracker.domain.PlayerCharacter;
import com.example.wowdungeontracker.service.PlayerCharacterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CharacterController {

    @Autowired
    CharacterRepository characterRepository;

    @GetMapping("/character")
    public String newChar(Model model,
                          NewCharForm newCharForm,
                          @CookieValue("loggedUser") String loggedUser,
                          @CookieValue("selectedChar") String selectedChar) {

        PlayerCharacterService pcs = new PlayerCharacterService(characterRepository);
        model.addAttribute("characterList", pcs.findByUserName(loggedUser));
        model.addAttribute("hasChar", pcs.userHasCharacters(loggedUser));

        return "character.html";
    }

    @PostMapping("/new_char")
    public String newCharCreation(@Valid NewCharForm newCharForm,
                                  BindingResult br,
                                  @CookieValue("loggedUser") String loggedUser) {

        if(br.hasErrors()) {
            System.out.println("error char creation");
            return "character.html";
        }

        PlayerCharacterService pcs = new PlayerCharacterService(characterRepository);
        PlayerCharacter newChar = new PlayerCharacter(null, loggedUser, newCharForm.charName());

        pcs.insert(newChar);

        return "redirect:/home";
    }

    @PostMapping("/delete_char")
    public String deleteChar(@RequestParam("charIdToDelete") Long charIdToDelete, @CookieValue("selectedChar") String selectedChar) {

        if(selectedChar != "") {
            PlayerCharacterService pcs = new PlayerCharacterService(characterRepository);

            pcs.deleteCharById(charIdToDelete, selectedChar);
        } else {
            System.out.println("no char selected");
        }

        return "redirect:/character";
    }
}
