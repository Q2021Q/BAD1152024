package biblioteca.security.spring.sis.services;


import biblioteca.security.spring.sis.persistence.entities.UserEntity;
import biblioteca.security.spring.sis.services.models.dtos.ResponseDTO;
import biblioteca.security.spring.sis.services.models.dtos.requestDTO.UserNameCodeRDTO;

public interface ICodeVerificationService {
    ResponseDTO addNewCode(UserEntity userEntity)throws Exception;

    ResponseDTO addRelationUserName(UserNameCodeRDTO userNameCodeRDTO)throws Exception;
}
