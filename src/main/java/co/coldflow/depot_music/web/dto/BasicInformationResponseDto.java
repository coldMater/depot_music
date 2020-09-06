package co.coldflow.depot_music.web.dto;

import co.coldflow.depot_music.entity.BasicInformation;

public class BasicInformationResponseDto {
    private String title;
    private String tel;
    private String address;
    private String email;
    private String chiefName;
    private String chiefTel;

    public BasicInformationResponseDto(BasicInformation basicInfo) {
        this.title = basicInfo .getTitle();
        this.tel = basicInfo .getTel();
        this.address = basicInfo .getAddress();
        this.email = basicInfo .getEmail();
        this.chiefName = basicInfo .getChiefName();
        this.chiefTel = basicInfo .getChiefTel();
    }

    public String getTitle() {
        return title;
    }

    public String getTel() {
        return tel;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getChiefName() {
        return chiefName;
    }

    public String getChiefTel() {
        return chiefTel;
    }
}
