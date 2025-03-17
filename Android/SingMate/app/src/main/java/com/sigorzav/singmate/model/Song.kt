package com.sigorzav.singmate.model

data class Song (
    val songTitle: String,              // 노래 제목
    val songArtist: String,             // 아티스트
    val songGenre: List<String>,        // 노래 장르
    val songReleaseDate: String,        // 노래 발매일자
    val songThumbnailUrl: String,       // 노래 썸네일 URL
    val songYoutubeUrl: String,         // 노래 유튜브 URL
    val songLyrics: String              // 가사
)