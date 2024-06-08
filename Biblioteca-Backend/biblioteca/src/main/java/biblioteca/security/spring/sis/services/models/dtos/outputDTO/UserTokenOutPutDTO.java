package biblioteca.security.spring.sis.services.models.dtos.outputDTO;

import biblioteca.roles.spring.sis.persistence.entities.RoleEntity;

import java.util.List;

public class UserTokenOutPutDTO {
    private String firstName;
    private String firstLastName;
    private String token;
    private String message;
    private int error;
    private List<RoleEntity> userAllRole;
    private String claveRol;

   public UserTokenOutPutDTO(){
        this.setError(0);
       this.setMessage("Successful");
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public List<RoleEntity> getUserAllRole() {
        return userAllRole;
    }

    public void setUserAllRole(List<RoleEntity> userAllRole) {
        this.userAllRole = userAllRole;
    }

    public String getClaveRol() {
        return claveRol;
    }

    public void setClaveRol(String claveRol) {
        this.claveRol = claveRol;
    }
}
