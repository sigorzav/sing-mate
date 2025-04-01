package com.sigorzav.singmate.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GeniusService {

    @Value("${GENIUS_API_TOKEN}")
    private String geniusApiToken;

    private static final String GENIUS_API_URL = "https://api.genius.com";
    private final RestTemplate restTemplate;

    // HTTP 요청 객체 생성
    private HttpEntity<String> createHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + geniusApiToken);
        return new HttpEntity<>(headers);
    }

    public String searchSongsFromGenius(String title, String artist) {
        String query = title + " " + artist;
        String url = GENIUS_API_URL + "/search?q=" + query;

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, createHttpEntity(), String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to fetch song detail from Genius API.");
        }
    }

    public String getLyricsFromGenius(String songId) {
        String url = GENIUS_API_URL + "/songs/" + songId;

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, createHttpEntity(), String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to fetch lyrics from Genius API.");
        }
    }
}
