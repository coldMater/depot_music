package co.coldflow.depot_music.dto;

public class ChangePasswordResponseDto {
    private EResponseStatus Status;
    private String message;

    public ChangePasswordResponseDto(EResponseStatus status, String message) {
        Status = status;
        this.message = message;
    }

    public EResponseStatus getStatus() {
        return Status;
    }

    public String getMessage() {
        return message;
    }
}
