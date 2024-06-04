package biblioteca.roles.spring.sis.services.impl;

import biblioteca.roles.spring.sis.persistence.entities.RoleEntity;
import biblioteca.roles.spring.sis.persistence.entities.UserRoleEntity;
import biblioteca.roles.spring.sis.persistence.entities.UserRoleId;
import biblioteca.roles.spring.sis.persistence.repositories.UserRoleRepository;
import biblioteca.roles.spring.sis.services.models.coreFunctions.UserRolCore;
import biblioteca.roles.spring.sis.services.models.dtos.ResponseRolDTO;
import biblioteca.roles.spring.sis.services.models.dtos.requestDTO.UserRolRDTO;
import biblioteca.security.spring.sis.persistence.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private UserRolCore userRolCore;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public ResponseRolDTO addUserRole(UserRolRDTO userRolRDTO){
        ResponseRolDTO responseRolDTO = new ResponseRolDTO();

        ResponseRolDTO responseRolValidatedDTO = userRolCore.userRoleValidate(userRolRDTO);
        if (responseRolValidatedDTO.getNumOfErrors() == 1){//se valida el los datos de la tabla userrol
            return responseRolValidatedDTO;
        }

        UserRoleEntity userRoleEntity = new UserRoleEntity();
        UserRoleId userRoleId = new UserRoleId();
        UserEntity userEntity = new UserEntity(userRolRDTO.getIdUser());
        RoleEntity roleEntity = new RoleEntity(userRolRDTO.getIdRol());

        //llave compuesta
        userRoleId.setIdUser(userRolRDTO.getIdUser());
        userRoleId.setIdRole(userRolRDTO.getIdRol());

        userRoleEntity.setIdUserRole(userRoleId);
        userRoleEntity.setUser(userEntity);
        userRoleEntity.setRole(roleEntity);


        userRoleRepository.save(userRoleEntity);
        responseRolDTO.setMessage("Rol asignado correctamente");
        return responseRolDTO;
    }
}
