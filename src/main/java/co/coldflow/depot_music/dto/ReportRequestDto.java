package co.coldflow.depot_music.dto;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;

public class ReportRequestDto {
    @NotNull(message = "수강생을 선택해주세요.")
    private Long studentId;

    @NotBlank(message = "프로그램명을 입력해주세요.")
    private String program;
    @NotBlank(message = "주제를 입력해주세요.")
    private String subject;
    @NotBlank(message = "결과를 입력해주세요.")
    private String result;

    @Min(value = 1, message = "수업시간은 1분보다 작을 수 없습니다.")
    @Max(value=1000, message = "수업시간이 너무 길게 입력되었니다.")
    @NumberFormat
    private Integer runningTime;

    public ReportRequestDto(Long studentId, @NotBlank(message = "프로그램명을 입력해주세요.") String program, @NotBlank(message = "주제를 입력해주세요.") String subject, @NotBlank(message = "결과를 입력해주세요.") String result, @NotEmpty @Min(value = 1, message = "수업시간은 1분보다 작을 수 없습니다.") @Max(value = 1000, message = "수업시간이 너무 길게 입력되었니다.") Integer runningTime) {
        this.studentId = studentId;
        this.program = program;
        this.subject = subject;
        this.result = result;
        this.runningTime = runningTime;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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

    public Integer getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(Integer runningTime) {
        this.runningTime = runningTime;
    }
}
