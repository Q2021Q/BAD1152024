package biblioteca.roles.spring.sis.persistence.entities;

import biblioteca.security.spring.sis.persistence.entities.UserEntity;
import jakarta.persistence.*;
import org.apache.catalina.Role;
import org.apache.catalina.User;

import java.io.Serializable;

@Entity
@Table(name = "userrole")
public class UserRoleEntity {

    @EmbeddedId
    private UserRoleId idUserRole;

    @ManyToOne
    @JoinColumn(name = "iduser", insertable = false, updatable = false)
    @MapsId("idUser")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "idrole", insertable = false, updatable = false)
    @MapsId("idRole")
    private RoleEntity role;

    public UserRoleEntity() {
    }

    public UserRoleEntity(Long idUser, Integer idRole) {
        this.idUserRole = new UserRoleId(idUser, idRole);
    }

    public UserRoleId getIdUserRole() {
        return idUserRole;
    }

    public void setIdUserRole(UserRoleId idUserRole) {
        this.idUserRole = idUserRole;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }
}
