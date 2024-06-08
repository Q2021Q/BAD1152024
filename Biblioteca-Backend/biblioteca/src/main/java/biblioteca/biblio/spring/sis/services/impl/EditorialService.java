package biblioteca.biblio.spring.sis.services.impl;


import biblioteca.biblio.spring.sis.persistence.entities.CategoriaRecursoEntity;
import biblioteca.biblio.spring.sis.persistence.entities.EditorialEntity;
import biblioteca.biblio.spring.sis.persistence.repositories.EditorialRepository;
import biblioteca.biblio.spring.sis.services.models.dtos.ResponseBiblioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditorialService {

    @Autowired
    private EditorialRepository editorialRepository;

    public ResponseBiblioDTO addEditorial(EditorialEntity editorialEntity){
        ResponseBiblioDTO responseBiblioDTO = new ResponseBiblioDTO();
        editorialRepository.save(editorialEntity);
        responseBiblioDTO.setMessage("ok");
        return responseBiblioDTO;
    }

    public List<EditorialEntity> getAllEditorial(){
        ResponseBiblioDTO responseBiblioDTO = new ResponseBiblioDTO();
        return editorialRepository.findAll();
    }

}
