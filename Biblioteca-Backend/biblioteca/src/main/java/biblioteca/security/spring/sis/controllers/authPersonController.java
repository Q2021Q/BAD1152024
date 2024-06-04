package biblioteca.security.spring.sis.controllers;


import biblioteca.security.spring.sis.services.IAuthService;
import biblioteca.security.spring.sis.services.impl.JWTUtilityServiceImpl;
import biblioteca.security.spring.sis.services.models.dtos.LoginDTO;
import biblioteca.security.spring.sis.services.models.dtos.outputDTO.UserTokenOutPutDTO;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class authPersonController {

    @Autowired
    private JWTUtilityServiceImpl jwtUtilityService;

    @Autowired
    private IAuthService authService;

    @GetMapping("/loginpersonalinterno")
    public ResponseEntity<UserTokenOutPutDTO> loginPrivateAccess(@RequestBody LoginDTO loginRequest, @RequestHeader("Authorization") String authorizationHeader) throws Exception {
        // Extraer el token JWT del encabezado Authorization
        String token = authorizationHeader.replace("Bearer ", "");
        JWTClaimsSet claimsSet = jwtUtilityService.extractUserDataFromJWT(token);
        Long userIdFromJwt = Long.parseLong(claimsSet.getSubject());
        UserTokenOutPutDTO userTokenOutPutDTO = authService.loginUserWithOneRole(loginRequest, userIdFromJwt);
        if (userTokenOutPutDTO.getError() != 1) {
            return new ResponseEntity<>(userTokenOutPutDTO, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(userTokenOutPutDTO, HttpStatus.UNAUTHORIZED);
        }
    }
}
