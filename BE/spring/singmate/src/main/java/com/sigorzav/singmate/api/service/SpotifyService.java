package com.sigorzav.singmate.api.service;

import com.sigorzav.singmate.config.SpotifyConfig;
import com.sigorzav.singmate.song.dto.SongDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpotifyService {

    private final SpotifyConfig spotifyConfig;

    /**
     * 노래 검색
     */
    public List<SongDTO> songSearchList(String query) {
        SpotifyApi spotifyApi = spotifyConfig.getSpotifyApi();
        SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(query).build();

        try {
            Track[] tracks = searchTracksRequest.execute().getItems();
            return generateSongList(tracks);
        } catch (IOException | SpotifyWebApiException | RuntimeException | ParseException e) {
            log.error("Spotify API 검색 오류 발생", e);
            throw new RuntimeException("Spotify API 검색이 실패했습니다.", e);
        }
    }

    /**
     * 노래 검색 - 결과 리스트 생성
     */
    private List<SongDTO> generateSongList(Track[] tracks) {
        //List<Map<String, Object>> songList = new ArrayList<>();
        List<SongDTO> songs = new ArrayList<>();

        for (Track track : tracks) {
            if (track.getName() == null) {
                continue;
            }

            String songTitle = track.getName();
            String songArtist = "Unknown Artist";
            String songReleaseDate = "Unknown Release Date";
            String songThumbnailUrl = "Unknown Image Url";

            if (track.getArtists() != null && track.getArtists().length > 0 && track.getArtists()[0].getName() != null) {
                songArtist = track.getArtists()[0].getName();
            }

            if (track.getAlbum() != null && track.getAlbum().getReleaseDate() != null) {
                songReleaseDate = track.getAlbum().getReleaseDate();
            }

            if (track.getAlbum() != null && track.getAlbum().getImages() != null && track.getAlbum().getImages().length > 0) {
                songThumbnailUrl = track.getAlbum().getImages()[0].getUrl();
            }

            SongDTO songDTO = new SongDTO();
            songDTO.setSongTitle(songTitle);
            songDTO.setSongArtist(songArtist);
            songDTO.setSongReleaseDate(songReleaseDate);
            songDTO.setSongThumbnailUrl(songThumbnailUrl);
            songs.add(songDTO);
        }

        return songs;
    }
}
