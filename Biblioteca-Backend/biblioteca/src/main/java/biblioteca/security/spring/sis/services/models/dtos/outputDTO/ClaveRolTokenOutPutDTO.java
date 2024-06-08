package biblioteca.security.spring.sis.services.models.dtos.outputDTO;

public class ClaveRolTokenOutPutDTO {
    private String token;
    private String claveRol;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getClaveRol() {
        return claveRol;
    }

    public void setClaveRol(String claveRol) {
        this.claveRol = claveRol;
    }
}
