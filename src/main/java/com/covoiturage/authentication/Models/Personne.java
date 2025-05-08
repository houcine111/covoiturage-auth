package com.covoiturage.authentication.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data

@Table(name = "personnes") // nom de la table en base de données (optionnel mais recommandé)
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "genre")
    private String genre;

    @Column(name = "numero_telephone")
    private String numeroTelephone;




    @Column(name = "role", nullable = false)
    private Role role=Role.USER;
    public enum Role {
        USER,
        ADMIN;
    }
}
