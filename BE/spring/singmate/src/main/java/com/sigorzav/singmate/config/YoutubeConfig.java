package com.sigorzav.singmate.config;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Getter
@Configuration
public class YoutubeConfig {

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    @Value("${YOUTUBE_API_KEY}")
    private String youtubeApiKey;

    @Bean
    public YouTube youTube() {
        return new YouTube.Builder(
                new NetHttpTransport(),
                JSON_FACTORY,
                request -> {}
        ).setApplicationName("singMate-App").build();
    }
}
