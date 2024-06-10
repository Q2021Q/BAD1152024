package biblioteca.biblio.spring.sis.controllers;

import biblioteca.biblio.spring.sis.persistence.entities.LibroEntity;
import biblioteca.biblio.spring.sis.services.impl.LibroService;
import biblioteca.biblio.spring.sis.services.models.dtos.ResponseBiblioDTO;
import biblioteca.biblio.spring.sis.services.models.dtos.requestDTO.LibroRequestTDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libro")
public class LIbroController {

    @Autowired
    private LibroService libroService;

    @PreAuthorize("hasAuthority('ROLE_BIBLIOTECARIO')")
    @PostMapping("/addBook")
    ResponseEntity<ResponseBiblioDTO> addBook(@RequestBody LibroRequestTDO libroRequestTDO){
        return new ResponseEntity<>(libroService.addLibro(libroRequestTDO), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_BIBLIOTECARIO')")
    @DeleteMapping("/deleteBook")
    ResponseEntity<ResponseBiblioDTO> deleteBook(@RequestBody LibroRequestTDO libroRequestTDO){
        return new ResponseEntity<>(libroService.delteBook(libroRequestTDO), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_BIBLIOTECARIO')")
    @PutMapping("/updateBook")
    ResponseEntity<ResponseBiblioDTO> updateBook(@RequestBody LibroRequestTDO libroRequestTDO){
        return new ResponseEntity<>(libroService.updateLibro(libroRequestTDO), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_BIBLIOTECARIO')")
    @GetMapping("/getAllBook")
    public ResponseEntity<List<LibroEntity>> getAllBook() {
        List<LibroEntity> recursos = libroService.getAllBooK();
        return new ResponseEntity<>(recursos, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_BIBLIOTECARIO')")
    @GetMapping("/buscarLibro")
    public List<LibroEntity> buscarLibro(@RequestParam String parametroFiltro) {
        return libroService.buscarLibro(parametroFiltro);
    }
}
