package co.coldflow.depot_music.web.dto;

import co.coldflow.depot_music.entity.BasicInformation;

public class BasicInformationRequestDto {
    private String title;
    private String tel;
    private String address;
    private String email;
    private String chiefName;
    private String chiefTel;

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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setChiefName(String chiefName) {
        this.chiefName = chiefName;
    }

    public void setChiefTel(String chiefTel) {
        this.chiefTel = chiefTel;
    }
}
