package com.leehyoju97.shortenurl.repository;

import com.leehyoju97.shortenurl.domain.Url;
import com.leehyoju97.shortenurl.util.Sha256;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UrlRepository {

    private static final Map<String, Url> shortUrlStore = new ConcurrentHashMap<>();

    public Url saveShortUrl(String originalUrl) {
        int position = 0;
        int requestCount = 0;
        String shortUrl = urlDuplicateValidate(originalUrl, position);

        Url saveUrl = Url.builder()
                .shortUrl(shortUrl)
                .originalUrl(originalUrl)
                .requestCount(requestCount)
                .build();

        shortUrlStore.put(saveUrl.getShortUrl(), saveUrl);

        return saveUrl;

    }

    private boolean isExistByShortUrl(String originalUrl) {
        return shortUrlStore.containsKey(originalUrl);
    }

    private String urlDuplicateValidate(String originalUrl, int position) {
        String shortUrl = Sha256.encode(originalUrl, position);

        while (isExistByShortUrl(shortUrl)) {
            shortUrl = Sha256.encode(originalUrl, ++position);
        }

        return shortUrl;
    }

    public Url redirectUrl(String shortUrl) {

        Url url = shortUrlStore.get(shortUrl);

        Url urlInfo = Url.builder()
                .shortUrl(url.getShortUrl())
                .originalUrl(url.getOriginalUrl())
                .requestCount(url.getRequestCount() + 1)
                .build();

        shortUrlStore.put(urlInfo.getShortUrl(), urlInfo);

        return shortUrlStore.get(urlInfo.getShortUrl());
    }

    public List<Url> listUrl() {
        return new ArrayList<>(shortUrlStore.values());
    }
}
