package biblioteca.security.spring.sis.services.models.coreFunctions;


import biblioteca.roles.spring.sis.persistence.entities.RoleEntity;
import biblioteca.roles.spring.sis.persistence.repositories.RoleRepository;
import biblioteca.security.spring.sis.persistence.entities.PersonalNameEntity;
import biblioteca.security.spring.sis.persistence.entities.UserEntity;
import biblioteca.security.spring.sis.persistence.repositories.UserRepository;
import biblioteca.security.spring.sis.services.IJWTUtilityService;
import biblioteca.security.spring.sis.services.models.dtos.LoginDTO;
import biblioteca.security.spring.sis.services.models.dtos.ResponseDTO;
import biblioteca.security.spring.sis.services.models.dtos.invalidFieldsDTO.UserCodeFieldDTO;
import biblioteca.security.spring.sis.services.models.dtos.invalidFieldsDTO.UserInvalidFieldDTO;
import biblioteca.security.spring.sis.services.models.dtos.outputDTO.ClaveRolTokenOutPutDTO;
import biblioteca.security.spring.sis.services.models.dtos.outputDTO.UserTokenOutPutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public class UserCore {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private IJWTUtilityService jwtUtilityService;

    public ResponseDTO nameValidate(PersonalNameEntity name){
        ResponseDTO response = new ResponseDTO();
        UserInvalidFieldDTO userInvalidFieldDTO = new UserInvalidFieldDTO();

        response.setNumOfErrors(0);

        if (name.getFirstName() == null || name.getFirstName().length() < 3 || name.getFirstName().length() > 20){
            response.setNumOfErrors(1);
            response.setMessage("Campo primer nombre es incorrecto");
            userInvalidFieldDTO.setFirstName(true);
            response.setDataError(userInvalidFieldDTO);
            return response;
        }

        if (name.getFirstLastName() == null || name.getFirstLastName().length() < 3 || name.getFirstLastName().length() > 20){
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("Campo primer apellido es incorrecto");
            userInvalidFieldDTO.setFirstLastName(true);
            response.setDataError(userInvalidFieldDTO);
            return response;
        }

        if (name.getSecondName() != null){
            if (!name.getSecondName().isEmpty()){
                if (name.getSecondName().length() > 20){
                    response.setNumOfErrors(response.getNumOfErrors() + 1);
                    response.setMessage("Longitud de campo superada");
                    userInvalidFieldDTO.setSecondName(true);
                    response.setDataError(userInvalidFieldDTO);
                    return response;
                }
            }
        }

        if (name.getSecondLastName() != null){
            if (!name.getSecondLastName().isEmpty()){
                if (name.getSecondLastName().length() > 20){
                    response.setNumOfErrors(response.getNumOfErrors() + 1);
                    response.setMessage("Longitud de campo superada");
                    userInvalidFieldDTO.setSecondLastName(true);
                    response.setDataError(userInvalidFieldDTO);
                    return response;
                }
            }
        }

        return response;
    }

    public ResponseDTO emailValidate(UserEntity user){
        ResponseDTO response = new ResponseDTO();
        response.setNumOfErrors(0);

        if (user.getEmail() == null || user.getEmail().isEmpty()){
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("El campo email es null");
            return response;
        }
        if (user.getEmail().length() > 30){
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("Longitud de campo superada");
            return response;
        }

        if (!user.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("El campo email no es correcto");
        }
        return response;
    }

    public ResponseDTO validatePassword(UserEntity user){
        ResponseDTO response = new ResponseDTO();
        UserCodeFieldDTO userCodeFieldDTO = new UserCodeFieldDTO();

        if (user.getPassword() == null){
            response.setNumOfErrors(1);
            response.setMessage("La contraseña es requerida");
            userCodeFieldDTO.setPassword(true);
            response.setDataError(userCodeFieldDTO);
            return response;
        }
        if (user.getPassword().contains(" ")){
            response.setNumOfErrors(1);
            response.setMessage("Formato de contraseña incorrecto, no se admiten espacios en blanco en la contraseña");
            userCodeFieldDTO.setPassword(true);
            response.setDataError(userCodeFieldDTO);
            return response;
        }

        if(
                user.getPassword() == null ||
                        !user.getPassword().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,16}$")
        ){
            response.setNumOfErrors(1);
            response.setMessage("La contraseña debe tener entre 8 y 16 caracteres, al menos un número, una minúscula y una mayúscula");
            userCodeFieldDTO.setPassword(true);
            response.setDataError(userCodeFieldDTO);
        }

        return response;
    }

    public ResponseDTO validateUsername(UserEntity userEntity){
        ResponseDTO responseDTO = new ResponseDTO();
        UserCodeFieldDTO userCodeFieldDTO = new UserCodeFieldDTO();

        if (userEntity.getUsername() == null){
            responseDTO.setNumOfErrors(1);
            responseDTO.setMessage("El nombre de usuario es requerido");
            userCodeFieldDTO.setUserName(true);
            responseDTO.setDataError(userCodeFieldDTO);
            return responseDTO;
        }

        if (userEntity.getUsername().isEmpty()){
            responseDTO.setNumOfErrors(1);
            responseDTO.setMessage("Error campo nombre de usuario vacío");
            userCodeFieldDTO.setUserName(true);
            responseDTO.setDataError(userCodeFieldDTO);
            return responseDTO;
        }

        if (userEntity.getUsername().contains(" ")){
            responseDTO.setNumOfErrors(1);
            responseDTO.setMessage("Error no se permiten espacios en blanco en el nombre de usuario");
            userCodeFieldDTO.setUserName(true);
            responseDTO.setDataError(userCodeFieldDTO);
            return responseDTO;
        }

        if (userEntity.getUsername().length() <= 4){
            responseDTO.setNumOfErrors(1);
            responseDTO.setMessage("El nombre de usuario debe de tener al menos 5 caracteres");
            userCodeFieldDTO.setUserName(true);
            responseDTO.setDataError(userCodeFieldDTO);
            return responseDTO;
        }

        if (userEntity.getUsername().length() >= 29){
            responseDTO.setNumOfErrors(1);
            responseDTO.setMessage("Longitud del campo nombre de usuario superada");
            userCodeFieldDTO.setUserName(true);
            responseDTO.setDataError(userCodeFieldDTO);
            return responseDTO;
        }

        return responseDTO;
    }

    public UserTokenOutPutDTO userDTOValidate(LoginDTO loginRequest){
        UserTokenOutPutDTO userTokenOutPutDTO = new UserTokenOutPutDTO();

        if (loginRequest.getUserName() == null) {
            userTokenOutPutDTO.setMessage("error, Null username!");
            userTokenOutPutDTO.setError(1);
            return userTokenOutPutDTO;
        }
        if (loginRequest.getUserName().isEmpty()) {
            userTokenOutPutDTO.setMessage("error, Empty username!");
            userTokenOutPutDTO.setError(1);
            return userTokenOutPutDTO;
        }

        if (loginRequest.getPassword() == null) {
            userTokenOutPutDTO.setMessage("error, Null password!");
            userTokenOutPutDTO.setError(1);
            return userTokenOutPutDTO;
        }
        if (loginRequest.getPassword().isEmpty()) {
            userTokenOutPutDTO.setMessage("error, Empty password!");
            userTokenOutPutDTO.setError(1);
            return userTokenOutPutDTO;
        }
        return  userTokenOutPutDTO;
    }

    public UserTokenOutPutDTO userDTOPasswordValidate(LoginDTO loginRequest){
        UserTokenOutPutDTO userTokenOutPutDTO = new UserTokenOutPutDTO();

        if (loginRequest.getPassword() == null) {
            userTokenOutPutDTO.setMessage("error, Null password!");
            userTokenOutPutDTO.setError(1);
            return userTokenOutPutDTO;
        }
        if (loginRequest.getPassword().isEmpty()) {
            userTokenOutPutDTO.setMessage("error, Empty password!");
            userTokenOutPutDTO.setError(1);
            return userTokenOutPutDTO;
        }
        return  userTokenOutPutDTO;
    }

    public UserTokenOutPutDTO userBdValidate(LoginDTO loginRequest, UserEntity user){

        UserTokenOutPutDTO userTokenOutPutDTO = new UserTokenOutPutDTO();

        try {

            if (user == null) {
                userTokenOutPutDTO.setMessage("error, User not registered!");
                userTokenOutPutDTO.setError(1);
                return userTokenOutPutDTO;
            }

            if (verifyPassword(loginRequest.getPassword(), user.getPassword())) {
                userTokenOutPutDTO.setFirstName(user.getIdpersonalname().getFirstName());
                userTokenOutPutDTO.setFirstLastName(user.getIdpersonalname().getFirstLastName());
                userTokenOutPutDTO.setMessage("Authentication successful");
            } else {
                userTokenOutPutDTO.setMessage("error, Authentication failed");
                userTokenOutPutDTO.setError(1);
            }
        } catch (Exception ex) {
            userTokenOutPutDTO.setMessage("An unexpected error occurred");
            userTokenOutPutDTO.setError(1);
        }

        return userTokenOutPutDTO;
    }

    public ClaveRolTokenOutPutDTO getTokend(LoginDTO loginRequest) throws Exception {
        try {

            ClaveRolTokenOutPutDTO claveRolTokenOutPutDTO = new ClaveRolTokenOutPutDTO();

            String token;
            UserEntity user = userRepository.findByUserName(loginRequest.getUserName());

            if (loginRequest.getIdRol() == null){
                token = jwtUtilityService.generateJWT(user.getId(), "unasigned");
                claveRolTokenOutPutDTO.setToken(token);
                claveRolTokenOutPutDTO.setClaveRol("unasigned");
                return claveRolTokenOutPutDTO;
            }

            Optional<RoleEntity> roleUser = roleRepository.findById(loginRequest.getIdRol());
            if (roleUser.isPresent()) {
                token  = jwtUtilityService.generateJWT(user.getId(), roleUser.get().getRoleCode());
                claveRolTokenOutPutDTO.setToken(token);
                claveRolTokenOutPutDTO.setClaveRol(roleUser.get().getRoleCode());
                return claveRolTokenOutPutDTO;
            }

            token = jwtUtilityService.generateJWT(user.getId(), "unasigned");
            claveRolTokenOutPutDTO.setClaveRol("unasigned");
            return claveRolTokenOutPutDTO;

        } catch (IllegalArgumentException e) {
            System.err.println("Error generating JWT: " + e.getMessage());
            throw new Exception("Error generating JWT", e);
        } catch (Exception e) {
            System.err.println("Unknown error: " + e.toString());
            throw new Exception("Unknown error", e);
        }
    }


    private boolean verifyPassword(String enteredPassword, String storedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(enteredPassword, storedPassword);
    }
}
