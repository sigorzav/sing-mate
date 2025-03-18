package com.sigorzav.singmate.song.service;

import com.sigorzav.singmate.api.response.ApiResponse;

public interface SongService {

    /**
     * 노래 검색
     */
    ApiResponse<Object> searchSongs(String query);
}
