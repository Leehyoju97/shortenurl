package com.leehyoju97.shortenurl.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leehyoju97.shortenurl.dto.UrlDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UrlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("단축URL 생성 테스트")
    public void 단축URL_생성테스트() throws Exception {
        String originalUrl = "https://www.google.com/search?q=bye&oq=bye&aqs=chrome.0.69i59j69i61.754j0j7&sourceid=chrome&ie=UTF-8";

        UrlDto urlDto = UrlDto.builder()
                .originalUrl(originalUrl)
                .build();

        mockMvc.perform(post("/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(urlDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("shortUrl").value("http://localhost:8090/api/U3kwVkxy"));
    }

    @Test
    public void url실행테스트() throws Exception {

        String shortUrl = "http://localhost:8090/api/U3kwVkxy";

        mockMvc.perform(get(shortUrl))
                .andExpect(status().is3xxRedirection());
    }
}