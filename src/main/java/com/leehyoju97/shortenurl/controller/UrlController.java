package com.leehyoju97.shortenurl.controller;

import com.leehyoju97.shortenurl.domain.Url;
import com.leehyoju97.shortenurl.dto.UrlDto;
import com.leehyoju97.shortenurl.dto.UrlResponse;
import com.leehyoju97.shortenurl.service.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class UrlController {

    @Autowired
    private final UrlService urlService;

    @PostMapping("/api")
    public ResponseEntity<?> createShortUrl(@Valid @RequestBody UrlDto urlDto) throws URISyntaxException {
        Url urlRequest = urlService.joinShortUrl(urlDto.getOriginalUrl());
        System.out.println(urlDto.getOriginalUrl());
        URI shortUri = new URI(urlRequest.getShortUrl());
        UrlResponse urlResponse = new UrlResponse("http://localhost:8090/api/" + urlRequest.getShortUrl());

        return ResponseEntity.created(shortUri).body(urlResponse);
    }
}
