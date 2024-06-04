package biblioteca.roles.spring.sis.services.models.dtos;


public class ResponseRolDTO<ResponseEntity> {
    private int numOfErrors;
    private String message;
    private String errorCode;
    //private T entityData;

    private ResponseEntity data;
    private ResponseEntity dataError;

    public ResponseRolDTO(){
        this.setMessage("Successful");
        this.setNumOfErrors(0);
    }

    public int getNumOfErrors() {
        return numOfErrors;
    }

    public void setNumOfErrors(int numOfErrors) {
        this.numOfErrors = numOfErrors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public ResponseEntity getData() {
        return data;
    }

    public void setData(ResponseEntity data) {
        this.data = data;
    }

    public ResponseEntity getDataError() {
        return dataError;
    }

    public void setDataError(ResponseEntity dataError) {
        this.dataError = dataError;
    }
}
