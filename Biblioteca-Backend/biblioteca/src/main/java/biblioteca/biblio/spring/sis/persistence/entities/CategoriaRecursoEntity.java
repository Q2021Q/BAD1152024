package biblioteca.biblio.spring.sis.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "categoriarecurso")
public class CategoriaRecursoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcategoria")
    private Long idCategoriaRecurso;

    @Column(name = "nombrecategoria")
    private String nombreCategoria;

    @Column(name = "descripcioncategoria")
    private String descripcionCategoria;

    public Long getIdCategoriaRecurso() {
        return idCategoriaRecurso;
    }

    public void setIdCategoriaRecurso(Long idCategoriaRecurso) {
        this.idCategoriaRecurso = idCategoriaRecurso;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }
}
