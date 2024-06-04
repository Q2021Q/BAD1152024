package biblioteca.roles.spring.sis.persistence.repositories;

import biblioteca.roles.spring.sis.persistence.entities.UserRoleEntity;
import biblioteca.roles.spring.sis.persistence.entities.UserRoleId;
import biblioteca.security.spring.sis.persistence.entities.PersonalNameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, UserRoleId> {

//    @Query("SELECT name FROM PersonalNameEntity name WHERE idPerName = :idName")
//    PersonalNameEntity findByIdName(@Param("idName") Long idName);

}
