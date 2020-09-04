package co.coldflow.depot_music.entity;

import co.coldflow.depot_music.entity.Base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Account extends BaseEntity {
    @Column(unique=true)
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private EUserRole role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EUserRole getRole() {
        return role;
    }

    public void setRole(EUserRole role) {
        this.role = role;
    }
}
