package biblioteca.security.spring.sis.services.models.dtos.requestDTO;

public class CodeVerificationRDTO {
    private String email;
    private String codeSentEmail;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodeSentEmail() {
        return codeSentEmail;
    }

    public void setCodeSentEmail(String codeSentEmail) {
        this.codeSentEmail = codeSentEmail;
    }
}
