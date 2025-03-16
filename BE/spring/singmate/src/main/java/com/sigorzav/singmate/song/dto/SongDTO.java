package com.sigorzav.singmate.song.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SongDTO {

    // 노래 제목
    private String songTitle;

    // 아티스트
    private String songArtist;

    // 노래 장르
    private List<String> songGenre;

    // 노래 발매일자
    private String songReleaseDate;

    // 노래 썸네일 URL
    private String songThumbnailUrl;

    // 노래 유튜브 URL
    private String songYoutubeUrl;

    // 가사
    private String songLyrics;

}
