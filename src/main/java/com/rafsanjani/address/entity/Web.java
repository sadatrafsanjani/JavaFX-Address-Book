package com.rafsanjani.address.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "webs")
public class Web implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "email")
    private String email;
    @Column(name = "website")
    private String website;
    @Column(name = "linkedin")
    private String linkedin;
    @Column(name = "facebook")
    private String facebook;

    public Web() {
    }

    public Web(String email, String website, String linkedin, String facebook) {
        this.email = email;
        this.website = website;
        this.linkedin = linkedin;
        this.facebook = facebook;
    }

    public Web(int id, String email, String website, String linkedin, String facebook) {
        this.id = id;
        this.email = email;
        this.website = website;
        this.linkedin = linkedin;
        this.facebook = facebook;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    @Override
    public String toString() {
        return "Web{" + "id=" + 
                id + ", email=" + 
                email + ", website=" + 
                website + ", linkedin=" + 
                linkedin + ", facebook=" + 
                facebook + '}';
    }
}
