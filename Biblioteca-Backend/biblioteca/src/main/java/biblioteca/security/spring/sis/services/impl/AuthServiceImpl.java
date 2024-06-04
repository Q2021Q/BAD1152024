package biblioteca.security.spring.sis.services.impl;

import biblioteca.roles.spring.sis.persistence.entities.RoleEntity;
import biblioteca.roles.spring.sis.persistence.entities.UserRoleEntity;
import biblioteca.roles.spring.sis.persistence.entities.UserRoleId;
import biblioteca.roles.spring.sis.persistence.repositories.RoleRepository;
import biblioteca.roles.spring.sis.persistence.repositories.UserRoleRepository;
import biblioteca.security.spring.sis.services.IAuthService;
import biblioteca.security.spring.sis.services.IJWTUtilityService;
import biblioteca.security.spring.sis.services.models.dtos.outputDTO.UserTokenOutPutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import biblioteca.security.spring.sis.persistence.entities.CodigoVerificacionEntity;
import biblioteca.security.spring.sis.persistence.entities.PersonalNameEntity;
import biblioteca.security.spring.sis.persistence.entities.UserEntity;
import biblioteca.security.spring.sis.persistence.repositories.CodigoVerificacionRepository;
import biblioteca.security.spring.sis.persistence.repositories.UserNameRepository;
import biblioteca.security.spring.sis.persistence.repositories.UserRepository;
import biblioteca.security.spring.sis.services.models.coreFunctions.CodeVerificationCore;
import biblioteca.security.spring.sis.services.models.coreFunctions.FechaCore;
import biblioteca.security.spring.sis.services.models.coreFunctions.UserCore;
import biblioteca.security.spring.sis.services.models.dtos.CodigoVerificacionDTO;
import biblioteca.security.spring.sis.services.models.dtos.LoginDTO;
import biblioteca.security.spring.sis.services.models.dtos.ResponseDTO;
import biblioteca.security.spring.sis.services.models.dtos.invalidFieldsDTO.UserCodeFieldDTO;
import biblioteca.security.spring.sis.services.models.dtos.requestDTO.CodeVerificationRDTO;
import biblioteca.security.spring.sis.services.models.dtos.requestDTO.UserCodeRDTO;

import javax.swing.text.html.parser.Parser;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserNameRepository userNameRepository;

    @Autowired
    private CodigoVerificacionRepository codigoVerificacionRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EmailService emailService;
    @Autowired
    private IJWTUtilityService jwtUtilityService;

    @Autowired
    private UserCore userCore;

    @Autowired
    private CodeVerificationCore codeVerificationCore;

    @Autowired
    private FechaCore fechaCore;

    @Override
    public ResponseDTO addUserName(PersonalNameEntity personalName){
        ResponseDTO responseName = userCore.nameValidate(personalName);


        //Validacion del nombre del usuario
        if (responseName.getNumOfErrors() == 1 ){
            return responseName;
        }
        //se inserta en BD el nombre de usuario
        PersonalNameEntity personalNameAdd = userNameRepository.save(personalName);
        responseName.setNumOfErrors(0);
        responseName.setMessage("Nombre de usuario insertado con éxito");
        responseName.setData(personalNameAdd);
        return  responseName;
    }


    @Override
    public UserTokenOutPutDTO login(LoginDTO loginRequest) throws Exception {
        try {

            UserTokenOutPutDTO userTokenOutPutDTO;

            userTokenOutPutDTO = userCore.userDTOValidate(loginRequest);
            if (userTokenOutPutDTO.getError() == 1){
                return userTokenOutPutDTO;
            }

            UserEntity user = userRepository.findByUserName(loginRequest.getUserName());
            //Verifica que userName y password existan en la bd
            userTokenOutPutDTO = userCore.userBdValidate(loginRequest, user);
            if (userTokenOutPutDTO.getError() == 1) {
                return userTokenOutPutDTO;
            }

            String token = userCore.getTokend(loginRequest);
            userTokenOutPutDTO.setToken(token);
            return userTokenOutPutDTO;

        } catch (IllegalArgumentException e) {
            System.err.println("Error generating JWT: " + e.getMessage());
            throw new Exception("Error generating JWT", e);
        } catch (Exception e) {
            System.err.println("Unknown error: " + e.toString());
            throw new Exception("Unknown error", e);
        }
    }
