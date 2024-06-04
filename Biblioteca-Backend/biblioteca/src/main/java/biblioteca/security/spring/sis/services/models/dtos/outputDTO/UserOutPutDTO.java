package biblioteca.security.spring.sis.services.models.dtos.outputDTO;

public class UserOutPutDTO {

    private String gmail;
    private String firstName;
    private String firstLastName;

    public UserOutPutDTO(){

    }


    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }
}
