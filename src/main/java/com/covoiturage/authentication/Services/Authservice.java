package com.covoiturage.authentication.Services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


public interface Authservice  {
public void login();
public void logout();
public void adduser();
}
