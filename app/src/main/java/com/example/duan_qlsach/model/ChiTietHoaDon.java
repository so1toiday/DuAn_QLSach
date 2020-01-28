package com.example.duan_qlsach.model;
//model của danh sách hóa đơn chi tiết khi nhấn thêm giỏ hàng
public class ChiTietHoaDon {
    int mahdct;
    int soluong,masach,mahd;
    int tongtien;
    String tensach;

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public ChiTietHoaDon(int mahdct, int soluong, int masach, int mahd, int tongtien) {
        this.mahdct = mahdct;
        this.soluong = soluong;
        this.masach = masach;
        this.mahd = mahd;
        this.tongtien = tongtien;
    }

    public int getMahd() {
        return mahd;
    }

    public void setMahd(int mahd) {
        this.mahd = mahd;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public int getMahdct() {
        return mahdct;
    }

    public void setMahdct(int mahdct) {
        this.mahdct = mahdct;
    }

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(int mahdct, int soluong, int masach, int mahd) {
        this.mahdct = mahdct;
        this.soluong = soluong;
        this.masach = masach;
        this.mahd = mahd;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
