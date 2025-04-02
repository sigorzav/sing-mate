package com.sigorzav.singmate.api.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
public class MusicMatchServiceIntegrationTest {

    @Autowired
    MusixMatchService musixMatchService;

    /**
     * 노래 검색
     */
    @Test
    public void testSearchTracksFromMusicMatch_RealApiCall() {
        String query = "Too Bad";

        // When
        String tracksData = musixMatchService.searchTracksFromMusixMatch(query);
        log.info("testSearchTracksFromMusicMatch_RealApiCall: {}", tracksData);

        // Then
        assertNotNull(tracksData);
        assertFalse(tracksData.isEmpty());
    }

    @Test
    public void testMatcherLyricsGetFromMusixMatch_RealApiCall() {
        String title = "TOO BAD (feat. Anderson .Paak)";
        String artist = "G-DRAGON";

        // When
        String lyricsData = musixMatchService.matcherLyricsGetFromMusixMatch(title, artist);
        log.info("testMatcherLyricsGetFromMusixMatch_RealApiCall: {}", lyricsData);

        // Then
        assertNotNull(lyricsData);
        assertFalse(lyricsData.isEmpty());
    }
}
