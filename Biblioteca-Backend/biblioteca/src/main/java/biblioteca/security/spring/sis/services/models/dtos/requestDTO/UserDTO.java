package biblioteca.security.spring.sis.services.models.dtos.requestDTO;


import biblioteca.security.spring.sis.persistence.entities.CodigoVerificacionEntity;
import biblioteca.security.spring.sis.persistence.entities.PersonalNameEntity;
import biblioteca.security.spring.sis.persistence.entities.UserEntity;

public class UserDTO {
    private UserEntity userEntity;
    private PersonalNameEntity personalNameEntity;
    private CodigoVerificacionEntity codigoVerificacionEntity;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public PersonalNameEntity getPersonalNameEntity() {
        return personalNameEntity;
    }

    public void setPersonalNameEntity(PersonalNameEntity personalNameEntity) {
        this.personalNameEntity = personalNameEntity;
    }

    public CodigoVerificacionEntity getCodigoVerificacionEntity() {
        return codigoVerificacionEntity;
    }

    public void setCodigoVerificacionEntity(CodigoVerificacionEntity codigoVerificacionEntity) {
        this.codigoVerificacionEntity = codigoVerificacionEntity;
    }
}
