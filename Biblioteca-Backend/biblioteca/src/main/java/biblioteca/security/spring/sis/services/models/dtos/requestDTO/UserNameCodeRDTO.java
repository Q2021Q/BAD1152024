package biblioteca.security.spring.sis.services.models.dtos.requestDTO;

public class UserNameCodeRDTO {
    private String email;
    private String codeSentEmail;
    private Long idUserName;

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

    public Long getIdUserName() {
        return idUserName;
    }

    public void setIdUserName(Long idUserName) {
        this.idUserName = idUserName;
    }
}
