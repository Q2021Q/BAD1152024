package biblioteca.security.spring.sis.persistence.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "codigoverificacion")
public class CodigoVerificacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcodigo")
    private Long idCodigo;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "diaactual")
    private String diaActual;

    @Column(name = "horaactual")
    private String horaActual;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iduser")
    private UserEntity iduser;


    public Long getIdCodigo() {
        return idCodigo;
    }

    public void setIdCodigo(Long idCodigo) {
        this.idCodigo = idCodigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public UserEntity getUser() {
        return iduser;
    }

    public void setUser(UserEntity user) {
        this.iduser = user;
    }

    public String getDiaActual() {
        return diaActual;
    }

    public void setDiaActual(String diaActual) {
        this.diaActual = diaActual;
    }

    public String getHoraActual() {
        return horaActual;
    }

    public void setHoraActual(String horaActual) {
        this.horaActual = horaActual;
    }
}
