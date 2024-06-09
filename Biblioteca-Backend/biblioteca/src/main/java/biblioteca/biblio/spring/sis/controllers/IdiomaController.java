package biblioteca.biblio.spring.sis.controllers;


import biblioteca.biblio.spring.sis.persistence.entities.EditorialEntity;
import biblioteca.biblio.spring.sis.persistence.entities.IdiomaEntity;
import biblioteca.biblio.spring.sis.services.impl.IdiomaService;
import biblioteca.biblio.spring.sis.services.models.dtos.ResponseBiblioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/idioma")
public class IdiomaController {

    @Autowired
    private IdiomaService idiomaService;

    @PreAuthorize("hasAuthority('ROLE_BIBLIOTECARIO')")
    @PostMapping("/addIdioma")
    ResponseEntity<ResponseBiblioDTO> addIdioma(@RequestBody IdiomaEntity idiomaEntity){
        return new ResponseEntity<>(idiomaService.addIdioma(idiomaEntity), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_BIBLIOTECARIO')")
    @DeleteMapping("/deleteIdioma")
    ResponseEntity<ResponseBiblioDTO> deleteIdioma(@RequestBody IdiomaEntity idiomaEntity){
        return new ResponseEntity<>(idiomaService.delteIdioma(idiomaEntity), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_BIBLIOTECARIO')")
    @GetMapping("/getAllIdioma")
    public ResponseEntity<List<IdiomaEntity>> getAllIdioma() {
        List<IdiomaEntity> recursos = idiomaService.getAllIdioma();
        return new ResponseEntity<>(recursos, HttpStatus.OK);
    }
}
