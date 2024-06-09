package biblioteca.biblio.spring.sis.services.impl;

import biblioteca.biblio.spring.sis.persistence.entities.*;
import biblioteca.biblio.spring.sis.persistence.repositories.LibroRepository;
import biblioteca.biblio.spring.sis.persistence.repositories.RecursoDigitalRepositorio;
import biblioteca.biblio.spring.sis.services.models.dtos.ResponseBiblioDTO;
import biblioteca.biblio.spring.sis.services.models.dtos.requestDTO.LibroRequestTDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private RecursoDigitalRepositorio recursoDigitalRepositorio;

    @Transactional
    public ResponseBiblioDTO addLibro(LibroRequestTDO libroRequestTDO){
        LibroEntity libroEntity = new LibroEntity();

        CategoriaRecursoEntity categoriaRecursoEntity = new CategoriaRecursoEntity();
        categoriaRecursoEntity.setIdCategoriaRecurso(libroRequestTDO.getIdCategoria());
        libroEntity.setCategoria(categoriaRecursoEntity);

        EditorialEntity editorialEntity = new EditorialEntity();
        editorialEntity.setIdEditorial(libroRequestTDO.getIdEditorial());
        libroEntity.setEditorial(editorialEntity);

        IdiomaEntity idiomaEntity = new IdiomaEntity();
        idiomaEntity.setIdIdioma(libroRequestTDO.getIdIdioma());
        libroEntity.setIdioma(idiomaEntity);

        AutorEntity autorEntity = new AutorEntity();
        autorEntity.setIdAutor(libroRequestTDO.getIdAutor());
        libroEntity.setAutor(autorEntity);

        RecursoDigitalEntity recursoDigitalEntity = new RecursoDigitalEntity();
        recursoDigitalEntity.setRutaAcceso(libroRequestTDO.getRutaAcceso());
        RecursoDigitalEntity recursoDigitalBd = recursoDigitalRepositorio.save(recursoDigitalEntity);
        //recursoDigitalEntity.setArchivoDigitalData(libroRequestTDO.getArchivoDigitalData());
        libroEntity.setRecursoDigital(recursoDigitalBd);

        libroEntity.setTitulo(libroRequestTDO.getTitulo());
        libroEntity.setFechapuBlicacion(libroRequestTDO.getFechapuBlicacion());
        libroEntity.setFechaAdQuisicion(libroRequestTDO.getFechaAdQuisicion());
        libroEntity.setCodigoISBN(libroRequestTDO.getCodigoISBN());
        libroEntity.setDescripcion(libroRequestTDO.getDescripcion());
        libroEntity.setEdicion(libroRequestTDO.getEdicion());
        libroEntity.setNumPaginas(libroRequestTDO.getNumPaginas());
        libroEntity.setEliminado(0);

        ResponseBiblioDTO responseBiblioDTO = new ResponseBiblioDTO();

        libroRepository.save(libroEntity);
        responseBiblioDTO.setMessage("ok");
        return responseBiblioDTO;
    }

    public ResponseBiblioDTO delteBook(LibroRequestTDO libroRequestTDO){
        ResponseBiblioDTO responseBiblioDTO = new ResponseBiblioDTO();

        Optional<LibroEntity> libroEntityOptional = libroRepository.findById(libroRequestTDO.getIdLibro());

        if (!libroEntityOptional.isPresent()){
            responseBiblioDTO.setNumOfErrors(1);
            responseBiblioDTO.setMessage("error al encontrar el id clave libro");
            return responseBiblioDTO;
        }

        LibroEntity libroEntity = libroEntityOptional.get();
        libroEntity.setEliminado(1);
        libroRepository.save(libroEntity);
        responseBiblioDTO.setMessage("Eliminado");
        return responseBiblioDTO;
    }

    @Transactional
    public ResponseBiblioDTO updateLibro(LibroRequestTDO libroRequestTDO){
        LibroEntity libroEntity = new LibroEntity();

        CategoriaRecursoEntity categoriaRecursoEntity = new CategoriaRecursoEntity();
        categoriaRecursoEntity.setIdCategoriaRecurso(libroRequestTDO.getIdCategoria());
        libroEntity.setCategoria(categoriaRecursoEntity);

        EditorialEntity editorialEntity = new EditorialEntity();
        editorialEntity.setIdEditorial(libroRequestTDO.getIdEditorial());
        libroEntity.setEditorial(editorialEntity);

        IdiomaEntity idiomaEntity = new IdiomaEntity();
        idiomaEntity.setIdIdioma(libroRequestTDO.getIdIdioma());
        libroEntity.setIdioma(idiomaEntity);

        AutorEntity autorEntity = new AutorEntity();
        autorEntity.setIdAutor(libroRequestTDO.getIdAutor());
        libroEntity.setAutor(autorEntity);


        Optional<RecursoDigitalEntity> recursoDigitalOptinal = recursoDigitalRepositorio.findById(libroRequestTDO.getIdRecursoDigital());
        RecursoDigitalEntity recursoDigitalEntity = recursoDigitalOptinal.get();
        recursoDigitalEntity.setRutaAcceso(libroRequestTDO.getRutaAcceso());
        recursoDigitalRepositorio.save(recursoDigitalEntity);
        libroEntity.setRecursoDigital(recursoDigitalEntity);

        libroEntity.setIdLibro(libroRequestTDO.getIdLibro());
        libroEntity.setTitulo(libroRequestTDO.getTitulo());
        libroEntity.setFechapuBlicacion(libroRequestTDO.getFechapuBlicacion());
        libroEntity.setFechaAdQuisicion(libroRequestTDO.getFechaAdQuisicion());
        libroEntity.setCodigoISBN(libroRequestTDO.getCodigoISBN());
        libroEntity.setDescripcion(libroRequestTDO.getDescripcion());
        libroEntity.setEdicion(libroRequestTDO.getEdicion());
        libroEntity.setNumPaginas(libroRequestTDO.getNumPaginas());
        libroEntity.setEliminado(0);

        ResponseBiblioDTO responseBiblioDTO = new ResponseBiblioDTO();

        libroRepository.save(libroEntity);
        responseBiblioDTO.setMessage("ok");
        return responseBiblioDTO;
    }

    public List<LibroEntity> getAllBooK(){
        ResponseBiblioDTO responseBiblioDTO = new ResponseBiblioDTO();
        return libroRepository.getAllLibro();
    }

    public List<LibroEntity> buscarLibro(String parametroFiltro) {

        if (parametroFiltro == null) {
            return null;
        }

        List<LibroEntity> boooksByTitulo = libroRepository.findByTituloContainingIgnoreCase(parametroFiltro);
        if (!boooksByTitulo.isEmpty()){
            return boooksByTitulo;
        }

        String autor = parametroFiltro.replaceAll("\\s+", "");
        List<LibroEntity> boooksByAutor = libroRepository.findByAutor(autor);

        if (!boooksByAutor.isEmpty()){
            return boooksByAutor;
        }

        String codigoISBN = parametroFiltro.replaceAll("\\s+", "");
        List<LibroEntity> boooksByISBN = libroRepository.findByCodigoISBN(codigoISBN);

        if (!boooksByISBN.isEmpty()){
            return boooksByISBN;
        }

        return null;
    }
}
