package biblioteca.roles.spring.sis.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "rol")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrole")
    private Integer idRole;

    @Column(name = "rolecode")
    private String roleCode;

    @Column(name = "rolename")
    private String roleName;

    public RoleEntity(){

    }

    public RoleEntity(Integer idRole){
        this.setIdRole(idRole);
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
