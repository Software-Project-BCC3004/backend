package br.es.pews.back.controller;

import br.es.pews.back.dto.LoginDTO;
import br.es.pews.back.services.AuthProfissionalServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/profissional")
public class AuthProfissionalController {

    @Autowired
    private AuthProfissionalServices authServices;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        String token = authServices.login(loginDTO);
        return ResponseEntity.ok(token);
    }
}
