package co.coldflow.depot_music.web.dto;

import co.coldflow.depot_music.entity.EStudentType;

public class StudentResponseDto {
    private Long id;
    private String name;
    private String birthDate;
    private String tel;
    private String email;
    private String address;
    private EStudentType studentType;

    public StudentResponseDto(Long id, String name, String birthDate, String tel, String email, String address, EStudentType studentType) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.tel = tel;
        this.email = email;
        this.address = address;
        this.studentType = studentType;
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
}