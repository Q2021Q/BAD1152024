package biblioteca.biblio.spring.sis.persistence.entities;

import jakarta.persistence.*;

import java.sql.Blob;

@Entity
@Table(name = "recursodigital")
public class RecursoDigitalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrecursodigital")
    private Long idRecursoDigital;

    @Column(name = "rutaacceso")
    private String rutaAcceso;

    @Column(name = "archivodigitaldata")
    private Blob archivoDigitalData;

    public Long getIdRecursoDigital() {
        return idRecursoDigital;
    }

    public void setIdRecursoDigital(Long idRecursoDigital) {
        this.idRecursoDigital = idRecursoDigital;
    }

    public String getRutaAcceso() {
        return rutaAcceso;
    }

    public void setRutaAcceso(String rutaAcceso) {
        this.rutaAcceso = rutaAcceso;
    }

    public Blob getArchivoDigitalData() {
        return archivoDigitalData;
    }

    public void setArchivoDigitalData(Blob archivoDigitalData) {
        this.archivoDigitalData = archivoDigitalData;
    }
}
