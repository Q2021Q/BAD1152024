package biblioteca.biblio.spring.sis.services.impl;

import biblioteca.biblio.spring.sis.persistence.entities.IdiomaEntity;
import biblioteca.biblio.spring.sis.persistence.entities.RecursoDigitalEntity;
import biblioteca.biblio.spring.sis.persistence.repositories.RecursoDigitalRepositorio;
import biblioteca.biblio.spring.sis.services.models.dtos.ResponseBiblioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecursoDigitalService {

    @Autowired
    private RecursoDigitalRepositorio recursoDigitalRepositorio;

    public ResponseBiblioDTO addRecursoDigital(RecursoDigitalEntity recursoDigitalEntity){
        ResponseBiblioDTO responseBiblioDTO = new ResponseBiblioDTO();

        recursoDigitalRepositorio.save(recursoDigitalEntity);
        responseBiblioDTO.setMessage("ok");
        return responseBiblioDTO;
    }

}
