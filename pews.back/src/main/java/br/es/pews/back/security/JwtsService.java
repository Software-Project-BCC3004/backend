package br.es.pews.back.security;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.Date;

@Service
public class JwtsService {

    private final PrivateKey privateKey;

    @Autowired
    public JwtsService(@Qualifier("getPrivateKey") PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public String generateToken(String subject) {
        try {
            return Jwts.builder()
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(privateKey)
                .compact();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar token JWT", e);
        }
    }
}
