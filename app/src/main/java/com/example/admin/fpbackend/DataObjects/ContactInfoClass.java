package com.example.admin.fpbackend.DataObjects;

/**
 * Created by Admin on 10/12/2017.
 */

public class ContactInfoClass {
    int id;
    String name;
    String skype;
    String phone;
    String email;
    String position;
    String refrence;

    public ContactInfoClass(int id, String name, String skype, String phone, String email, String position, String refrence) {
        this.id = id;
        this.name = name;
        this.skype = skype;
        this.phone = phone;
        this.email = email;
        this.position = position;
        this.refrence = refrence;
    }

    public ContactInfoClass() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRefrence() {
        return refrence;
    }

    public void setRefrence(String refrence) {
        this.refrence = refrence;
    }
}
