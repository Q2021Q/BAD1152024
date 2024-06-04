package biblioteca.security.spring.sis.services.impl;


import biblioteca.security.spring.sis.services.ICodeVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import biblioteca.security.spring.sis.persistence.entities.PersonalNameEntity;
import biblioteca.security.spring.sis.persistence.entities.UserEntity;
import biblioteca.security.spring.sis.persistence.repositories.UserNameRepository;
import biblioteca.security.spring.sis.persistence.repositories.UserRepository;
import biblioteca.security.spring.sis.services.models.coreFunctions.CodeVerificationCore;
import biblioteca.security.spring.sis.services.models.dtos.ResponseDTO;
import biblioteca.security.spring.sis.services.models.dtos.requestDTO.CodeVerificationRDTO;
import biblioteca.security.spring.sis.services.models.dtos.requestDTO.UserNameCodeRDTO;

@Service
public class CodeVerificationServiceImpl implements ICodeVerificationService {

    @Autowired
    CodeVerificationCore codeVerificationCore;

    @Autowired
    UserNameRepository userNameRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseDTO addNewCode(UserEntity userEntity) throws Exception {
        ResponseDTO responseDTO = new ResponseDTO();
        //actualiza con un nuevo condigo de verificacion, en la BD y en el correo
        boolean wasUpdate = codeVerificationCore.updateCodeVerification(userEntity.getEmail());

        if (wasUpdate){
            responseDTO.setNumOfErrors(0);
            responseDTO.setMessage("Hemos enviado un nuevo código de verificación al correo " + userEntity.getEmail());
        }else {
            responseDTO.setNumOfErrors(1);
            responseDTO.setMessage("Error, no se pudo crear un nuevo código");
        }

        return responseDTO;
    }

    @Override
    public ResponseDTO addRelationUserName(UserNameCodeRDTO userNameCodeRDTO){
        CodeVerificationRDTO codeVerificationRDTO = new CodeVerificationRDTO();
        codeVerificationRDTO.setEmail(userNameCodeRDTO.getEmail());
        codeVerificationRDTO.setCodeSentEmail(userNameCodeRDTO.getCodeSentEmail());

        //codeSentToEmailVerify valida que el email y codigo sean correctos
        ResponseDTO responseDTO = codeVerificationCore.codeSentToEmailVerify(codeVerificationRDTO, 2);// minutos
        // el codigo que se mando al correo solo sera valido durante dos minutos

        //verifica que el codigo sea valido
        if (responseDTO.getNumOfErrors() == 1){
            return responseDTO;
        }

        if (userNameCodeRDTO.getIdUserName() == null){
            responseDTO.setMessage("Error clave del nombre no proporcionada");
            responseDTO.setNumOfErrors(1);
            return  responseDTO;
        }

        //Corrobora que efectivamente el id proporcionando este en la tabla/entidad PersonalNameEntity
        PersonalNameEntity nameRegistro = userNameRepository.findByIdName(userNameCodeRDTO.getIdUserName());
        if (nameRegistro == null){
            responseDTO.setMessage("Error violación de clave id del nombre en la tabla PersonalName");
            responseDTO.setNumOfErrors(1);
            return  responseDTO;
        }

        //el usuari tiene un unico registro de la talba PersonalName
        UserEntity userRegistro = userRepository.findByIdPersonalName(userNameCodeRDTO.getIdUserName());
        if (userRegistro != null){
            responseDTO.setMessage("Error violación de clave id del nombre en la tabla User");
            responseDTO.setNumOfErrors(1);
            return  responseDTO;
        }

        //el email existe ya que en la validacion del codigo enviado, en ese punto se verifica su existencia en la bd
        UserEntity userFindByEmail = userRepository.findByEmail(userNameCodeRDTO.getEmail());
        if (userFindByEmail.getIdpersonalname() != null){
            responseDTO.setMessage("Error violación en la relación del usuario y tabla nombre");
            responseDTO.setNumOfErrors(1);
            return  responseDTO;
        }

        userFindByEmail.setIdpersonalname(nameRegistro);
        userRepository.save(userFindByEmail);

        responseDTO.setNumOfErrors(0);
        return responseDTO;
    }

}
