package com.example.duan_qlsach.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan_qlsach.database.DatabaseHelper;
import com.example.duan_qlsach.model.HoaDon;

import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    DatabaseHelper dt;
    SQLiteDatabase db;
    public static final String SQL = "CREATE TABLE HoaDon(" +
            "mahd INTEGER primary key autoincrement," +
            "ngay TEXT,tongtien int)";
    static final String TABLE_NAME = "HoaDon";

    public HoaDonDAO(Context context) {
        dt = new DatabaseHelper(context);
        db = dt.getWritableDatabase();
    }


    //thêm hóa đơn
    public boolean inserthoadon(HoaDon hd) {
        ContentValues values = new ContentValues();
        values.put("ngay", hd.getNgay());
        values.put("tongtien", hd.getTongtien());
        long a = db.insert(TABLE_NAME, null, values);
        if (a != -1) return true;
        return false;
    }

    //lấy hóa đơn cuối cùng
    public HoaDon getlasthoadon() {
        Cursor c = db.rawQuery("SELECT * from " + TABLE_NAME, null);
        HoaDon hd = new HoaDon();
        if (c.moveToLast()) {
            hd.setMahd(c.getInt(0));
            hd.setNgay(c.getString(1));
            hd.setTongtien(c.getInt(2));
        }
        return hd;
    }


    //lấy tổng tiền theo mã hóa đơn
    public int gettongtien(int mahd) {
        Cursor c = db.rawQuery("SELECT tongtien from " + TABLE_NAME + " where mahd=?", new String[]{String.valueOf(mahd)});
        int ketqua = 0;
        if (c.moveToFirst()) {
            ketqua = c.getInt(c.getColumnIndex("tongtien"));
        }
        return ketqua;
    }

    //lấy tổng tiền theo ngày
    public int gettongtien(String ngay) {
        Cursor c = db.rawQuery("SELECT sum(tongtien) from " + TABLE_NAME + " where ngay=? group by ngay", new String[]{ngay});
        int ketqua = 0;
        if (c.moveToFirst()) {
            ketqua = c.getInt(0);
        }
        return ketqua;

    }

    //lấy tổng tiền theo quý
    public int tongtienquy(String... month) {
        int ketqua = 0;
        Cursor c = db.rawQuery("SELECT sum(tongtien) FROM HoaDon WHERE strftime('%m',HoaDon.ngay) =? or strftime('%m',HoaDon.ngay)=? or strftime('%m',HoaDon.ngay)=?", month);
        if (c.moveToFirst()) {
            ketqua = c.getInt(0);
        }
        return ketqua;
    }

    //thống kê theo ngày
    public int thongkengay() {
        int ketqua = 0;
        Cursor c = db.rawQuery("SELECT sum(tongtien) from HoaDon where ngay=date('now')", null);
        if (c.moveToFirst()) {
            ketqua = c.getInt(0);
        }
        return ketqua;
    }

    //thống kê theo tháng
    public int thongkethang() {
        int ketqua = 0;
        Cursor c = db.rawQuery("SELECT sum(tongtien) from HoaDon where strftime('%m',ngay)=strftime('%m','now') and " +
                "strftime('%Y',ngay)=strftime('%Y','now')", null);
        if (c.moveToFirst()) {
            ketqua = c.getInt(0);
        }
        return ketqua;
    }

    //thống kê theo năm
    public int thongkenam() {
        int ketqua = 0;
        Cursor c = db.rawQuery("SELECT sum(tongtien) from HoaDon where strftime('%Y',ngay)=strftime('%Y','now')", null);
        if (c.moveToFirst()) {
            ketqua = c.getInt(0);
        }
        return ketqua;
    }


}
