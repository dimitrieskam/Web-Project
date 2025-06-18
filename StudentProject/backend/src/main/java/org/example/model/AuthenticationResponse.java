package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("message")
    private String message;

    @JsonProperty("role")
    private String role;

    @JsonProperty("username")
    private String username;

    @JsonProperty("user_id")
    private Long userId;

    public AuthenticationResponse(String accessToken, String refreshToken, String message) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.message = message;
    }

    public AuthenticationResponse(String accessToken, String refreshToken, String message, String role, String username, Long userId) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.message = message;
        this.role = role;
        this.username = username;
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getMessage() {
        return message;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public Long getUserId() {
        return userId;
    }
}
