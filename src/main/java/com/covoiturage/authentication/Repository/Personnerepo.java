package com.covoiturage.authentication.Repository;

import com.covoiturage.authentication.Models.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Personnerepo extends JpaRepository<Personne, Long> {

    Optional<Personne> findByUsername(String username);
}
