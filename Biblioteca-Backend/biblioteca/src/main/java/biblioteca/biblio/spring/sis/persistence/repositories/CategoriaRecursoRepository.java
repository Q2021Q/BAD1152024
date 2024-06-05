package biblioteca.biblio.spring.sis.persistence.repositories;

import biblioteca.biblio.spring.sis.persistence.entities.CategoriaRecursoEntity;
import biblioteca.roles.spring.sis.persistence.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRecursoRepository extends JpaRepository<CategoriaRecursoEntity, Long> {

}
