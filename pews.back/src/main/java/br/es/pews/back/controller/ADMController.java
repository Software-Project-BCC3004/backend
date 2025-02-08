package br.es.pews.back.controller;

import br.es.pews.back.models.ADM;
import br.es.pews.back.services.ADMServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adm")
public class ADMController {

    @Autowired
    private ADMServices admServices;

    @PostMapping("/criar")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ADM> createADM(@RequestBody ADM adm) {
        return admServices.save(adm);
    }
}
