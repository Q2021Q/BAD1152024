package biblioteca.biblio.spring.sis.services.impl;

import biblioteca.biblio.spring.sis.persistence.entities.EditorialEntity;
import biblioteca.biblio.spring.sis.persistence.entities.IdiomaEntity;
import biblioteca.biblio.spring.sis.persistence.repositories.IdiomaRepository;
import biblioteca.biblio.spring.sis.services.models.dtos.ResponseBiblioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdiomaService {
    @Autowired
    private IdiomaRepository idiomaRepository;

    public ResponseBiblioDTO addIdioma(IdiomaEntity idiomaEntity){
        ResponseBiblioDTO responseBiblioDTO = new ResponseBiblioDTO();

        idiomaEntity.setEliminado(0);
        idiomaRepository.save(idiomaEntity);
        responseBiblioDTO.setMessage("ok");
        return responseBiblioDTO;
    }

    public ResponseBiblioDTO delteIdioma(IdiomaEntity idiomaEntity){
        ResponseBiblioDTO responseBiblioDTO = new ResponseBiblioDTO();

        idiomaEntity.setEliminado(1);
        idiomaRepository.save(idiomaEntity);
        responseBiblioDTO.setMessage("eliminado");
        return responseBiblioDTO;
    }

    public List<IdiomaEntity> getAllIdioma(){
        ResponseBiblioDTO responseBiblioDTO = new ResponseBiblioDTO();
        return idiomaRepository.getAllIdioma();
    }
}
