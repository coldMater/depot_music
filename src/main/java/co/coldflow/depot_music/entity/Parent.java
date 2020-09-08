package co.coldflow.depot_music.entity;

import co.coldflow.depot_music.entity.Base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Parent extends BaseEntity {
    @Column(unique = true)
    private String name;
    private String tel;

    @OneToOne
    @JoinColumn(name="account_id")
    private Account account;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
