package com.example.wowdungeontracker.controller;

import com.example.wowdungeontracker.help.WithMockOAuth2User;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class InsertRunControllerTests {

    @Autowired
    MockMvc mvc;

    @DisplayName("Die Seite besitzt eine Form mit POST-Request, um Runs hinzuzufügen")
    @Test
    @WithMockOAuth2User(login = "testuser")
    public void test_01() throws Exception {
        String textHtml = mvc.perform(get("/insert")).
                andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Document html = Jsoup.parse(textHtml);

        assertThat(html.select("form[id=insert-run-form]").toString().contains("insert-run-form")).isTrue();
    }
}
