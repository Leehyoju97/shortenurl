package com.leehyoju97.shortenurl.service;

import com.leehyoju97.shortenurl.domain.Url;
import com.leehyoju97.shortenurl.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UrlService {

    @Autowired
    private final UrlRepository urlRepository;

    public Url joinShortUrl(String originalUrl) {

        return urlRepository.saveShortUrl(originalUrl);

    }

}
