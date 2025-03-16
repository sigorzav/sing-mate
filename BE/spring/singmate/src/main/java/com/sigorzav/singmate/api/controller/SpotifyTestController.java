package com.sigorzav.singmate.api.controller;

import com.sigorzav.singmate.api.service.SpotifyTestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("spotify/")
@RequiredArgsConstructor
@Tag(name = "SpotifyTestController", description="Spotify TEST API")
public class SpotifyTestController {

    private final SpotifyTestService spotifyTestService;

    @GetMapping("available-genre-seeds")
    @Operation(summary = "사용 가능한 장르 조회", description = "사용 가능한 장르 조회", tags = {"Spotify"})
    public List<String> availableGenreSeeds() throws Exception {
        return spotifyTestService.availableGenreSeeds();
    }

}
