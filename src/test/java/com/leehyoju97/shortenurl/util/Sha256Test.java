package com.leehyoju97.shortenurl.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Sha256Test {

    @DisplayName("SHA로 8글자의 랜덤 문자가 나오는지 테스트")
    @Test
    public void SHA_알고리즘테스트() {
        assertThat(Sha256.encode("https://www.google.com/search?q=bye&oq=bye&aqs=chrome.0.69i59.849j0j7&sourceid=chrome&ie=UTF-8", 0)).hasSize(8);
    }
}