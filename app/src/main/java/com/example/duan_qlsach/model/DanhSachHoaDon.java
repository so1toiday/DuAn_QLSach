package com.example.duan_qlsach.model;
//lấy danh sách hóa đơn đê up lên list
public class DanhSachHoaDon {
    int mahd, soluong, tongtien;
    String ngay, sach;

    public int getMahd() {
        return mahd;
    }

    public void setMahd(int mahd) {
        this.mahd = mahd;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getSach() {
        return sach;
    }

    public void setSach(String sach) {
        this.sach = sach;
    }

    public DanhSachHoaDon() {
    }

    public DanhSachHoaDon(int mahd, int soluong, int tongtien, String ngay, String sach) {
        this.mahd = mahd;
        this.soluong = soluong;
        this.tongtien = tongtien;
        this.ngay = ngay;
        this.sach = sach;
    }
}
