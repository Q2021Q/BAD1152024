package biblioteca.security.spring.sis.persistence.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import biblioteca.security.spring.sis.persistence.entities.PersonalNameEntity;

import java.util.Optional;

public interface UserNameRepository extends JpaRepository<PersonalNameEntity, Long> {

    @Query(value = "SELECT * FROM personalname WHERE idpersonalname = :idpersonalname", nativeQuery = true)
    Optional<PersonalNameEntity> findByIPersonalName(String idpersonalname);

    @Query("SELECT name FROM PersonalNameEntity name WHERE idPerName = :idName")
    PersonalNameEntity findByIdName(@Param("idName") Long idName);

}
