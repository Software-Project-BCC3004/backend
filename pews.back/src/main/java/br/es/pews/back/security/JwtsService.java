package br.es.pews.back.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.Date;
import java.util.List;

@Service
public class JwtsService {

    private final PrivateKey privateKey;

    @Autowired
    public JwtsService(@Qualifier("getPrivateKey") PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public String generateToken(String subject, List<String> roles) {
        try {
            return Jwts.builder()
                    .subject(subject)
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                    .claim("roles", roles)
                    .signWith(privateKey, SignatureAlgorithm.RS256)
                    .compact();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar token JWT", e);
        }
    }
}
