package co.coldflow.depot_music.dto;

import co.coldflow.depot_music.entity.EStudentType;
import co.coldflow.depot_music.entity.Student;

public class StudentResponseDto {
    private Long id;
    private String name;
    private String birthDate;
    private String tel;
    private String email;
    private String address;
    private EStudentType studentType;
    private ParentResponseDto parent;

    public StudentResponseDto(Long id, String name, String birthDate, String tel, String email, String address, EStudentType studentType) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.tel = tel;
        this.email = email;
        this.address = address;
        this.studentType = studentType;
    }

    public StudentResponseDto(Long id, String name, String birthDate, String tel, String email, String address, EStudentType studentType, ParentResponseDto parent) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.tel = tel;
        this.email = email;
        this.address = address;
        this.studentType = studentType;
        this.parent = parent;
    }

    public StudentResponseDto(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.birthDate = student.getBirthDate().toString();
        this.tel = student.getTel();
        this.email = student.getEmail();
        this.address = student.getAddress();
        this.studentType = student.getStudentType();
        this.parent = student.getParent() != null ? new ParentResponseDto(student.getParent()) : null;
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

    public ParentResponseDto getParent() {
        return parent;
    }

    public void setParent(ParentResponseDto parent) {
        this.parent = parent;
    }
}
