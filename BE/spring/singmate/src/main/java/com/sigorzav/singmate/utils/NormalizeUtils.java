package com.sigorzav.singmate.utils;

import org.apache.commons.text.similarity.LevenshteinDistance;

public class NormalizeUtils {

    // 문자열 정규화
    public static String normalize(String text) {
        if (text == null || text.isEmpty()) return "";

        // 1. 대소문자 통일
        text = text.toLowerCase();

        // 2. 양 끝 공백 제거 및 중간 공백을 단일 공백으로 변환
        text = text.trim().replaceAll("\\s+", "");

        // 3. 특수문자 제거 (영어, 숫자, 한글만 남김)
        text = text.replaceAll("[^a-zA-Z0-9가-힣]", "");

        return text;
    }

    // 유사도 점수 계산 (Levenshtein Distance 방식)
    public static double calculateSimilarity(String s1, String s2) {
        LevenshteinDistance distance = new LevenshteinDistance();
        int maxLen = Math.max(s1.length(), s2.length());
        int editDistance = distance.apply(s1, s2);

        // 유사도가 1.0에 가까울수록 더 유사함
        return 1.0 - (double) editDistance / maxLen;
    }

    // 문자열이 포함 관계인지 확인 (전처리 포함)
    public static boolean contains(String text, String keyword) {
        String normalizedText = normalize(text);
        String normalizedKeyword = normalize(keyword);

        return normalizedText.contains(normalizedKeyword);
    }
}
