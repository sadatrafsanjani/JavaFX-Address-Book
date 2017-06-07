package com.rafsanjani.address.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "generals")
public class General implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "phoneId")
    private Phone phone;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "webId")
    private Web web;
    @Column(name = "title")
    private String title;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "organization")
    private String organization;
    @Column(name = "address")
    private String address;
    @Column(name = "picture")
    private byte[] picture;

    public General() {
    }

    public General(int id, Phone phone, Web web, String title, 
            String firstName, String lastName, String organization, String address, byte[] picture) {
        this.id = id;
        this.phone = phone;
        this.web = web;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.organization = organization;
        this.address = address;
        this.picture = picture;
    }

    public General(Phone phone, Web web, String title, String firstName, 
            String lastName, String organization, String address, byte[] picture) {
        this.phone = phone;
        this.web = web;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.organization = organization;
        this.address = address;
        this.picture = picture;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Web getWeb() {
        return web;
    }

    public void setWeb(Web web) {
        this.web = web;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
