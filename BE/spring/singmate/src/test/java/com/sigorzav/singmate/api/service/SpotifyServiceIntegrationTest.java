package com.sigorzav.singmate.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sigorzav.singmate.song.dto.SongDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
public class SpotifyServiceIntegrationTest {

    @Autowired
    private SpotifyService spotifyService;

    @Test
    void testSearchSongsFromSpotify_RealApiCall() throws JsonProcessingException {
        String query = "좋은날";

        // When
        List<SongDTO> songs = spotifyService.searchSongsFromSpotify(query);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonSongs = objectMapper.writeValueAsString(songs);

        log.info("testSearchSongsFromSpotify_RealApiCall: {}", jsonSongs);

        // Then
        assertNotNull(songs);
        assertFalse(songs.isEmpty());
    }
}
