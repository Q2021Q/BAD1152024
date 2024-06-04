package biblioteca.security.spring.sis.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "personalname")
public class PersonalNameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpersonalname")
    private Long idPerName;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "first_lastname")
    private String firstLastName;
    @Column(name = "second_name")
    private String secondName;
    @Column(name = "second_lastname")
    private String secondLastName;

    public Long getIdPerName() {
        return idPerName;
    }

    public void setIdPerName(Long idPerName) {
        this.idPerName = idPerName;
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

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }
}
