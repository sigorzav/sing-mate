package com.sigorzav.singmate.api.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
public class GeniusServiceIntegrationTest {

    @Autowired
    GeniusService geniusService;

    @Test
    void testSearchSongFromGenius_RealApiCall() {
        String title = "Good Day";
        String artist = "MeloMance";

        // When
        String songsData = geniusService.searchSongsFromGenius(title, artist);
        log.info("testSearchSongFromGenius_RealApiCall: {}", songsData);

        // Then
        assertNotNull(songsData);
        assertFalse(songsData.isEmpty());
    }

    @Test
    void testGetLyricsFromGenius_RealApiCall() {
        String songId = "5565751";

        // When
        String songLyricsData = geniusService.getLyricsFromGenius(songId);
        log.info("testGetLyricsFromGenius_RealApiCall: {}", songLyricsData);

        // Then
        assertNotNull(songLyricsData);
        assertFalse(songLyricsData.isEmpty());
    }
}
