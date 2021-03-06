package co.coldflow.depot_music.entity;

import co.coldflow.depot_music.entity.Base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student extends BaseEntity {
    private String name;
    private LocalDate birthDate;
    private String tel;
    private String email;
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EStudentType studentType = EStudentType.ADULT;

    @ManyToOne
    @JoinColumn(name="parent")
    private Parent parent;

    @OneToOne
    @JoinColumn(name="account_id", nullable = false)
    private Account account;

    @ManyToMany(mappedBy = "students")
    private List<Instructor> instructors  = new ArrayList<>();

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

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }
}
