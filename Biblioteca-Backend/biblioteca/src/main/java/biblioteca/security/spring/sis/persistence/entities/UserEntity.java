package biblioteca.security.spring.sis.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "userss")
public class UserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "verifiedemail")
    private int verifiedemail;

    private String email;
    private String password;

    @OneToOne
    @JoinColumn(name = "idpersonalname")
    private PersonalNameEntity idpersonalname;

    //Costructores
    public UserEntity() {

    }
    public UserEntity(Long idUser) {
        this.setId(idUser);
    }

    public UserEntity(String email, long idUser) {
        this.email = email;
        this.id = idUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int isVerifiedemail() {
        return verifiedemail;
    }

    public void setVerifiedemail(int verifiedemail) {
        this.verifiedemail = verifiedemail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PersonalNameEntity getIdpersonalname() {
        return idpersonalname;
    }

    public void setIdpersonalname(PersonalNameEntity idpersonalname) {
        this.idpersonalname = idpersonalname;
    }
}
