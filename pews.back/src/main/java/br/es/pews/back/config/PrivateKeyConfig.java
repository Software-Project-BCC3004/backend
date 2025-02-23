package br.es.pews.back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

@Configuration
public class PrivateKeyConfig {

    @Bean
    public PrivateKey getPrivateKey() throws Exception {
        Path path = Paths.get(System.getenv("JWT_PRIVATE_KEY_PATH"));
        byte[] keyBytes = Files.readAllBytes(path);
        String privateKeyPem = new String(keyBytes)
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll(".replaceAll(\"\\\\s+\", \"\");", "");
        byte[] decodeKey = java.util.Base64.getDecoder().decode(privateKeyPem);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodeKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }
}
