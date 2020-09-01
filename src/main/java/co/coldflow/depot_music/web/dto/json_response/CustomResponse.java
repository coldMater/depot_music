package co.coldflow.depot_music.web.dto.json_response;

import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public class CustomResponse {
    private Object object;
    private List<FieldError> errors;

    public CustomResponse(Object object, List<FieldError> errors) {
        this.object = object;
        this.errors = errors;
    }

    public CustomResponse(List<FieldError> errors) {
        this.object = null;
        this.errors = errors;
    }

    public CustomResponse(Object object) {
        this.object = object;
        this.errors = new ArrayList();
    }

    public CustomResponse() {
    }

    public Object getData() {
        return object;
    }

    public void setData(String data) {
        this.object = data;
    }

    public List<FieldError> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldError> errors) {
        this.errors = errors;
    }
}
