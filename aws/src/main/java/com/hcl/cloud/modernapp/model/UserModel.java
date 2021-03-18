package com.hcl.cloud.modernapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class UserModel implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotEmpty(message="{username.not.empty}")
    private String username;
    @NotEmpty(message="{password.not.empty}")
    private String password;
    // @Column(nullable = false)
    // private String passwordConfirm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    // public String getPasswordConfirm() {
    // return passwordConfirm;
    // }

    // public void setPasswordConfirm(String password) {
    // this.passwordConfirm = password;
    // }
}
