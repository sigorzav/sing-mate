package com.sigorzav.singmate.api.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
public class YoutubeServiceIntegrationTest {

    @Autowired
    private YoutubeService youtubeService;

    @Test
    void testSearchVideoFromYouTube_RealApiCall() throws Exception {
        // Given
        String title = "좋은날";
        String artist = "아이유";

        // When
        String videoId = youtubeService.searchVideoFromYouTube(title, artist);
        log.info("videoId: {}", videoId);

        // Then
        assertNotNull(videoId);
        assertFalse(videoId.isEmpty());
    }
}
