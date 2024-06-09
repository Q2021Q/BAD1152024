package biblioteca.biblio.spring.sis.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "libro")
public class LibroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlibro")
    private Long idLibro;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "fechapublicacion")
    private String fechapuBlicacion;

    @Column(name = "fechaadquisicion")
    private String fechaAdQuisicion;

    @Column(name = "codigoisbn")
    private String codigoISBN;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "edicion")
    private String edicion;

    @Column(name = "numpaginas")
    private Integer numPaginas;

    @Column(name = "eliminado")
    private Integer eliminado;

    @ManyToOne
    @JoinColumn(name = "idcategoria")
    private CategoriaRecursoEntity categoria;

    @ManyToOne
    @JoinColumn(name = "ideditorial")
    private EditorialEntity editorial;

    @ManyToOne
    @JoinColumn(name = "ididioma")
    private IdiomaEntity idioma;

    @ManyToOne
    @JoinColumn(name = "idautor")
    private AutorEntity autor;

    @ManyToOne
    @JoinColumn(name = "idrecursodigital")
    private RecursoDigitalEntity recursoDigital;

    public Long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Long idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFechapuBlicacion() {
        return fechapuBlicacion;
    }

    public void setFechapuBlicacion(String fechapuBlicacion) {
        this.fechapuBlicacion = fechapuBlicacion;
    }

    public String getFechaAdQuisicion() {
        return fechaAdQuisicion;
    }

    public void setFechaAdQuisicion(String fechaAdQuisicion) {
        this.fechaAdQuisicion = fechaAdQuisicion;
    }

    public String getCodigoISBN() {
        return codigoISBN;
    }

    public void setCodigoISBN(String codigoISBN) {
        this.codigoISBN = codigoISBN;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public Integer getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(Integer numPaginas) {
        this.numPaginas = numPaginas;
    }

    public Integer getEliminado() {
        return eliminado;
    }

    public void setEliminado(Integer eliminado) {
        this.eliminado = eliminado;
    }

    public CategoriaRecursoEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaRecursoEntity categoria) {
        this.categoria = categoria;
    }

    public EditorialEntity getEditorial() {
        return editorial;
    }

    public void setEditorial(EditorialEntity editorial) {
        this.editorial = editorial;
    }

    public IdiomaEntity getIdioma() {
        return idioma;
    }

    public void setIdioma(IdiomaEntity idioma) {
        this.idioma = idioma;
    }

    public AutorEntity getAutor() {
        return autor;
    }

    public void setAutor(AutorEntity autor) {
        this.autor = autor;
    }

    public RecursoDigitalEntity getRecursoDigital() {
        return recursoDigital;
    }

    public void setRecursoDigital(RecursoDigitalEntity recursoDigital) {
        this.recursoDigital = recursoDigital;
    }
}
