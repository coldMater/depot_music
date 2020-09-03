package co.coldflow.depot_music.entity;

import co.coldflow.depot_music.entity.Base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Report extends BaseEntity {
    private String name;
    private LocalDateTime classTime;
    private int runningTime;
    private String program;
    private String subject;
    private String result;

    @Enumerated(EnumType.STRING)
    private EStudentType studentType;

    @ManyToOne
    @JoinColumn(name="instructor_id")
    private Instructor instructor;

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;

    @OneToMany(mappedBy = "report")
    private List<Comment> comment = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getClassTime() {
        return classTime;
    }

    public void setClassTime(LocalDateTime classTime) {
        this.classTime = classTime;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public EStudentType getStudentType() {
        return studentType;
    }

    public void setStudentType(EStudentType studentType) {
        this.studentType = studentType;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }
}
