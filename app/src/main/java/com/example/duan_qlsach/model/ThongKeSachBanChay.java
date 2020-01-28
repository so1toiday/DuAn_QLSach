package com.example.duan_qlsach.model;

public class ThongKeSachBanChay {
    int masach;
    String tensach;



    String ngay;
    int soluong;

    public ThongKeSachBanChay(int masach, String tensach, String ngay, int soluong) {
        this.masach = masach;
        this.tensach = tensach;
        this.ngay = ngay;
        this.soluong = soluong;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public ThongKeSachBanChay() {
    }
}
