package biblioteca.biblio.spring.sis.controllers;


import biblioteca.biblio.spring.sis.persistence.entities.CategoriaRecursoEntity;
import biblioteca.biblio.spring.sis.services.impl.CategoriaRecursoService;
import biblioteca.biblio.spring.sis.services.models.dtos.ResponseBiblioDTO;
import biblioteca.roles.spring.sis.services.impl.RoleService;
import biblioteca.roles.spring.sis.services.models.dtos.ResponseRolDTO;
import biblioteca.roles.spring.sis.services.models.dtos.requestDTO.UserRolRDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bibliotecario")
public class CategoriaRecursoController {

    @Autowired
    private CategoriaRecursoService categoriaRecursoService;

    @PreAuthorize("hasAuthority('ROLE_BIBLIOTECARIO')")
    @PostMapping("/addcategoriarecurso")
    ResponseEntity<ResponseBiblioDTO> addUserRole(@RequestBody CategoriaRecursoEntity categoriaRecursoEntity){

        return new ResponseEntity<>(categoriaRecursoService.addCategoriaRecurso(categoriaRecursoEntity), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_BIBLIOTECARIO')")
    @PostMapping("/updatecategoriarecurso")
    ResponseEntity<ResponseBiblioDTO> updateUserRole(@RequestBody CategoriaRecursoEntity categoriaRecursoEntity){

        return new ResponseEntity<>(categoriaRecursoService.updateCategoriaRecurso(categoriaRecursoEntity), HttpStatus.OK);
    }
}
