
package br.es.pews.back.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.UUID;

@Configuration
public class AuthorizationServerConfig {

    @Bean
    SecurityFilterChain authorizationSecurityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/oauth2/**") // Aplica esta configuração apenas para endpoints de OAuth2
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        return http.build();
    }



    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        RegisteredClient adminClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("admin_client")
                .clientSecret(passwordEncoder().encode("admin_secret"))
                .authorizationGrantTypes(grantTypes -> {
                    grantTypes.add(AuthorizationGrantType.CLIENT_CREDENTIALS);
                    grantTypes.add(AuthorizationGrantType.REFRESH_TOKEN);
                })
                .scopes(scopes -> {  // O correto é definir escopos, não roles
                    scopes.add("admin");
                })
                .build();

        RegisteredClient profissionalClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("profissional_client")
                .clientSecret(passwordEncoder().encode("profissional_secret"))
                .authorizationGrantTypes(grantTypes -> {
                    grantTypes.add(AuthorizationGrantType.CLIENT_CREDENTIALS);
                    grantTypes.add(AuthorizationGrantType.REFRESH_TOKEN);
                })
                .scope("ROLE_PROFISSIONAL")
                .build();

        return new InMemoryRegisteredClientRepository(List.of(adminClient, profissionalClient));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder()
                .issuer("http://localhost:8080")
                .build();
    }
}
