package biblioteca.security.spring.sis.controllers;


import biblioteca.roles.spring.sis.services.impl.RoleService;
import biblioteca.security.spring.sis.persistence.entities.PersonalNameEntity;
import biblioteca.security.spring.sis.services.IAuthService;
import biblioteca.security.spring.sis.services.ICodeVerificationService;
import biblioteca.security.spring.sis.services.models.dtos.outputDTO.UserTokenOutPutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import biblioteca.security.spring.sis.persistence.entities.UserEntity;
import biblioteca.security.spring.sis.services.models.dtos.LoginDTO;
import biblioteca.security.spring.sis.services.models.dtos.ResponseDTO;
import biblioteca.security.spring.sis.services.models.dtos.requestDTO.UserCodeRDTO;
import biblioteca.security.spring.sis.services.models.dtos.requestDTO.UserNameCodeRDTO;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private IAuthService authService;

    @Autowired
    private ICodeVerificationService iCodeVerificationService;

    @PostMapping("/insertusername")
    private ResponseEntity<ResponseDTO> addUserName(@RequestBody PersonalNameEntity personalName) throws Exception {
        return new ResponseEntity<>(authService.addUserName(personalName), HttpStatus.OK);
    }

    @PostMapping("/registerEmail")
    private ResponseEntity<ResponseDTO> addUserEmail(@RequestBody UserEntity userEntity) throws Exception {
       return new ResponseEntity<>(authService.registerEmail(userEntity), HttpStatus.OK);
    }

    @PutMapping("/generatenewcode")
    private ResponseEntity<ResponseDTO> newCodeJenerated(@RequestBody UserEntity userEntity) throws Exception {

        //return codeGeneratedResponse;
        return new ResponseEntity<>(iCodeVerificationService.addNewCode(userEntity), HttpStatus.OK);

    }

    @PutMapping("/addrelacionusername")
    private ResponseEntity <ResponseDTO> addRelationUserName(@RequestBody UserNameCodeRDTO userNameCodeRDTO) throws Exception {
        return new ResponseEntity<>(iCodeVerificationService.addRelationUserName(userNameCodeRDTO), HttpStatus.OK);
    }

    @PostMapping("/register")
    private ResponseEntity<ResponseDTO> addUser(@RequestBody UserCodeRDTO userCodeRDTO) throws Exception {
        return new ResponseEntity<>(authService.register(userCodeRDTO), HttpStatus.OK);
    }

    @PostMapping("/login")
    private ResponseEntity<UserTokenOutPutDTO> login(@RequestBody LoginDTO loginRequest) throws Exception {
        UserTokenOutPutDTO userTokenOutPutDTO = new UserTokenOutPutDTO();
        try {
            Integer idRole = 1; // idRol = 1, todos los usuarios cuentan con este rol
            loginRequest.setIdRol(idRole);
            userTokenOutPutDTO = authService.login(loginRequest);
            if (userTokenOutPutDTO.getError() != 1) {
                return new ResponseEntity<>(userTokenOutPutDTO, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(userTokenOutPutDTO, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            userTokenOutPutDTO.setMessage("Error, fallo en autentificar al usuario");
            userTokenOutPutDTO.setError(1);
            return new ResponseEntity<>(userTokenOutPutDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/loginForUsersWithRoles")
    private ResponseEntity<UserTokenOutPutDTO> loginUserRole(@RequestBody LoginDTO loginRequest) throws Exception {
        UserTokenOutPutDTO userTokenOutPutDTO = authService.loginUserWithRoles(loginRequest);
        if (userTokenOutPutDTO.getError() == 1){
            return new ResponseEntity<>(userTokenOutPutDTO, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(userTokenOutPutDTO, HttpStatus.ACCEPTED);
    }
}
