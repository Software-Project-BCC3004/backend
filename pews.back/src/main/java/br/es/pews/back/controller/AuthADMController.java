package br.es.pews.back.controller;

import br.es.pews.back.dto.LoginDTO;
import br.es.pews.back.services.AuthADMServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/adm")
public class AuthADMController {

    @Autowired
    private AuthADMServices authADMServices;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        String token = authADMServices.login(loginDTO);
        return ResponseEntity.ok(token);
    }
}
