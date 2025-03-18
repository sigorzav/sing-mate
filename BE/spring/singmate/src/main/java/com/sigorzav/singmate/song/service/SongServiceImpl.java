package com.sigorzav.singmate.song.service;

import com.sigorzav.singmate.api.response.ApiResponse;
import com.sigorzav.singmate.api.service.SpotifyService;
import com.sigorzav.singmate.song.dto.SongDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class SongServiceImpl implements SongService {

    private final SpotifyService spotifyService;

    /**
     * 노래 검색
     */
    @Override
    public ApiResponse<Object> searchSongs(String query) {
        log.info("songSearch request processing. Query: {}", query);

        try {
            List<SongDTO> songs = spotifyService.searchSongs(query);
            return new ApiResponse<>(songs);
        } catch (Exception e) {
            log.error("노래 검색 오류 발생", e);
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, "노래 검색 중 오류가 발생했습니다.");
        }
    }
}
