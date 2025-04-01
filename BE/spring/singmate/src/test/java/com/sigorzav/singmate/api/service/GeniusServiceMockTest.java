package com.sigorzav.singmate.api.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Slf4j
public class GeniusServiceMockTest {

    @Mock
    private GeniusService geniusService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this); // Mock 객체 초기화
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close(); // 리소스 닫기
    }

    @Test
    public void testSearchSongFromGenius() throws Exception {
        String title = "Too Bad";
        String artist = "G-Dragon";

        when(geniusService.searchSongsFromGenius(title, artist)).thenReturn("Z8j_XEn9b_8");

        String result = geniusService.searchSongsFromGenius(title, artist);


        assertEquals("Z8j_XEn9b_8", result);
        verify(geniusService, times(1)).searchSongsFromGenius(title, artist);
    }

    @Test
    public void testGetLyricsFromGenius() throws Exception {
        String songId = "123456789";

        when(geniusService.getLyricsFromGenius(songId)).thenReturn("Z8j_XEn9b_8");

        String result = geniusService.getLyricsFromGenius(songId);

        assertEquals("Z8j_XEn9b_8", result);
        verify(geniusService, times(1)).getLyricsFromGenius(songId);
    }
}
