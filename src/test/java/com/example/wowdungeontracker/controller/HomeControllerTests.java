package com.example.wowdungeontracker.controller;

import com.example.wowdungeontracker.adapter.database.RunRepository;
import com.example.wowdungeontracker.help.WithMockOAuth2User;
import com.example.wowdungeontracker.service.RunService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class HomeControllerTests {

    @Autowired
    MockMvc mvc;

    @DisplayName("Die Seite ist unter /home erreichbar")
    @Test
    @WithMockOAuth2User(login = "testuser")
    public void test_01() throws Exception {
        mvc.perform(get("/home")).
                andExpect(status().isOk());
    }

    @DisplayName("Die Seite besitzt Ids für die Stats-Box, Insert-Box und letten Runs")
    @Test
    @WithMockOAuth2User(login = "testuser")
    public void test_02() throws Exception {
        String textHtml = mvc.perform(get("/home")).
                andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Document html = Jsoup.parse(textHtml);

        assertThat(html.select("div[id=stats-box]").toString().contains("stats-box")).isTrue();
        assertThat(html.select("div[id=insert-box]").toString().contains("insert-box")).isTrue();
        assertThat(html.select("div[id=recent-runs]").toString().contains("recent-runs")).isTrue();
    }

    @DisplayName("Für einen ausgewählten Character, der vollendete Runs gespeichert hat, werden die Runs auf der Seite gerendert")
    @Test
    public void test_03() throws Exception {
        RunRepository repo = mock(RunRepository.class);
        RunService rs = new RunService(repo);
        String testChar = "test";
        when(rs.characterHasRunned(testChar)).thenReturn(true);

        String textHtml = mvc.perform(get("/home")).
                andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Document html = Jsoup.parse(textHtml);

        assertThat(html.select("div[id=recent-runs]").toString().contains("recent-runs")).isTrue();
    }

}
