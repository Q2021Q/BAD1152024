package biblioteca.biblio.spring.sis.services.models.dtos.requestDTO;

import biblioteca.biblio.spring.sis.persistence.entities.*;
import jakarta.persistence.*;

import java.sql.Blob;

public class LibroRequestTDO {

    private Long idLibro;

    private String titulo;

    private String fechapuBlicacion;

    private String fechaAdQuisicion;

    private String codigoISBN;

    private String descripcion;

    private String edicion;

    private Integer numPaginas;

    private Integer eliminado;

    private Long idCategoria;

    private Integer idEditorial;

    private Integer idIdioma;

    private Integer idAutor;

    private Long idRecursoDigital;

    private String rutaAcceso;

    private Blob archivoDigitalData;

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

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdEditorial() {
        return idEditorial;
    }

    public void setIdEditorial(Integer idEditorial) {
        this.idEditorial = idEditorial;
    }

    public Integer getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(Integer idIdioma) {
        this.idIdioma = idIdioma;
    }

    public Integer getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Integer idAutor) {
        this.idAutor = idAutor;
    }

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
