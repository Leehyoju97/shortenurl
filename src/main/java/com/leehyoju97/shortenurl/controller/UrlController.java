package com.leehyoju97.shortenurl.controller;

import com.leehyoju97.shortenurl.domain.Url;
import com.leehyoju97.shortenurl.dto.UrlDto;
import com.leehyoju97.shortenurl.dto.UrlResponse;
import com.leehyoju97.shortenurl.service.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UrlController {

    private final UrlService urlService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<UrlResponse> createShortUrl(@Valid @RequestBody UrlDto urlDto) throws URISyntaxException {
        String urlRequest = urlService.joinShortUrl(urlDto.getOriginalUrl());
        URI shortUri = new URI(urlRequest);
        UrlResponse urlResponse = new UrlResponse("http://localhost:8090/api/" + urlRequest);

        return ResponseEntity
                .created(shortUri)
                .body(urlResponse);
    }

    @GetMapping("/api/{shortUrl}")
    @ResponseBody
    public ResponseEntity<Url> redirectUrl(@PathVariable String shortUrl) throws URISyntaxException {
        String shortUrlInfo = urlService.redirectUrl(shortUrl);
        URI redirectUri = new URI(shortUrlInfo);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(redirectUri);

        return ResponseEntity
                .status(HttpStatus.SEE_OTHER)
                .headers(httpHeaders)
                .build();
    }

    @GetMapping("/api/list")
    public String listUrl(Model model) {
        List<Url> listUrl = urlService.listUrl();
        model.addAttribute("listUrl", listUrl);

        return "urlList";
    }

}
