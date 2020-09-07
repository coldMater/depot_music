package co.coldflow.depot_music.dto;

public class ChangePasswordRequestDto {
    private String prevPassword;
    private String newPassword;
    private String checkPassword;

    public ChangePasswordRequestDto(String prevPassword, String newPassword, String checkPassword) {
        this.prevPassword = prevPassword;
        this.newPassword = newPassword;
        this.checkPassword = checkPassword;
    }

    public String getPrevPassword() {
        return prevPassword;
    }

    public void setPrevPassword(String prevPassword) {
        this.prevPassword = prevPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getCheckPassword() {
        return checkPassword;
    }

    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
    }
}
