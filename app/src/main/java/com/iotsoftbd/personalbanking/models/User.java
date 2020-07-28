package com.iotsoftbd.personalbanking.models;

import java.io.Serializable;

public class User implements Serializable {

    private String email;
    private String password;
    private String profession;
    private String userDOB;
    private String userNid;
    private String phn;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, String profession, String userDOB, String userNid, String phn) {
        this.email = email;
        this.password = password;
        this.profession = profession;
        this.userDOB = userDOB;
        this.userNid = userNid;
        this.phn = phn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getUserDOB() {
        return userDOB;
    }

    public void setUserDOB(String userDOB) {
        this.userDOB = userDOB;
    }

    public String getUserNid() {
        return userNid;
    }

    public void setUserNid(String userNid) {
        this.userNid = userNid;
    }

    public String getPhn() {
        return phn;
    }

    public void setPhn(String phn) {
        this.phn = phn;
    }
}
