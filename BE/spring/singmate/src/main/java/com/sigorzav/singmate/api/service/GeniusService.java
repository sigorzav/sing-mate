package com.sigorzav.singmate.api.service;

import com.sigorzav.singmate.api.config.ApiConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GeniusService {

    @Value("${GENIUS_API_TOKEN}")
    private String geniusApiToken;

    private static final String GENIUS_API_URL = "https://api.genius.com";
    private final WebClient webClient;

    // HTTP 요청 객체 생성
    private HttpEntity<String> createHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + geniusApiToken);
        return new HttpEntity<>(headers);
    }

    public String searchSongsFromGenius(String title, String artist) {
        String query = title + " " + artist;

        Mono<String> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host(ApiConstants.GENIUS_BASE_URL)
                        .path("/search")
                        .queryParam("q", query)
                        .build())
                .header("Authorization", "Bearer " + geniusApiToken)
                .retrieve()
                .bodyToMono(String.class);

        return response.block();
    }

    public String getLyricsFromGenius(String songId) {
        Mono<String> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host(ApiConstants.GENIUS_BASE_URL)
                        .path("/songs/")
                        .queryParam(songId)
                        .build())
                .header("Authorization", "Bearer " + geniusApiToken)
                .retrieve()
                .bodyToMono(String.class);

        return response.block();
    }
}
