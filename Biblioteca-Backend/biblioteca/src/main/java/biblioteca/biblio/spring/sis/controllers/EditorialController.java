package biblioteca.biblio.spring.sis.controllers;


import biblioteca.biblio.spring.sis.persistence.entities.CategoriaRecursoEntity;
import biblioteca.biblio.spring.sis.persistence.entities.EditorialEntity;
import biblioteca.biblio.spring.sis.services.impl.EditorialService;
import biblioteca.biblio.spring.sis.services.models.dtos.ResponseBiblioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editorial")
public class EditorialController {

    @Autowired
    private EditorialService editorialService;

    @PreAuthorize("hasAuthority('ROLE_BIBLIOTECARIO')")
    @PostMapping("/addeditorial")
    ResponseEntity<ResponseBiblioDTO> addEditorial(@RequestBody EditorialEntity editorialEntity){

        return new ResponseEntity<>(editorialService.addEditorial(editorialEntity), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_BIBLIOTECARIO')")
    @GetMapping("/getAllEditorial")
    public ResponseEntity<List<EditorialEntity>> getAllEditorial() {
        List<EditorialEntity> recursos = editorialService.getAllEditorial();
        return new ResponseEntity<>(recursos, HttpStatus.OK);
    }
}
