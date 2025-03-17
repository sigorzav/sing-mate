package com.sigorzav.singmate.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

import java.io.IOException;

/**
 * ✅ Spotify API Config
 * - Access Token 생성
 * - 만료여부 체크 및 자동 갱신
 * - Refresh Token 갱신 미생성 (Client Credentials Flow 방식)
 */
@Slf4j
@Component
public class SpotifyConfig {

    @Getter
    private final SpotifyApi spotifyApi;
    private final ClientCredentialsRequest clientCredentialsRequest;

    // Access Token의 만료 시간 (밀리초)
    private long tokenExpirationTime;

    public SpotifyConfig(
            @Value("${spotify.client.id}") String spotifyClientId,
            @Value("${spotify.client.secret}") String spotifyClientSecret
    ) {
        // Spotify API 설정
        this.spotifyApi = new SpotifyApi.Builder()
                .setClientId(spotifyClientId)
                .setClientSecret(spotifyClientSecret)
                .build();

        // Client Credentials Flow 요청 객체 생성
        this.clientCredentialsRequest = spotifyApi.clientCredentials().build();

        log.info("SpotifyConfig initialized with clientId: {}", spotifyClientId);

        // Access Token 요청
        requestAccessToken();
    }

    /**
     * Access Token 요청 및 설정
     */
    private void requestAccessToken() {
        try {
            // Client Credentials Flow를 통해 Access Token 요청
            ClientCredentials clientCredentials = clientCredentialsRequest.execute();

            // Spotify API에 Access Token 설정
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());

            // 유효시간 설정: 1시간
            this.tokenExpirationTime = System.currentTimeMillis() + 3600 * 1000;
            log.info("Access Token retrieved successfully! Expires in: {} seconds", clientCredentials.getExpiresIn());
        } catch (IOException | SpotifyWebApiException | RuntimeException | ParseException e) {
            log.error("Error while retrieving access token: ", e);
        }
    }

    // Access Token 만료 및 갱신
    public void ensureAccessToken() {
        if (isAccessTokenExpired()) {
            requestAccessToken();
        }
    }

    // Access Token 만료 여부 확인
    private boolean isAccessTokenExpired() {
        return System.currentTimeMillis() >= tokenExpirationTime;
    }

}
