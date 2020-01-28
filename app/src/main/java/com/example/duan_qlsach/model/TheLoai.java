package com.example.duan_qlsach.model;

public class TheLoai {
    int matl;
    String tentl;
    String vitri;
    String mota;

    public TheLoai(int matl, String tentl, String vitri, String mota) {
        this.matl = matl;
        this.tentl = tentl;
        this.vitri = vitri;
        this.mota = mota;
    }

    public String getVitri() {
        return vitri;
    }

    public void setVitri(String vitri) {
        this.vitri = vitri;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public TheLoai() {
    }

    public int getMatl() {
        return matl;
    }

    public void setMatl(int matl) {
        this.matl = matl;
    }

    public String getTentl() {
        return tentl;
    }

    public void setTentl(String tentl) {
        this.tentl = tentl;
    }
}
