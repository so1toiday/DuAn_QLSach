package com.example.duan_qlsach.model;

public class HoaDon {
    int mahd;
    String ngay;
    int tongtien;

    public HoaDon(int mahd, String ngay, int tongtien) {
        this.mahd = mahd;
        this.ngay = ngay;
        this.tongtien = tongtien;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public HoaDon() {
    }

    public int getMahd() {
        return mahd;
    }

    public void setMahd(int mahd) {
        this.mahd = mahd;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }
}
