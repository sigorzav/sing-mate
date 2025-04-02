package com.sigorzav.singmate.api.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sigorzav.singmate.api.config.ApiConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class MusixMatchService {

    @Value("${MUSICMATCH_API_KEY}")
    private String musixMatchApiKey;

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    /**
     * 노래 검색
     */
    public String searchTracksFromMusixMatch(String query) {
        Mono<String> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host(ApiConstants.MUSICMATCH_BASE_URL)
                        .path("/ws/1.1/track.search")
                        .queryParam("q", query)
                        .queryParam("apikey", musixMatchApiKey)
                        .queryParam("page", 1)
                        .queryParam("page_size", 50)                    // Free PLAN :: 최대 50개
                        .queryParam("s_track_release_date", "desc")     // 최신 노래 순으로 조회
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .map(this::decodeUnicode);

        return response.block();
    }

    /**
     * 가사 검색
     */
    public String matcherLyricsGetFromMusixMatch(String title, String artist) {
        Mono<String> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host(ApiConstants.MUSICMATCH_BASE_URL)
                        .path("/ws/1.1/matcher.lyrics.get")
                        .queryParam("q_track", title)
                        .queryParam("q_artist", artist)
                        .queryParam("apikey", musixMatchApiKey)
                        .build())
                .retrieve()
                .bodyToMono(String.class);

        return response.block();
    }

    /**
     * 유니코드 디코딩
     */
    private String decodeUnicode(String response) {
        try {
            JsonNode jsonNode = objectMapper.readTree(response);
            return jsonNode.toPrettyString();
        } catch (Exception e) {
            log.error("Error decoding unicode", e);
            return response;
        }
    }
}
