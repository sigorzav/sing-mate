package com.sigorzav.singmate.api.service;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.sigorzav.singmate.config.YoutubeConfig;
import com.sigorzav.singmate.utils.NormalizeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class YoutubeService {

    private final YouTube youtube;
    private final YoutubeConfig youtubeConfig;

    public String searchVideo(String title, String artist) throws IOException {

        // YouTube Search API 동영상 검색 요청 객체 생성
        YouTube.Search.List search = youtube.search().list(Collections.singletonList("id,snippet"));

        // API KEY 설정
        search.setKey(youtubeConfig.getYoutubeApiKey());

        // 조회 Q : Title + Artist
        String query = title + " " + artist;
        search.setQ(query);
        
        // Video & 최대 10개
        search.setType(Collections.singletonList("video"));
        search.setMaxResults(10L);

        // API 호출
        SearchListResponse response = search.execute();
        List<SearchResult> results = response.getItems();

        if (results == null || results.isEmpty()) {
            return null;
        }
        
        // 결과 필터링
        Optional<SearchResult> matched = results.stream()
                .filter(item -> {
                    String videoTitle = item.getSnippet().getTitle().trim().toLowerCase();

                    // 정규화 비교
                    boolean isTitleMatch = NormalizeUtils.contains(videoTitle, title);
                    boolean isArtistMatch = NormalizeUtils.contains(videoTitle, artist);

                    if (isTitleMatch && isArtistMatch) {
                        return true;
                    }

                    // 유사도 점수 계산
                    double similarity = NormalizeUtils.calculateSimilarity(
                            NormalizeUtils.normalize(videoTitle),
                            NormalizeUtils.normalize(title + artist)
                    );
                    return similarity > 0.8;
                })
                .findFirst();

        return matched.map(result -> result.getId().getVideoId()).orElse(null);
    }
}
