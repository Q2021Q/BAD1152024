package biblioteca.biblio.spring.sis.controllers;

import biblioteca.biblio.spring.sis.persistence.entities.LibroEntity;
import biblioteca.biblio.spring.sis.services.impl.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lector")
public class LectorController {

    @Autowired
    private LibroService libroService;

    @PreAuthorize("hasAuthority('ROLE_BIBUSONL')")
    @GetMapping("/buscarLibro")
    public List<LibroEntity> buscarLibro(@RequestParam String parametroFiltro) {
        return libroService.buscarLibro(parametroFiltro);
    }

    @PreAuthorize("hasAuthority('ROLE_BIBUSONL')")
    @GetMapping("/getAllBook")
    public ResponseEntity<List<LibroEntity>> getAllBook() {
        List<LibroEntity> recursos = libroService.getAllBooK();
        return new ResponseEntity<>(recursos, HttpStatus.OK);
    }
}
