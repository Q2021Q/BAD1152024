package biblioteca.security.spring.sis.services.models.dtos.requestDTO;

public class UserCodeRDTO {
    private String userName;
    private String email;
    private String password;
    private String codeSentEmail;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCodeSentEmail() {
        return codeSentEmail;
    }

    public void setCodeSentEmail(String codeSentEmail) {
        this.codeSentEmail = codeSentEmail;
    }
}
