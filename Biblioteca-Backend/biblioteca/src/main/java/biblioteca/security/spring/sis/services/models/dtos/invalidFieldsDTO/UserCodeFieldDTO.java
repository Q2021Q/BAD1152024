package biblioteca.security.spring.sis.services.models.dtos.invalidFieldsDTO;

public class UserCodeFieldDTO {
    private boolean userName;
    private boolean password;

    public UserCodeFieldDTO(){
        this.userName = false;
        this.password = false;
    }

    public boolean isUserName() {
        return userName;
    }

    public void setUserName(boolean userName) {
        this.userName = userName;
    }

    public boolean isPassword() {
        return password;
    }

    public void setPassword(boolean password) {
        this.password = password;
    }
}
