package biblioteca.roles.spring.sis.services.models.dtos.requestDTO;

public class UserRolRDTO {
    private Long idUser;
    private Integer idRol;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }
}
