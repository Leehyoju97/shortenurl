package com.leehyoju97.shortenurl.repository;

import com.leehyoju97.shortenurl.domain.Url;
import com.leehyoju97.shortenurl.util.Sha256;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UrlRepository {

    private static final Map<Integer, Url> shortUrlStore = new ConcurrentHashMap<>();

    public Url saveShortUrl(String originalUrl) {
        int position = 0;
        int requestCount = 0;
        String shortUrl = Sha256.encode(originalUrl, position);

        while(isExistByShortUrl(originalUrl)) {
            shortUrl = Sha256.encode(originalUrl, ++position);
        }

        Url saveUrl = Url.builder()
                .id(position)
                .originalUrl(originalUrl)
                .shortUrl(shortUrl)
                .count(requestCount)
                .build();

        shortUrlStore.put(position, saveUrl);

        return saveUrl;

    }

    private boolean isExistByShortUrl(String originalUrl) {
        return shortUrlStore.containsKey(originalUrl);
    }
}
