package biblioteca.biblio.spring.sis.persistence.repositories;

import biblioteca.biblio.spring.sis.persistence.entities.RecursoDigitalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecursoDigitalRepositorio extends JpaRepository<RecursoDigitalEntity, Long> {
}
