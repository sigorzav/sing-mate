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
public class MusicMatchServiceMockTest {

    @Mock
    private MusixMatchService musixMatchService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void testSearchTracksFromMusixMatch() {
        String query = "Too Bad";

        when(musixMatchService.searchTracksFromMusixMatch(query)).thenReturn("Z8j_XEn9b_8");

        String result = musixMatchService.searchTracksFromMusixMatch(query);

        assertEquals("Z8j_XEn9b_8", result);
        verify(musixMatchService, times(1)).searchTracksFromMusixMatch(query);
    }
}
