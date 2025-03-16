package com.sigorzav.singmate.song.controller;

import com.sigorzav.singmate.api.response.ApiResponse;
import com.sigorzav.singmate.song.service.SongService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("song/")
@RequiredArgsConstructor
@Tag(name = "SongController", description="노래 관련 기능 API")
public class SongController {

    private final SongService songService;

    @GetMapping("search-list")
    @ResponseBody
    @Operation(summary = "노래 검색", description = "가수나, 곡의 이름 등으로 노래를 검색합니다.", tags = {"노래 관리"})
    public ApiResponse<Object> searchList(@RequestParam String query) {
        return songService.songSearchList(query);
    }
}
