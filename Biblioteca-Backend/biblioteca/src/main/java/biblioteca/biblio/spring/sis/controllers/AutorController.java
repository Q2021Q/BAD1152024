package biblioteca.biblio.spring.sis.controllers;


import biblioteca.biblio.spring.sis.persistence.entities.AutorEntity;
import biblioteca.biblio.spring.sis.persistence.entities.IdiomaEntity;
import biblioteca.biblio.spring.sis.services.impl.AutorService;
import biblioteca.biblio.spring.sis.services.models.dtos.ResponseBiblioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PreAuthorize("hasAuthority('ROLE_BIBLIOTECARIO')")
    @PostMapping("/addAutor")
    ResponseEntity<ResponseBiblioDTO> addIdioma(@RequestBody AutorEntity autorEntity){
        return new ResponseEntity<>(autorService.addAutor(autorEntity), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_BIBLIOTECARIO')")
    @GetMapping("/getAllAutor")
    public ResponseEntity<List<AutorEntity>> getAllAutor() {
        List<AutorEntity> recursos = autorService.getAllAutor();
        return new ResponseEntity<>(recursos, HttpStatus.OK);
    }

}
