package br.es.pews.back.security;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
@Slf4j
public class SecurityConfig {

    @Value("${security.jwt.public-key-path}")
    private String publicKeyPath;

    @PostConstruct
    public void init() {
        if (publicKeyPath == null || publicKeyPath.isBlank()) {
            log.error("🚨 ERRO: Caminho da chave pública não está definido corretamente!");
            throw new IllegalStateException("Caminho da chave pública JWT não configurado.");
        }
        log.info("🔍 Caminho da chave pública configurado: {}", publicKeyPath);
    }

    @Bean
    public PublicKey publicKey() {
        return loadPublicKey(publicKeyPath);
    }

    private PublicKey loadPublicKey(String path) {
        try {
            log.info("🔍 Tentando carregar a chave pública de: {}", path);
            String keyContent = readKeyFile(path);
            byte[] keyBytes = Base64.getDecoder().decode(keyContent);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            return KeyFactory.getInstance("RSA").generatePublic(keySpec);
        } catch (Exception e) {
            log.error("🚨 ERRO ao carregar a chave pública de {}", path, e);
            throw new IllegalStateException("Erro ao carregar chave pública", e);
        }
    }

    private String readKeyFile(String path) throws IOException {
        Path filePath = Path.of(path);
        if (!Files.exists(filePath)) {
            log.error("🚨 ERRO: Arquivo da chave pública não encontrado em {}", path);
            throw new IllegalStateException("Arquivo da chave pública não encontrado: " + path);
        }
        return Files.readString(filePath)
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");
    }
}
