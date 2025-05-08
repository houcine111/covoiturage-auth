package com.covoiturage.authentication;

import com.covoiturage.authentication.Repository.Personnerepo;
import com.covoiturage.authentication.Services.Servicemanager;
import com.covoiturage.authentication.dto.AuthResponse;
import com.covoiturage.authentication.security.jwt;
import com.covoiturage.authentication.Models.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class Controller {

    @Autowired
    private Personnerepo personnerepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private jwt jwtService;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Personne personne) {
        // Check if user already exists
        if (personnerepo.findByUsername(personne.getUsername()).isPresent()) {
            return ResponseEntity
                .badRequest()
                .body("Username already registered");
        }

        // Set default role if not specified
        if (personne.getRole() == null) {
            personne.setRole(Personne.Role.USER);
        }

        // Encode password and save user
        personne.setPassword(passwordEncoder.encode(personne.getPassword()));
        Personne savedPersonne = personnerepo.save(personne);

        // Generate token and response
        String token = jwtService.generateJwtToken(savedPersonne.getUsername());
        AuthResponse response = AuthResponse.builder()
            .token(token)
            .user(AuthResponse.UserInfo.fromPersonne(savedPersonne))
            .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        try {
            String username = loginRequest.get("username");
            String password = loginRequest.get("password");

            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );

            // If authentication successful, generate token
            String token = jwtService.generateJwtToken(username);
            
            // Get user details
            Personne personne = personnerepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

            AuthResponse response = AuthResponse.builder()
                .token(token)
                .user(AuthResponse.UserInfo.fromPersonne(personne))
                .build();

            return ResponseEntity.ok(response);
            
        } catch (AuthenticationException e) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Invalid username or password");
        }
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyToken(@RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            if (jwtService.validateJwtToken(token)) {
                String username = jwtService.getUsernameFromToken(token);
                Personne personne = personnerepo.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

                AuthResponse response = AuthResponse.builder()
                    .token(token)
                    .user(AuthResponse.UserInfo.fromPersonne(personne))
                    .build();

                return ResponseEntity.ok(response);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
    }
}
