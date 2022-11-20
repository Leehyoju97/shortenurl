package com.leehyoju97.shortenurl.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Url {

    private String shortUrl;
    private String originalUrl;
    private int requestCount;

}
