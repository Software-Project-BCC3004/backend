package br.es.pews.back.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.Date;

@Service
public class JwtsService {

    private final PrivateKey privateKey;

    @Autowired
    public JwtsService(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public String generateToken(String subject) {
        return Jwts.builder()
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }
}
