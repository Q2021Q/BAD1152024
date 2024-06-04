package biblioteca.security.spring.sis.services.models.dtos;

import java.util.Random;

public class CodigoVerificacionDTO {
    private String codigoVerificacion;
    // Constructor
    public CodigoVerificacionDTO() {
        setCodigoVerificacion();
    }
    public String getCodigoVerificacion() {
        return codigoVerificacion;
    }

    private void setCodigoVerificacion() {
        final int LONGITUD_CODIGO = 6;
        String caracteres = "0123456789";
        Random random = new Random();
        StringBuilder codigoBuilder = new StringBuilder(LONGITUD_CODIGO);
        for (int i = 0; i < LONGITUD_CODIGO; i++) {
            int indice = random.nextInt(caracteres.length());
            codigoBuilder.append(caracteres.charAt(indice));
        }
        this.codigoVerificacion = codigoBuilder.toString();
    }
}
