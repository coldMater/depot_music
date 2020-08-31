package co.coldflow.depot_music.web.dto.json_response;

import org.springframework.validation.FieldError;

import java.util.List;

public class CustomResponse {
    private String data;
    private List<FieldError> errors;

    public CustomResponse(String data, List<FieldError> errors) {
        this.data = data;
        this.errors = errors;
    }

    public CustomResponse(List<FieldError> errors) {
        this.data = null;
        this.errors = errors;
    }

    public CustomResponse() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<FieldError> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldError> errors) {
        this.errors = errors;
    }
}
