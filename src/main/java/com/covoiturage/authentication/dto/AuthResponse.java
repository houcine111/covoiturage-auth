package com.covoiturage.authentication.dto;

import com.covoiturage.authentication.Models.Personne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String token;
    private UserInfo user;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserInfo {
        private Long id;
        private String username;
        private String email;
        private String nom;
        private String prenom;
        private Personne.Role role;
        private String numeroTelephone;

        public static UserInfo fromPersonne(Personne personne) {
            return UserInfo.builder()
                    .id(personne.getId())
                    .username(personne.getUsername())
                    .email(personne.getEmail())
                    .nom(personne.getNom())
                    .prenom(personne.getPrenom())
                    .role(personne.getRole())
                    .numeroTelephone(personne.getNumeroTelephone())
                    .build();
        }
    }
} 