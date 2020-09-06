package co.coldflow.depot_music.entity;

import co.coldflow.depot_music.entity.Base.BaseEntity;

import javax.persistence.Entity;

@Entity
public class BasicInformation extends BaseEntity {
    private String title;
    private String tel;
    private String address;
    private String email;
    private String chiefName;
    private String chiefTel;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChiefName() {
        return chiefName;
    }

    public void setChiefName(String chiefName) {
        this.chiefName = chiefName;
    }

    public String getChiefTel() {
        return chiefTel;
    }

    public void setChiefTel(String chiefTel) {
        this.chiefTel = chiefTel;
    }
}
