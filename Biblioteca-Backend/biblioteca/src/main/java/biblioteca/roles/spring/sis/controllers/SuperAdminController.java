package biblioteca.roles.spring.sis.controllers;

import biblioteca.roles.spring.sis.services.impl.RoleService;
import biblioteca.roles.spring.sis.services.models.dtos.ResponseRolDTO;
import biblioteca.roles.spring.sis.services.models.dtos.requestDTO.UserRolRDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/superadmin")
public class SuperAdminController {

    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
    @PostMapping("/adduserrole")
    ResponseEntity<ResponseRolDTO> addUserRole(@RequestBody UserRolRDTO userRolRDTO){
        return new ResponseEntity<>(roleService.addUserRole(userRolRDTO), HttpStatus.OK);
    }

}
