package biblioteca.biblio.spring.sis.services.impl;

import biblioteca.biblio.spring.sis.persistence.entities.AutorEntity;
import biblioteca.biblio.spring.sis.persistence.repositories.AutorRepository;
import biblioteca.biblio.spring.sis.services.models.dtos.ResponseBiblioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public ResponseBiblioDTO addAutor(AutorEntity autorEntity){
        ResponseBiblioDTO responseBiblioDTO = new ResponseBiblioDTO();

        autorRepository.save(autorEntity);
        responseBiblioDTO.setMessage("ok");
        return responseBiblioDTO;
    }

    public List<AutorEntity> getAllAutor(){
        ResponseBiblioDTO responseBiblioDTO = new ResponseBiblioDTO();
        return autorRepository.findAll();
    }

}
