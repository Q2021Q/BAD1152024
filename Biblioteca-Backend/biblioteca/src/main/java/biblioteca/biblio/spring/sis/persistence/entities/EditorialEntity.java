package biblioteca.biblio.spring.sis.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "editorial")
public class EditorialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ideditorial")
    private Integer idEditorial;

    @Column(name = "nombreeditorial")
    private String nombreEditorial;

    @Column(name = "email")
    private String email;

    @Column(name = "telefonoeditorial")
    private String telefonoEditorial;

    public Integer getIdEditorial() {
        return idEditorial;
    }

    public void setIdEditorial(Integer idEditorial) {
        this.idEditorial = idEditorial;
    }

    public String getNombreEditorial() {
        return nombreEditorial;
    }

    public void setNombreEditorial(String nombreEditorial) {
        this.nombreEditorial = nombreEditorial;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefonoEditorial() {
        return telefonoEditorial;
    }

    public void setTelefonoEditorial(String telefonoEditorial) {
        this.telefonoEditorial = telefonoEditorial;
    }
}
