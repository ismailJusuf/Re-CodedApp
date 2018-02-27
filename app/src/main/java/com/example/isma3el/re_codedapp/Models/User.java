package com.example.isma3el.re_codedapp.Models;

/**
 * Created by isma3el on 2/14/2018.
 */

public class User {

    private String id;
    private String fullName;
    private String image;
    private String email;
    private String phoneNumber;
    private String bootcamp;
    private String nationality;
    public int type; //0 for student 1 for teacher

    public User() {

    }

    public User(String id, String fullName, String image, String email, String phoneNumber, String bootcamp, String nationality, int type) {
        this.id = id;
        this.fullName = fullName;
        this.image = image;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bootcamp = bootcamp;
        this.nationality = nationality;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBootcamp() {
        return bootcamp;
    }

    public void setBootcamp(String bootcamp) {
        this.bootcamp = bootcamp;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}