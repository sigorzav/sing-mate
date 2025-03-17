package com.sigorzav.singmate.song.service;

import com.sigorzav.singmate.api.response.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public interface SongService {

    /**
     * 노래 검색
     */
    ApiResponse<Object> searchSongs(String query);
}
