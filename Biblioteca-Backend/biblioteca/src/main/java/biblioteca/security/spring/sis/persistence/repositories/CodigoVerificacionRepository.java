package biblioteca.security.spring.sis.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import biblioteca.security.spring.sis.persistence.entities.CodigoVerificacionEntity;

public interface CodigoVerificacionRepository extends JpaRepository<CodigoVerificacionEntity, Long> {
    @Query(value = "SELECT * FROM codigoverificacion WHERE iduser = :idUser", nativeQuery = true)
    CodigoVerificacionEntity findByIdUser(Long idUser);

    //Obtiene el codigo de verificacion por medio del email
    @Query("SELECT cve FROM CodigoVerificacionEntity cve JOIN cve.iduser user WHERE user.email = :email")
    CodigoVerificacionEntity findCodeByEmail(@Param("email") String email);

}