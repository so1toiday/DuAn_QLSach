package com.example.duan_qlsach.model;

public class NguoiDung {
    private String username;
    private String pass;
    private String phone;
    private String fullname;
    private String address;
    public NguoiDung(String username, String pass, String phone, String fullname,String address) {
        this.username = username;
        this.pass = pass;
        this.phone = phone;
        this.fullname = fullname;
        this.address=address;
    }

    public NguoiDung(String username, String pass) {
        this.username = username;
        this.pass = pass;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public NguoiDung() {
        this.username = "no name";
        this.pass = "123456";
        this.phone = "123456";
        this.fullname = "no name";
        this.address="no name";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


}
