package biblioteca.biblio.spring.sis.persistence.repositories;

import biblioteca.biblio.spring.sis.persistence.entities.LibroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<LibroEntity, Long> {
    @Query("SELECT le FROM LibroEntity le WHERE eliminado <> 1")
    List<LibroEntity> getAllLibro();

    @Query("SELECT l FROM LibroEntity l WHERE " +
            "(:titulo IS NULL OR l.titulo LIKE %:titulo%) AND " +
            "(:codigoISBN IS NULL OR l.codigoISBN = :codigoISBN) AND " +
            "(:autor IS NULL OR " +
            "(l.autor.firstName LIKE %:autor% OR l.autor.secondName LIKE %:autor% OR " +
            "l.autor.firstLastName LIKE %:autor% OR l.autor.secondLastName LIKE %:autor%))")
    List<LibroEntity> findByTituloOrCodigoISBNOrAutor(
            @Param("titulo") String titulo,
            @Param("codigoISBN") String codigoISBN,
            @Param("autor") String autor );

//    @Query("SELECT CONCAT(COALESCE(l.autor.firstName, ''), ' ', COALESCE(l.autor.secondName, ''), ' ', " +
//            "COALESCE(l.autor.firstLastName, ''), ' ', COALESCE(l.autor.secondLastName, '')) " +
//            "FROM LibroEntity l WHERE l.idLibro = :idLibro")
//    String findByAutor(@Param("idLibro") Long idLibro);

//    @Query("SELECT l FROM LibroEntity l JOIN l.autor a WHERE " +
//            "LOWER(CONCAT(COALESCE(a.firstName, ''), COALESCE(a.secondName, ''), ' ', " +
//            "COALESCE(a.firstLastName, ''), '', COALESCE(a.secondLastName, ''))) " +
//            "LIKE LOWER(CONCAT('%', :autor, '%'))")
//    List<LibroEntity> findByAutor(@Param("autor") String autor);

    @Query("SELECT l FROM LibroEntity l JOIN l.autor a WHERE " +
            "LOWER(REPLACE(CONCAT(COALESCE(a.firstName, ''), COALESCE(a.secondName, ''), " +
            "COALESCE(a.firstLastName, ''), COALESCE(a.secondLastName, '')), ' ', '')) " +
            "LIKE LOWER(REPLACE(CONCAT('%', :autor, '%'), ' ', ''))")
    List<LibroEntity> findByAutor(@Param("autor") String autor);


    List<LibroEntity> findByTituloContainingIgnoreCase(String titulo);


    List<LibroEntity> findByCodigoISBN(String codigoISBN);
}
