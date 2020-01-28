package com.example.duan_qlsach.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.duan_qlsach.dao.ChiTietHoaDonDAO;
import com.example.duan_qlsach.dao.HoaDonDAO;
import com.example.duan_qlsach.dao.NguoiDungDAO;
import com.example.duan_qlsach.dao.SachDAO;
import com.example.duan_qlsach.dao.TheLoaiDAO;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATA_NAME = "dbBookMananger";
    public static final int VERSION = 2;

    public DatabaseHelper(Context context) {
        super(context, DATA_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NguoiDungDAO.SQL_NGUOI_DUNG);
        db.execSQL(NguoiDungDAO.ADMIN);
        db.execSQL(TheLoaiDAO.SQL);
        db.execSQL(SachDAO.SQL);
        db.execSQL(HoaDonDAO.SQL);
        db.execSQL(ChiTietHoaDonDAO.SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + NguoiDungDAO.TABLE_NAME);
        db.execSQL("Drop table if exists " + SachDAO.TABLE_NAME);
        db.execSQL("Drop table if exists " + TheLoaiDAO.TABLE_NAME);
    }
}
