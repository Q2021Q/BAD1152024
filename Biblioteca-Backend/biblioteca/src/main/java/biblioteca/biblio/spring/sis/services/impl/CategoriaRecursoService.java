package biblioteca.biblio.spring.sis.services.impl;

import biblioteca.biblio.spring.sis.persistence.entities.CategoriaRecursoEntity;
import biblioteca.biblio.spring.sis.persistence.repositories.CategoriaRecursoRepository;
import biblioteca.biblio.spring.sis.services.models.dtos.ResponseBiblioDTO;
import biblioteca.roles.spring.sis.persistence.entities.RoleEntity;
import biblioteca.roles.spring.sis.persistence.entities.UserRoleEntity;
import biblioteca.roles.spring.sis.persistence.entities.UserRoleId;
import biblioteca.roles.spring.sis.persistence.repositories.UserRoleRepository;
import biblioteca.roles.spring.sis.services.models.dtos.ResponseRolDTO;
import biblioteca.roles.spring.sis.services.models.dtos.requestDTO.UserRolRDTO;
import biblioteca.security.spring.sis.persistence.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaRecursoService {

    @Autowired
    private CategoriaRecursoRepository categoriaRecursoRepository;

    public ResponseBiblioDTO addCategoriaRecurso(CategoriaRecursoEntity categoriaRecursoEntity){
       ResponseBiblioDTO responseBiblioDTO = new ResponseBiblioDTO();
       categoriaRecursoRepository.save(categoriaRecursoEntity);
       responseBiblioDTO.setMessage("ok");
       return responseBiblioDTO;
    }

    public ResponseBiblioDTO updateCategoriaRecurso(CategoriaRecursoEntity categoriaRecursoEntity){
        ResponseBiblioDTO responseBiblioDTO = new ResponseBiblioDTO();
        categoriaRecursoRepository.save(categoriaRecursoEntity);
        responseBiblioDTO.setMessage("ok");
        return responseBiblioDTO;
    }

    public List<CategoriaRecursoEntity> getAllCatRecursos() {
        return categoriaRecursoRepository.findAll();
    }
}
