package biblioteca.biblio.spring.sis.controllers;

import biblioteca.biblio.spring.sis.persistence.entities.RecursoDigitalEntity;
import biblioteca.biblio.spring.sis.services.impl.RecursoDigitalService;
import biblioteca.biblio.spring.sis.services.models.dtos.ResponseBiblioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recursoDigital")
public class RecursoDigitalController {
    @Autowired
    private RecursoDigitalService recursoDigitalService;

    @PreAuthorize("hasAuthority('ROLE_BIBLIOTECARIO')")
    @PostMapping("/addRecursoDigital")
    ResponseEntity<ResponseBiblioDTO> addRecursoDigital(@RequestBody RecursoDigitalEntity recursoDigitalEntity){
        return new ResponseEntity<>(recursoDigitalService.addRecursoDigital(recursoDigitalEntity), HttpStatus.OK);
    }
}