//Si el usuario tiene solo un rol (idRol != 1) se retorna el token con ese rol, si el user tiene mas de un rol, se retorna la lista de roles y un
// token con rol no asignado, el usuario tendara que seleccionar el rol y hacer un nuevo legeo en la funcoin loginUserWithOneRole
    @Override
    public UserTokenOutPutDTO loginUserWithRoles(LoginDTO loginRequest) throws Exception{//para el personal interno, se le aplican las mismas validaciones que el logeo de usuarios, el enfoque es verificar que el usuario es un mienbro de la organizacoin y no un usuario que solo tiene el rol generico, como cliente por ejemplo
        try {

            UserTokenOutPutDTO userTokenOutPutDTO;

            userTokenOutPutDTO = userCore.userDTOValidate(loginRequest);
            if (userTokenOutPutDTO.getError() == 1){
                return userTokenOutPutDTO;
            }

            UserEntity user = userRepository.findByUserName(loginRequest.getUserName());
            //Verifica que userName y password existan en la bd
            userTokenOutPutDTO = userCore.userBdValidate(loginRequest, user);
            if (userTokenOutPutDTO.getError() == 1) {
                return userTokenOutPutDTO;
            }

            List<RoleEntity> userAllRole = roleRepository.getListRole(loginRequest.getUserName());
            if (userAllRole.isEmpty()){
                userTokenOutPutDTO.setError(1);
                userTokenOutPutDTO.setMessage("Error, no se obtuvieron los roles del usuario");
                return  userTokenOutPutDTO;
            }

            String token;
            if (userAllRole.size() == 1){
                loginRequest.setIdRol(userAllRole.get(0).getIdRole());
                token = userCore.getTokend(loginRequest);
            }else {
                token = userCore.getTokend(loginRequest);//Obtiene un token con rol unasigned (rol no asignado)
            }
            userTokenOutPutDTO.setToken(token);
            userTokenOutPutDTO.setUserAllRole(userAllRole);
            return userTokenOutPutDTO;

        } catch (IllegalArgumentException e) {
            System.err.println("Error generating JWT: " + e.getMessage());
            throw new Exception("Error generating JWT", e);
        } catch (Exception e) {
            System.err.println("Unknown error: " + e.toString());
            throw new Exception("Unknown error", e);
        }
    }

    @Override
    public UserTokenOutPutDTO loginUserWithOneRole(LoginDTO loginRequest, Long userIdFromJwt) throws Exception {
        try {

            UserRoleId userRoleId = new UserRoleId();

            UserTokenOutPutDTO userTokenOutPutDTO;

            //valida la existencia de la contraseña
            userTokenOutPutDTO = userCore.userDTOPasswordValidate(loginRequest);
            if (userTokenOutPutDTO.getError() == 1){
                return userTokenOutPutDTO;
            }

            Optional <UserEntity> user = userRepository.findById(userIdFromJwt);
            //Verifica que userName y password existan en la bd
            userTokenOutPutDTO = userCore.userBdValidate(loginRequest, user.get());
            if (userTokenOutPutDTO.getError() == 1) {
                return userTokenOutPutDTO;
            }

            if (loginRequest.getIdRol() == null){
                userTokenOutPutDTO.setError(1);
                userTokenOutPutDTO.setMessage("Error, no se proporcionó la clave rol del usuario");
                return  userTokenOutPutDTO;
            }

            //user con rol id 1 no permite
            if (loginRequest.getIdRol() == 1){
                userTokenOutPutDTO.setError(1);
                userTokenOutPutDTO.setMessage("Error, permiso denegado");
                return  userTokenOutPutDTO;
            }

            userRoleId.setIdUser(userIdFromJwt);
            userRoleId.setIdRole(loginRequest.getIdRol());
            if (!userRoleRepository.findById(userRoleId).isPresent()){
                userTokenOutPutDTO.setError(1);
                userTokenOutPutDTO.setMessage("Error, permiso denegado");
                return  userTokenOutPutDTO;
            }

            String token;
            loginRequest.setUserName(user.get().getUsername());
            token = userCore.getTokend(loginRequest);//Obtiene un token con rol

            userTokenOutPutDTO.setToken(token);
            return userTokenOutPutDTO;

        } catch (IllegalArgumentException e) {
            System.err.println("Error generating JWT: " + e.getMessage());
            throw new Exception("Error generating JWT", e);
        } catch (Exception e) {
            System.err.println("Unknown error: " + e.toString());
            throw new Exception("Unknown error", e);
        }
    }

    @Override
    @Transactional
    public ResponseDTO register(UserCodeRDTO userCodeRDTO) throws Exception {
        try {
            UserEntity userEntity = new UserEntity();
            ResponseDTO response = new ResponseDTO();
            UserCodeFieldDTO userCodeFieldDTO = new UserCodeFieldDTO();

            CodeVerificationRDTO codeVerificationRDTO = new CodeVerificationRDTO();

            UserEntity userEntityRepository = userRepository.findByEmail(userCodeRDTO.getEmail());
            if (userEntityRepository == null){
                response.setNumOfErrors(1);
                response.setMessage("Error no se completó la creación de usuario, la cuenta de correo es invalidad");
                response.setErrorCode("USNOTFOU");
                return response;
            }

            if (userEntityRepository.isVerifiedemail() == 1){
                response.setNumOfErrors(1);
                response.setErrorCode("USEXBD");
                response.setMessage("El email ya está registrado");
                return response;
            }

            codeVerificationRDTO.setEmail(userCodeRDTO.getEmail());
            codeVerificationRDTO.setCodeSentEmail(userCodeRDTO.getCodeSentEmail());

            userEntity.setUsername(userCodeRDTO.getUserName());
            userEntity.setPassword(userCodeRDTO.getPassword());

            ResponseDTO responseCodeDTO = codeVerificationCore.codeSentToEmailVerify(codeVerificationRDTO, 10);//para crear el usuario y contraseña, se contaran con 10 minutos habiles

            if (responseCodeDTO.getNumOfErrors() == 1){
                return responseCodeDTO;
            }

            if (userCore.validatePassword(userEntity).getNumOfErrors() == 1){//the function return a ResponseDTO
                response = userCore.validatePassword(userEntity);
                return response;
            }

            if (userCore.validateUsername(userEntity).getNumOfErrors() == 1){
                response = userCore.validateUsername(userEntity);
                return response;
            }

            //EL FORMATO EMAIL NO SE VALIDA, ESTE SE VERIFICA EN LA FUNCION codeVerificationCore
            
            if (userRepository.findByUserName(userCodeRDTO.getUserName()) != null){
                response.setNumOfErrors(1);
                response.setMessage("El nombre de usuario ya existe");
                userCodeFieldDTO.setUserName(true);
                response.setDataError(userCodeFieldDTO);
                return response;
            }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            userEntityRepository.setPassword(encoder.encode(userCodeRDTO.getPassword()));
            userEntityRepository.setUsername(userCodeRDTO.getUserName());
            userRepository.save(userEntityRepository);//actualiza el registro con el userName and password
            //El Correo fue confirmado, se actualiza con 1
            userEntityRepository.setVerifiedemail(1);

            //Insertar rol al usuario
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            RoleEntity role = new RoleEntity();
            UserRoleId userRoleId = new UserRoleId();

            role.setIdRole(1);//idRole = 1

            //Creacion de la llave compuesta
            userRoleId.setIdUser(userEntityRepository.getId());
            userRoleId.setIdRole(role.getIdRole());

            userRoleEntity.setUser(userEntityRepository);//relacion con users
            userRoleEntity.setRole(role);//relacion con roles
            userRoleEntity.setIdUserRole(userRoleId);//llave primariria

            userRoleRepository.save(userRoleEntity);

            response.setMessage("User created successfully!");

            response.setDataError(userCodeFieldDTO);
            return  response;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }



    @Override                        //request
    @Transactional
    public ResponseDTO registerEmail(UserEntity userEntityR) throws Exception {
        try {
            CodigoVerificacionDTO codidigoVerificacionDTO = new CodigoVerificacionDTO();
            CodigoVerificacionEntity codigoVerificacionEntity = new CodigoVerificacionEntity();

            ResponseDTO responseEmail = userCore.emailValidate(userEntityR);

            List<UserEntity> getAllUsers = userRepository.findAll();
            //formato de correo incorrecto
            if (responseEmail.getNumOfErrors() > 0){
                return responseEmail;
            }
            //verifica si ya existe el usuario creado con el codigo de verificacion, el correo nunca fue confirmado
            if (codeVerificationCore.checkExistingCode(userEntityR.getEmail())){
                responseEmail.setNumOfErrors(1);
                responseEmail.setErrorCode("UNVERUS");
                boolean wasUpdate = codeVerificationCore.updateCodeVerification(userEntityR.getEmail());
                if (wasUpdate){
                    responseEmail.setNumOfErrors(1);
                    responseEmail.setMessage("Hemos enviado un nuevo código de verificación al correo " + userEntityR.getEmail());
                    return  responseEmail;
                }else {
                    responseEmail.setNumOfErrors(1);
                    responseEmail.setMessage("Error al intentar crear un nuevo código de verificación  ");
                    return responseEmail;
                }
            }


            for (UserEntity existingUser : getAllUsers) {
                if (existingUser.getEmail().equals(userEntityR.getEmail())) {
                    responseEmail.setNumOfErrors(1);
                    responseEmail.setErrorCode("USALDYEXTS");
                    responseEmail.setMessage("El email " + userEntityR.getEmail() + " ya está registrado!");
                    return responseEmail;
                }
            }


            //se inserta en la BD el correo en la tabla users
            UserEntity userSaved =  userRepository.save(userEntityR);

            //se inserta el codigo de veficaion en BD y mandarlo al email
            codigoVerificacionEntity.setCodigo(codidigoVerificacionDTO.getCodigoVerificacion());
            codigoVerificacionEntity.setUser(userSaved);
            codigoVerificacionEntity.setDiaActual(fechaCore.getDiaActual());
            codigoVerificacionEntity.setHoraActual(fechaCore.getHoraActual());
            String codeSentToEmail = codigoVerificacionEntity.getCodigo();
            codigoVerificacionRepository.save(codigoVerificacionEntity);

            //se envia el codigo de ferificacion al corro electronico
            emailService.sendVerificationCode(userEntityR.getEmail(), codeSentToEmail);

            responseEmail.setNumOfErrors(0);
            responseEmail.setMessage("Hemos enviado un código de verificación al correo: " + userEntityR.getEmail());
            return responseEmail;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private boolean verifyPassword(String enteredPassword, String storedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(enteredPassword, storedPassword);
    }
}
