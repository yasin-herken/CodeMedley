package com.codemedley.orderservice.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

@Configuration
@EnableMethodSecurity
public class ResourceServerConfig {

    private static final String SECURED_PATTERN_WRITE = "/orders/**";
    private static final String SECURED_PATTERN_READ = "/orders/{id}";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, SECURED_PATTERN_WRITE)
                        .hasAuthority("SCOPE_write")
                        .requestMatchers(HttpMethod.GET, SECURED_PATTERN_READ)
                        .hasAuthority("SCOPE_read")
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .opaqueToken(opaqueTokenConfigurer -> {
                            opaqueTokenConfigurer
                                    .introspectionUri("http://localhost:8085/oauth2/introspect")
                                    .introspectionClientCredentials("orderprocessingapp", "orderprocessingappsecret");
                        })
                );

        return http.build();
    }
}
