package biblioteca.roles.spring.sis.services.models.coreFunctions;

import biblioteca.roles.spring.sis.persistence.entities.UserRoleEntity;
import biblioteca.roles.spring.sis.persistence.entities.UserRoleId;
import biblioteca.roles.spring.sis.persistence.repositories.RoleRepository;
import biblioteca.roles.spring.sis.persistence.repositories.UserRoleRepository;
import biblioteca.roles.spring.sis.services.models.dtos.ResponseRolDTO;
import biblioteca.roles.spring.sis.services.models.dtos.requestDTO.UserRolRDTO;
import biblioteca.security.spring.sis.persistence.repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRolCore {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    public ResponseRolDTO userRoleValidate(UserRolRDTO userRolRDTO){
        ResponseRolDTO responseRolDTO = new ResponseRolDTO();

        if (userRolRDTO.getIdUser() == null){
            responseRolDTO.setNumOfErrors(1);
            responseRolDTO.setMessage("Error, no se ha proporcionada la clave id del usuario");
            return responseRolDTO;
        }

        if (userRolRDTO.getIdRol() == null){
            responseRolDTO.setNumOfErrors(1);
            responseRolDTO.setMessage("Error, no se ha proporcionada la clave id del rol");
            return responseRolDTO;
        }

        // Para buscar un registro por su clave primaria compuesta
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        UserRoleId userRoleId =  new UserRoleId(userRolRDTO.getIdUser(), userRolRDTO.getIdRol());

        //UserRoleId userRoleId = new UserRoleId(userRolRDTO.getIdUser(), userRolRDTO.getIdRol());
        Optional<UserRoleEntity> userRoleOptional = userRoleRepository.findById(userRoleId);

        if (userRoleOptional.isPresent()){
            responseRolDTO.setNumOfErrors(1);
            responseRolDTO.setMessage("Error, el Rol ya está asignado");
            return responseRolDTO;
        }

        if (!(userRepository.findById(userRolRDTO.getIdUser()).isPresent())){
            responseRolDTO.setNumOfErrors(1);
            responseRolDTO.setMessage("Error, clave id usuario no encontrada");
            return responseRolDTO;
        }

        if (!(roleRepository.findById(userRolRDTO.getIdRol()).isPresent())){
            responseRolDTO.setNumOfErrors(1);
            responseRolDTO.setMessage("Error, clave id rol no encontrada");
            return responseRolDTO;
        }

        return responseRolDTO;
    }
}
