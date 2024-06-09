package biblioteca.biblio.spring.sis.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "idioma")
public class IdiomaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ididioma")
    private Integer idIdioma;

    @Column(name = "nombreidioma")
    private String nombreIdioma;

    @Column(name = "eliminado")
    private Integer eliminado;

    public Integer getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(Integer idIdioma) {
        this.idIdioma = idIdioma;
    }

    public Integer getEliminado() {
        return eliminado;
    }

    public void setEliminado(Integer eliminado) {
        this.eliminado = eliminado;
    }

    public String getNombreIdioma() {
        return nombreIdioma;
    }

    public void setNombreIdioma(String nombreIdioma) {
        this.nombreIdioma = nombreIdioma;
    }
}
