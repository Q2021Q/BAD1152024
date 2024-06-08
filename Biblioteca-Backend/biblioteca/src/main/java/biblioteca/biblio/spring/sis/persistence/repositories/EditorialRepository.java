package biblioteca.biblio.spring.sis.persistence.repositories;

import biblioteca.biblio.spring.sis.persistence.entities.EditorialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepository extends JpaRepository<EditorialEntity, Integer> {
}
