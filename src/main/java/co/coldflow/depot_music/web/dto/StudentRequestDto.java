package co.coldflow.depot_music.web.dto;

import co.coldflow.depot_music.entity.EStudentType;

public class StudentRequestDto {
    private String name;
    private String birthDate;
    private String tel;
    private String email;
    private String address;
    private EStudentType studentType;
    private Long parentId;

    public StudentRequestDto(String name, String birthDate, String tel, String email, String address, EStudentType studentType, Long parentId) {
        this.name = name;
        this.birthDate = birthDate;
        this.tel = tel;
        this.email = email;
        this.address = address;
        this.studentType = studentType;
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public EStudentType getStudentType() {
        return studentType;
    }

    public void setStudentType(EStudentType studentType) {
        this.studentType = studentType;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
