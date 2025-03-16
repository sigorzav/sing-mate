package com.sigorzav.singmate.api.service;

import com.sigorzav.singmate.config.SpotifyConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpotifyTestService {

    private final SpotifyConfig spotifyConfig;

    public List<String> availableGenreSeeds() {
        SpotifyApi spotifyApi = spotifyConfig.getSpotifyApi();

        try {
            String[] genreSeeds = spotifyApi.getAvailableGenreSeeds().build().execute();
            return Arrays.asList(genreSeeds);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
