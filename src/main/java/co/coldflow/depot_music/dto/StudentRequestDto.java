package co.coldflow.depot_music.dto;

import co.coldflow.depot_music.entity.EStudentType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class StudentRequestDto {
    @NotEmpty(message = "이름은 비어있을 수 없습니다.")
    private String name;

    @Past(message = "생년월일은 현재 날짜 이전이어야 합니다.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    @NotEmpty(message = "전화번호는 비어있을 수 없습니다. '-'를 사용해주세요.")
    private String tel;

    @Email
    private String email;

    private String address;
    private EStudentType studentType;
    private Long parentId;

    public StudentRequestDto(String name, LocalDate birthDate, String tel, String email, String address, EStudentType studentType, Long parentId) {
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
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
