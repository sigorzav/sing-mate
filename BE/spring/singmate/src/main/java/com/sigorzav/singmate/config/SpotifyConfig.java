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

@Slf4j
@Component
public class SpotifyConfig {

    @Getter
    private final SpotifyApi spotifyApi;
    private final ClientCredentialsRequest clientCredentialsRequest;

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

            log.info("Access Token retrieved successfully! Expires in: {} seconds", clientCredentials.getExpiresIn());
        } catch (IOException | SpotifyWebApiException | RuntimeException | ParseException e) {
            log.error("Error while retrieving access token: ", e);
        }

    }

}
