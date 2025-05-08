package com.covoiturage.authentication.Services;

import com.covoiturage.authentication.Models.Personne;
import com.covoiturage.authentication.Repository.Personnerepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Data
@Service
public class Servicemanager implements UserDetailsService {
    @Autowired
    private Personnerepo personnerepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Personne> p = personnerepo.findByUsername(username);
        if (p.isPresent()) {
            var personne = p.get();
            return User.builder()
                    .username(personne.getUsername())
                    .password(personne.getPassword())
                    .authorities(Collections.singletonList(
                        new SimpleGrantedAuthority("ROLE_" + personne.getRole().name())
                    ))
                    .build();
        } else throw new UsernameNotFoundException(username);
    }
}
