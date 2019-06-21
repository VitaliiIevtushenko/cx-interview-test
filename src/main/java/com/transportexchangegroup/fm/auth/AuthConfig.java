package com.transportexchangegroup.fm.auth;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ua.ardas.jwt.AuthConstProvider;
import ua.ardas.jwt.JwtService;
import ua.ardas.jwt.KeyStorage;
import ua.ardas.jwt.RsaKeyStorageBuilder;

@Configuration
@EnableConfigurationProperties({AuthProperties.class})
public class AuthConfig {
    private final AuthProperties authProperties;

    public AuthConfig(AuthProperties authProperties) {
        this.authProperties = authProperties;
    }

    @Bean
    @Profile("!test")
    public JwtFilter jwtFilter(JwtService jwtService, AuthConstProvider authConstProvider) {
        return new JwtFilter(jwtService, authConstProvider);
    }

    @Bean
    public KeyStorage authKeyStorage() {
        return new RsaKeyStorageBuilder()
                .publicKey(authProperties.getPublicKeyPath())
                .build();
    }

    @Bean
    @Profile("!test")
    public JwtService jwtService(KeyStorage authKeyStorage) {
        return new JwtService(authKeyStorage, authProperties.getTokenExpireHours());
    }
}