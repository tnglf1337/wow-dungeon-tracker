package com.example.wowdungeontracker.adapter.web.controller;

import com.example.wowdungeontracker.adapter.database.CharacterRepository;
import com.example.wowdungeontracker.adapter.database.RunRepository;
import com.example.wowdungeontracker.domain.PlayerCharacter;
import com.example.wowdungeontracker.domain.Run;
import com.example.wowdungeontracker.service.PlayerCharacterService;
import com.example.wowdungeontracker.service.RunService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;

@Controller
public class HomeController {
    @Autowired CharacterRepository characterRepository;
    @Autowired RunRepository runRepository;

    @GetMapping("/")
    public String index(Model model,
                        HttpServletResponse httpResponse,
                        OAuth2AuthenticationToken auth) {
        List<PlayerCharacter> charList = characterRepository.findByUserName(auth.getPrincipal().getAttribute("login"));
        httpResponse.addCookie(new Cookie("selectedChar", null));
        httpResponse.addCookie(new Cookie("loggedUser", auth.getPrincipal().getAttribute("login")));

        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(OAuth2AuthenticationToken auth,
                       Model model,
                       HttpServletResponse httpResponse,
                       RedirectAttributes redirectAttributes,
                       @CookieValue("selectedChar") String selectedChar) {

        PlayerCharacterService pcs = new PlayerCharacterService(characterRepository);
        RunService rs = new RunService(runRepository);
        String userName = auth.getPrincipal().getAttribute("login");

        List<PlayerCharacter> l = (List<PlayerCharacter>) pcs.findByUserName(userName);
        model.addAttribute("usersCharacters", l);
        redirectAttributes.addFlashAttribute("usersCharacter", l);
        httpResponse.addCookie(new Cookie("loggedUser", userName));
        List<Run> allCharRuns = rs.findRunsByCharacterName(selectedChar);
        Collections.reverse(allCharRuns);

        model.addAttribute("hasRunned", rs.characterHasRunned(selectedChar));
        model.addAttribute("runList", allCharRuns);
        model.addAttribute("totalRuns", runRepository.countByCharacterName(selectedChar));
        model.addAttribute("totalDrops", runRepository.countByDrop());
        model.addAttribute("runDropRatio", rs.dropRunRatio(selectedChar) + "%");
        model.addAttribute("failedRunRate", rs.failedRunRatio(selectedChar) + "%");
        model.addAttribute("hasChar", pcs.userHasCharacters(userName));
        model.addAttribute("mostDungeon", rs.mostRunDungeon(selectedChar));
        model.addAttribute("mostChar", rs.mostUsedChar(userName));

        return "home.html";
    }

    @PostMapping("/character")
    public String characterSelection(@RequestParam("charName") String charName,
                                     HttpServletResponse httpServletResponse) {

        httpServletResponse.addCookie(new Cookie("selectedChar", charName));

        return "redirect:/home";
    }

    @PostMapping("/home/delete")
    public String deleteRun(@RequestParam("runIdToDelete") Long runIdToDelete) {

        System.out.println(runIdToDelete);

        runRepository.deleteByRunId(runIdToDelete);

        return "redirect:/home";
    }
}