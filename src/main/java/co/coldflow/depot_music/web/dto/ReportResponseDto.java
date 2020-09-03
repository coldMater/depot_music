package co.coldflow.depot_music.web.dto;

import co.coldflow.depot_music.entity.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReportResponseDto {
    private long id;
    private long instructorId;
    private String instructorName;
    private long studentId;
    private String studentName;
    private EStudentType studentType;
    private long parentId;
    private String parentName;

    private String classDate;
    private String classTime;
    private int runningTime;

    private String program;
    private String subject;
    private String result;

    private List<CommentResponseDto> comments = new ArrayList<>();

    public ReportResponseDto(Report report) {
        Instructor instructor = report.getInstructor();
        Student student = report.getStudent();
        Parent parent = student != null ? student.getParent() : null;

        this.id = report.getId();
        this.instructorId = instructor!=null?instructor.getId():-1;
        this.instructorName = instructor!=null?instructor.getRealName():"-";
        this.studentId = student!=null?student.getId():-1;
        this.studentName = student!=null?student.getName():"-";
        this.studentType = student!=null?student.getStudentType():EStudentType.ADULT;
        this.parentId = parent!=null?parent.getId():-1;
        this.parentName = parent!=null?parent.getName():"-";
        this.classDate = report.getClassTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.classTime = report.getClassTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        this.runningTime = report.getRunningTime();
        this.program = report.getProgram();
        this.subject = report.getSubject();
        this.result = report.getResult();

        for(Comment comment: report.getComment()){
            this.comments.add(new CommentResponseDto(comment));
        }
    }

    public long getId() {
        return id;
    }

    public long getInstructorId() {
        return instructorId;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public long getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public EStudentType getStudentType() {
        return studentType;
    }

    public long getParentId() {
        return parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public String getClassDate() {
        return classDate;
    }

    public String getClassTime() {
        return classTime;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public String getProgram() {
        return program;
    }

    public String getSubject() {
        return subject;
    }

    public String getResult() {
        return result;
    }

    public List<CommentResponseDto> getComments() {
        return comments;
    }
}
