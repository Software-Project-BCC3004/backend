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
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
@Slf4j
public class SecurityConfig {

    @Value("${jwt.private-key-path:#{null}}")
    private String privateKeyPath;

    @Value("${jwt.public-key-path:#{null}}")
    private String publicKeyPath;

    @PostConstruct
    public void init() {
        if (privateKeyPath == null || publicKeyPath == null) {
            log.error("🚨 ERRO: Caminho das chaves não está definido corretamente!");
            throw new IllegalStateException("Caminho das chaves JWT não configurado.");
        }
        log.info("🔍 Caminho da chave privada: {}", privateKeyPath);
        log.info("🔍 Caminho da chave pública: {}", publicKeyPath);
    }

    @Bean
    public PrivateKey privateKey() {
        return loadPrivateKey(privateKeyPath);
    }

    @Bean
    public PublicKey publicKey() {
        return loadPublicKey(publicKeyPath);
    }

    private PrivateKey loadPrivateKey(String path) {
        try {
            log.info("🔍 Carregando chave privada de {}", path);
            String keyContent = readKeyFile(path);
            byte[] keyBytes = Base64.getDecoder().decode(keyContent);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            return KeyFactory.getInstance("RSA").generatePrivate(keySpec);
        } catch (Exception e) {
            log.error("🚨 ERRO ao carregar a chave privada!", e);
            throw new IllegalStateException("Erro ao carregar chave privada", e);
        }
    }

    private PublicKey loadPublicKey(String path) {
        try {
            log.info("🔍 Carregando chave pública de {}", path);
            String keyContent = readKeyFile(path);
            byte[] keyBytes = Base64.getDecoder().decode(keyContent);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            return KeyFactory.getInstance("RSA").generatePublic(keySpec);
        } catch (Exception e) {
            log.error("🚨 ERRO ao carregar a chave pública!", e);
            throw new IllegalStateException("Erro ao carregar chave pública", e);
        }
    }

    private String readKeyFile(String path) throws IOException {
        Path filePath = Path.of(path);
        if (!Files.exists(filePath)) {
            log.error("🚨 ERRO: Arquivo não encontrado em {}", path);
            throw new IllegalStateException("Arquivo da chave não encontrado: " + path);
        }
        return Files.readString(filePath)
                .replaceAll("-----BEGIN (PRIVATE|PUBLIC) KEY-----", "")
                .replaceAll("-----END (PRIVATE|PUBLIC) KEY-----", "")
                .replaceAll("\\s+", "");
    }
}
