package biblioteca.security.spring.sis.services;




import biblioteca.security.spring.sis.persistence.entities.PersonalNameEntity;
import biblioteca.security.spring.sis.persistence.entities.UserEntity;
import biblioteca.security.spring.sis.services.models.dtos.LoginDTO;
import biblioteca.security.spring.sis.services.models.dtos.ResponseDTO;
import biblioteca.security.spring.sis.services.models.dtos.outputDTO.UserTokenOutPutDTO;
import biblioteca.security.spring.sis.services.models.dtos.requestDTO.UserCodeRDTO;

import java.util.HashMap;

public interface IAuthService {

    ResponseDTO addUserName(PersonalNameEntity personalName);

    public UserTokenOutPutDTO login(LoginDTO loginRequest) throws Exception;
    public ResponseDTO register(UserCodeRDTO userCodeRDTO) throws Exception;

    ResponseDTO registerEmail(UserEntity userEntity) throws Exception;

    public UserTokenOutPutDTO loginUserWithOneRole(LoginDTO loginRequest, Long userIdFromJwt) throws Exception;
    public UserTokenOutPutDTO loginUserWithRoles(LoginDTO loginRequest) throws Exception;

}
