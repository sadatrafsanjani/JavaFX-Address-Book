package com.rafsanjani.address.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phones")
public class Phone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "official")
    private String official;
    @Column(name = "mobile")
    private String mobile;

    public Phone() {
    }

    public Phone(String official, String mobile) {
        this.official = official;
        this.mobile = mobile;
    }

    public Phone(int id, String official, String mobile) {
        this.id = id;
        this.official = official;
        this.mobile = mobile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOfficial() {
        return official;
    }

    public void setOfficial(String official) {
        this.official = official;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "Phone{" + "id="
                + id + ", official="
                + official + ", mobile="
                + mobile + '}';
    }
}
