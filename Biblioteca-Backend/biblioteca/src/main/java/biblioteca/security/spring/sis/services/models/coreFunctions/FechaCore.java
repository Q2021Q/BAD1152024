package biblioteca.security.spring.sis.services.models.coreFunctions;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class FechaCore {

    public String getHoraActual(){
        // Obtener la hora actual en la zona horaria de El Salvador
        ZoneId zonaHorariaElSalvador = ZoneId.of("America/El_Salvador");
        ZonedDateTime horaActual = ZonedDateTime.now(zonaHorariaElSalvador);

        // Formatear la hora según un patrón específico
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaFormateada = horaActual.format(formatoHora);

        return horaFormateada;
    }

    public String getDiaActual(){
        // Obtener la fecha actual en El Salvador
        ZoneId zonaHorariaElSalvador = ZoneId.of("America/El_Salvador");
        LocalDate fechaActual = LocalDate.now(zonaHorariaElSalvador);

        // Formatear la fecha según el patrón "dd/MM/yyyy"
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = fechaActual.format(formatoFecha);

        return fechaFormateada;
    }
}
