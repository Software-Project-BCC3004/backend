package br.es.pews.back.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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
    SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        // ADMIN pode tudo
                        .requestMatchers("/adm/**").hasRole("ADMIN")
                        .requestMatchers("/auth/adm/**").hasRole("ADMIN")

                        // PROFISSIONAL pode gerenciar Pacientes e PEWS
                        .requestMatchers("/pacientes/**").hasRole("PROFISSIONAL")
                        .requestMatchers("/avalicao/pews/**").hasRole("PROFISSIONAL")
                        .requestMatchers("/auth/profissional/login").hasRole("PROFISSIONAL")

                        // Qualquer outra requisição precisa estar autenticada
                        .anyRequest().authenticated()
                )
                .securityMatcher("/oauth");

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
                .scope("ROLE_ADMIN")
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
                .issuer("http://localhost:9000")
                .build();
    }
}
