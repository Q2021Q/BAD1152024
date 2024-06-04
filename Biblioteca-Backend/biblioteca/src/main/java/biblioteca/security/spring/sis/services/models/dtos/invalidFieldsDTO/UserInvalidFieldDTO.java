package biblioteca.security.spring.sis.services.models.dtos.invalidFieldsDTO;

public class UserInvalidFieldDTO {
    private Boolean idUser;
    private Boolean gmail;
    private Boolean firstName;
    private Boolean firstLastName;
    private Boolean secondName;
    private Boolean secondLastName;

    public UserInvalidFieldDTO(){
        this.idUser = false;
        this.gmail = false;
        this.firstName = false;
        this.firstLastName = false;
        this.secondName = false;
        this.secondLastName = false;
    }

    public Boolean getIdUser() {
        return idUser;
    }

    public void setIdUser(Boolean idUser) {
        this.idUser = idUser;
    }

    public Boolean getGmail() {
        return gmail;
    }

    public void setGmail(Boolean gmail) {
        this.gmail = gmail;
    }

    public Boolean getFirstName() {
        return firstName;
    }

    public void setFirstName(Boolean firstName) {
        this.firstName = firstName;
    }

    public Boolean getFirstLastName() {
        return firstLastName;
    }

    public void setFirstLastName(Boolean firstLastName) {
        this.firstLastName = firstLastName;
    }

    public Boolean getSecondName() {
        return secondName;
    }

    public void setSecondName(Boolean secondName) {
        this.secondName = secondName;
    }

    public Boolean getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(Boolean secondLastName) {
        this.secondLastName = secondLastName;
    }
}
