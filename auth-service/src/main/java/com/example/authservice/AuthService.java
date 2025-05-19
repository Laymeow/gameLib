package com.example.authservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;
    private final UserServiceClient userServiceClient;

    public AuthService(PasswordEncoder passwordEncoder,
                       JwtEncoder jwtEncoder,
                       UserServiceClient userServiceClient) {
        this.passwordEncoder = passwordEncoder;
        this.jwtEncoder = jwtEncoder;
        this.userServiceClient = userServiceClient;
    }

    public String login(String username, String rawPassword) {
        User user = userServiceClient.getUserByUsername(username);
        if (user == null || !passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("gamelib")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
                .subject(user.getUsername())
                .claim("userId", user.getId())
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}