package com.example.userservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String expiration;

    // constructeur optionnel avec un seul paramètre
    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
        this.refreshToken = null;
    }
}
