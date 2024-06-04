package biblioteca.security.spring.sis.persistence.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import biblioteca.security.spring.sis.persistence.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT user FROM UserEntity user WHERE email = :email")
    UserEntity findByEmail(@Param("email") String email);

    //Obtiene datos de users y personalname, si el correo existe y si no se ha verificado por codigo de confirmacion
    @Query("SELECT user FROM UserEntity user WHERE email = :email AND verifiedemail = 0")
    Object findUserDetailsByEmail(@Param("email") String email);

    @Query("SELECT user FROM UserEntity user WHERE username = :userName")
    UserEntity findByUserName(@Param("userName") String userName);

    @Query("SELECT user FROM UserEntity user WHERE idpersonalname.idPerName = :idName")
    UserEntity findByIdPersonalName(@Param("idName") Long idName);
}
