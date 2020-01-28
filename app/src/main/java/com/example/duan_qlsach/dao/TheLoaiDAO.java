package com.example.duan_qlsach.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan_qlsach.database.DatabaseHelper;
import com.example.duan_qlsach.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiDAO {
    public static final String TABLE_NAME = "TheLoai";
    public static final String SQL = "CREATE TABLE TheLoai(" +
            "matl INT PRIMARY KEY," +
            "tentl TEXT," +
            "vitri TEXT," +
            "mota TEXT)";
    DatabaseHelper dt;
    SQLiteDatabase db;

    public TheLoaiDAO(Context context) {
        dt = new DatabaseHelper(context);
        db = dt.getWritableDatabase();
    }

    //thêm thê loại
    public boolean insertSach(TheLoai tl) {
        ContentValues values = new ContentValues();
        values.put("matl", tl.getMatl());
        values.put("tentl", tl.getTentl());
        values.put("vitri", tl.getVitri());
        values.put("mota", tl.getMota());
        try {
            long result = db.insert(TABLE_NAME, null, values);
            if (result != -1) return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    //lấy danh sách thể loại
    public List<TheLoai> selectall() {
        List<TheLoai> list = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        while (c.moveToNext()) {
            TheLoai tl = new TheLoai();
            tl.setMatl(c.getInt(0));
            tl.setTentl(c.getString(1));
            tl.setVitri(c.getString(2));
            tl.setMota(c.getString(3));
            list.add(tl);
        }
        return list;
    }

    //xóa thể loại
    public boolean deleteTheloai(int matl) {
        if (db.delete(TABLE_NAME, "matl=?", new String[]{String.valueOf(matl)}) != -1) return true;
        return false;
    }

}
