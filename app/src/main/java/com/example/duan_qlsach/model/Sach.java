package com.example.duan_qlsach.model;

public class Sach {
    int masach;
    int gia;
    int soluong;
    int anh;
    int matl;
    String tensach,nxb,tacgia;


    public Sach(int masach, int gia, int soluong, int anh, int matl, String tensach, String nxb, String tacgia) {
        this.masach = masach;
        this.gia = gia;
        this.soluong = soluong;
        this.anh = anh;
        this.matl = matl;
        this.tensach = tensach;
        this.nxb = nxb;
        this.tacgia = tacgia;
    }

    public int getMatl() {
        return matl;
    }

    public void setMatl(int matl) {
        this.matl = matl;
    }

    public Sach() {
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getAnh() {
        return anh;
    }

    public void setAnh(int anh) {
        this.anh = anh;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }



    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }
}
