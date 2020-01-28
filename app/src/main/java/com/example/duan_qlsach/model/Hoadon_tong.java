package com.example.duan_qlsach.model;
// để lấy danh sách hóa đơn
public class Hoadon_tong {
    int mahd;
    String chuoi;
    String ngay;
    int tongtien;

    public Hoadon_tong(int mahd, String chuoi, String ngay, int tongtien) {
        this.mahd = mahd;
        this.chuoi = chuoi;
        this.ngay = ngay;
        this.tongtien = tongtien;
    }

    public Hoadon_tong() {
    }

    public int getMahd() {
        return mahd;
    }

    public void setMahd(int mahd) {
        this.mahd = mahd;
    }

    public String getChuoi() {
        return chuoi;
    }

    public void setChuoi(String chuoi) {
        this.chuoi = chuoi;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }
}
