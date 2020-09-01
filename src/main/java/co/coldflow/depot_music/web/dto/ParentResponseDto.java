package co.coldflow.depot_music.web.dto;

import co.coldflow.depot_music.entity.Parent;

public class ParentResponseDto {
    private Long id;
    private String name;
    private String tel;

    public ParentResponseDto(Long id, String name, String tel) {
        this.id = id;
        this.name = name;
        this.tel = tel;
    }

    public ParentResponseDto(Parent parent){
        this.id = parent.getId();
        this.name = parent.getName();
        this.tel = parent.getTel();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
