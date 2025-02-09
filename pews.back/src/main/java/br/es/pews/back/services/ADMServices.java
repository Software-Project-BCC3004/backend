package br.es.pews.back.services;

import br.es.pews.back.dto.AdmDTO;
import br.es.pews.back.models.ADM;
import br.es.pews.back.repository.ADMRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@Service
public class ADMServices {

    @Autowired
    private final ADMRepository admRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public ADMServices(ADMRepository admRepository, PasswordEncoder passwordEncoder) {
        this.admRepository = admRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<ADM> save(@RequestBody ADM adm) {
        try {
            adm.setPassword(passwordEncoder.encode(adm.getPassword()));
            ADM admSaved = admRepository.save(adm);
            return ResponseEntity.ok(admSaved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<ADM> update(@PathVariable Long id, @RequestBody AdmDTO admDTO) {
        ADM admUpdated = admRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ADM with ID: " + id + " not found"));

        modelMapper.map(admDTO, admUpdated);

        admRepository.save(admUpdated);
        return ResponseEntity.ok(admUpdated);
    }

    public ResponseEntity<ADM> delete(@PathVariable Long id) {
        ADM admDelete = admRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ADM with ID: " + id + " not found"));
        admRepository.delete(admDelete);
        return ResponseEntity.ok(admDelete);
    }
}
