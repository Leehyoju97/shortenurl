package com.leehyoju97.shortenurl.service;

import com.leehyoju97.shortenurl.domain.Url;
import com.leehyoju97.shortenurl.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;

    public String joinShortUrl(String originalUrl) {

        return urlRepository.saveShortUrl(originalUrl).getShortUrl();

    }

    public String redirectUrl(String shortUrl) {

        return urlRepository.redirectUrl(shortUrl).getOriginalUrl();

    }

    public List<Url> listUrl() {
        return urlRepository.listUrl();
    }


}
