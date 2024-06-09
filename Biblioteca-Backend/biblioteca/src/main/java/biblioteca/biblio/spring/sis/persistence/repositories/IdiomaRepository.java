package biblioteca.biblio.spring.sis.persistence.repositories;

import biblioteca.biblio.spring.sis.persistence.entities.IdiomaEntity;
import biblioteca.roles.spring.sis.persistence.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IdiomaRepository extends JpaRepository<IdiomaEntity, Integer> {
    @Query("SELECT ie FROM IdiomaEntity ie WHERE eliminado <> 1")
    List<IdiomaEntity> getAllIdioma();
}
