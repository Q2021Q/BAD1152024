package biblioteca.security.spring.sis.services.models.coreFunctions;


import biblioteca.security.spring.sis.persistence.repositories.CodigoVerificacionRepository;
import biblioteca.security.spring.sis.persistence.repositories.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import biblioteca.security.spring.sis.persistence.entities.CodigoVerificacionEntity;
import biblioteca.security.spring.sis.persistence.entities.UserEntity;
import biblioteca.security.spring.sis.services.impl.EmailService;
import biblioteca.security.spring.sis.services.models.dtos.CodigoVerificacionDTO;
import biblioteca.security.spring.sis.services.models.dtos.ResponseDTO;
import biblioteca.security.spring.sis.services.models.dtos.requestDTO.CodeVerificationRDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;





public class CodeVerificationCore {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CodigoVerificacionRepository codigoVerificacionRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private FechaCore fechaCore;

    @Autowired
    private UserCore userCore;


    public ResponseDTO validateCode(CodigoVerificacionEntity codVerification){
        ResponseDTO response = new ResponseDTO();

        response.setNumOfErrors(0);

        if (codVerification.getCodigo() == null){
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("El código de verificación es requerido");
        }

        return response;
    }

    public boolean checkExistingCode(String email){
        Object emailNoverificado = userRepository.findUserDetailsByEmail(email);
        if (emailNoverificado != null){
            return true;
        }
        System.out.println("registro no encontrado "+ emailNoverificado);

        return false;
    }

    public boolean updateCodeVerification(String email) throws MessagingException {

        //se busca al usuario con el email
        UserEntity userEncontrado = userRepository.findByEmail(email);

        UserEntity user = new UserEntity();


        CodigoVerificacionDTO codidigoVerificacionDTO = new CodigoVerificacionDTO();
        CodigoVerificacionEntity codigoVerificacionEntity = new CodigoVerificacionEntity();

        if (userEncontrado != null){
            user.setId(userEncontrado.getId());
            codigoVerificacionEntity.setCodigo(codidigoVerificacionDTO.getCodigoVerificacion());
            //se le pasa el objeto user de la relacion
            codigoVerificacionEntity.setUser(user);
            String codeSentToEmail = codigoVerificacionEntity.getCodigo();//se obtiene el nuevo codigo
            //se obtiene el registro que se requiere actualizar
            CodigoVerificacionEntity codigoVerToUp = codigoVerificacionRepository.findByIdUser(userEncontrado.getId());
            if (codigoVerToUp != null){
                codigoVerificacionEntity.setIdCodigo(codigoVerToUp.getIdCodigo());
                codigoVerificacionEntity.setDiaActual(fechaCore.getDiaActual());
                codigoVerificacionEntity.setHoraActual(fechaCore.getHoraActual());
                codigoVerificacionRepository.save(codigoVerificacionEntity);
                //se envia el codigo de verificacion al corro electronico
                emailService.sendVerificationCode(userEncontrado.getEmail(), codeSentToEmail);
                //respuesta



                return true;
            }
        }

        return false;
    }

    public ResponseDTO codeSentToEmailVerify(CodeVerificationRDTO codeVerificationRDTO, int minutosDisponibles){
        ResponseDTO responseDTO = new ResponseDTO();
        UserEntity userEntityRDTO = new UserEntity();
        userEntityRDTO.setEmail(codeVerificationRDTO.getEmail());

        if (userCore.emailValidate(userEntityRDTO).getNumOfErrors() == 1){
            return userCore.emailValidate(userEntityRDTO);
        }

        CodigoVerificacionEntity codigoRegistro = codigoVerificacionRepository.findCodeByEmail(codeVerificationRDTO.getEmail());

        if (codigoRegistro == null){
            responseDTO.setNumOfErrors(1);
            responseDTO.setMessage("No se encontró ningún registro del código de verificación con el email proporcionado");
            return responseDTO;
        }

        if (!this.currentDayCode(codigoRegistro.getDiaActual())){//verifica el dia
            responseDTO.setNumOfErrors(1);
            responseDTO.setMessage("El periodo de validez del código a caducado");
            return responseDTO;
        }

        if (!this.currentTimeCode(codigoRegistro.getHoraActual(), minutosDisponibles)){//verifica la hora
            responseDTO.setNumOfErrors(1);
            responseDTO.setMessage("El periodo de validez del código a caducado");
            return responseDTO;
        }
        // compara codigo del request(enviado a gmail) con el codigo en la base de datos
        if (codeVerificationRDTO.getCodeSentEmail() == null){
            responseDTO.setNumOfErrors(1);
            responseDTO.setMessage("Error no se ha proporcionado el código de verificación");
            return responseDTO;
        }

        // compara codigo del request(enviado a gmail) con el codigo en la base de datos
        if (!codeVerificationRDTO.getCodeSentEmail().equals(codigoRegistro.getCodigo())){
            responseDTO.setNumOfErrors(1);
            responseDTO.setMessage("Error código invalido");
            return responseDTO;
        }

        responseDTO.setNumOfErrors(0);
        return responseDTO;


    }

    // Comparar la fecha de creacion/actualizacion del codigo de feriricacion, si el registro diaActual en la bd es el dia actual retorna true
    public boolean currentDayCode(String diaActualBd){

        // Convertir las cadenas de fecha al formato LocalDate
        LocalDate diaActualDateBd = LocalDate.parse(diaActualBd, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate diaActual = LocalDate.parse(fechaCore.getDiaActual(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        boolean sonIguales = diaActualDateBd.isEqual(diaActual);

        if (sonIguales) {
            return true;
        }

        return false;
    }

    // Comparar la hora de creacion/actualizacion del codigo de feriricacion, si el registr
    public boolean currentTimeCode(String horaBd, int minutosValidos){

        // Parsea la hora actual
        LocalTime horaActualBd = LocalTime.parse(horaBd, DateTimeFormatter.ofPattern("HH:mm:ss"));

        // el codigo enviado al correo solo tendra 2 minutos de validez
        String validTime = horaActualBd.plusMinutes(minutosValidos).toString();//suma dos minutos

        // Límite inferior en formato "00:00:00"
        LocalTime limiteInferior = LocalTime.parse(horaBd, DateTimeFormatter.ofPattern("HH:mm:ss"));

        // Límite superior en formato "00:00:00"
        LocalTime limiteSuperior = LocalTime.parse(validTime, DateTimeFormatter.ofPattern("HH:mm:ss"));

        // Se usa la hora actual para verificar si el codigo esta dentro de la hora validad (solo tiene dos minutos de valides)
        LocalTime horaActual = LocalTime.parse(fechaCore.getHoraActual(), DateTimeFormatter.ofPattern("HH:mm:ss"));
        // Valida si la hora actual está dentro del rango
        if (horaActual.isAfter(limiteInferior) && horaActual.isBefore(limiteSuperior)) {
            return true;
        }

        System.out.println("hora valida " + validTime);

        return false;
    }

}
