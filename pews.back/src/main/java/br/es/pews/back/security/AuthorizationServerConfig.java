package br.es.pews.back.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.web.SecurityFilterChain;

import java.util.UUID;

@Configuration
public class AuthorizationServerConfig {

    @Bean
    SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/pacientes/consultar/**").hasAuthority("SCOPE_paciente:read")
                        .requestMatchers("/pacientes/criar").hasAuthority("SCOPE_paciente:write")
                        .requestMatchers("/pacientes/atualizar/**").hasAuthority("SCOPE_paciente:write")
                        .requestMatchers("/pacientes/deletar/**").hasAuthority("SCOPE_paciente:write")
                        .requestMatchers("/profissional/consultar/**").hasAuthority("SCOPE_profissional:read")
                        .requestMatchers("/profissional/criar").hasAuthority("SCOPE_profissional:write")
                        .requestMatchers("/profissional/atualizar/**").hasAuthority("SCOPE_profissional:write")
                        .requestMatchers("/profissional/deletar/**").hasAuthority("SCOPE_profissional:write")
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }

    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("client_id")
                .clientSecret(passwordEncoder().encode("client_secret"))
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri("http://localhost:8080/login/oauth2/code/my_client")
                .scope("paciente:read")
                .scope("paciente:write")
                .scope("profissional:read")
                .scope("profissional:write")
                .build();

        return new InMemoryRegisteredClientRepository(registeredClient);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
