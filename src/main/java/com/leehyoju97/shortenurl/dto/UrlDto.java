package com.leehyoju97.shortenurl.dto;

import lombok.*;
import javax.validation.constraints.NotBlank;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UrlDto {

    @NotBlank
    private String originalUrl;
}
