package co.coldflow.depot_music.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class ParentRequestDto {
    @NotEmpty
    @NotBlank
    private String name;
    @NotEmpty
    @NotBlank
    private String tel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public ParentRequestDto(@NotEmpty @NotBlank String name, @NotEmpty @NotBlank String tel) {
        this.name = name;
        this.tel = tel;
    }
}
