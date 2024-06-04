package biblioteca.roles.spring.sis.persistence.repositories;

import biblioteca.roles.spring.sis.persistence.entities.RoleEntity;
import biblioteca.security.spring.sis.persistence.entities.UserEntity;
import biblioteca.security.spring.sis.services.models.dtos.outputDTO.UserTokenOutPutDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    //    con la relacion
//    @Query("SELECT ur.role FROM UserRoleEntity ur WHERE ur.user.username = :username AND ur.user.id <> 1")
//    List<RoleEntity> getListRole(@Param("username") String username);

//    @Query("SELECT r FROM RoleEntity r WHERE r.idRole = 1")
//    RoleEntity findRoleByIdOne();
//se modificao la relacion, se puede simplificar usando la relacion en la entidad
    @Query("SELECT r FROM RoleEntity r " +
            "JOIN UserRoleEntity ur ON ur.id.idRole = r.idRole " +
            "JOIN UserEntity u ON u.id = ur.id.idUser " +
            "WHERE u.username = :username AND r.id <> 1")
    List<RoleEntity> getListRole(@Param("username") String username);

    //ADN u.id = ur.idUser AND r.id <> 1
}
