package com.example.duan_qlsach.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duan_qlsach.database.DatabaseHelper;
import com.example.duan_qlsach.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    public static final String TABLE_NAME = "Sach";
    public static final String SQL = "CREATE TABLE Sach(" +
            "masach INT primary key," +
            "tensach TEXT," +
            "matl INT," +
            "nxb TEXT," +
            "tacgia TEXT," +
            "gia INT," +
            "soluong INT," +
            "anh INT," +
            "FOREIGN KEY (matl) REFERENCES TheLoai(matl))";
    DatabaseHelper dt;
    SQLiteDatabase db;

    public SachDAO(Context context) {
        dt = new DatabaseHelper(context);
        db = dt.getWritableDatabase();
    }


    //thêm sách
    public boolean insertSach(Sach s) {
        ContentValues values = new ContentValues();
        values.put("masach", s.getMasach());
        values.put("tensach", s.getTensach());
        values.put("matl", s.getMatl());
        values.put("nxb", s.getNxb());
        values.put("tacgia", s.getTacgia());
        values.put("gia", s.getGia());
        values.put("soluong", s.getSoluong());
        values.put("anh", s.getAnh());
        try {
            long result = db.insert(TABLE_NAME, null, values);
            if (result != -1) return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }


    //lấy danh sách sách
    public List<Sach> selectall() {
        List<Sach> list = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        while (c.moveToNext()) {
            Sach s = new Sach();
            s.setMasach(c.getInt(0));
            s.setTensach(c.getString(1));
            s.setMatl(c.getInt(2));
            s.setNxb(c.getString(3));
            s.setTacgia(c.getString(4));
            s.setGia(c.getInt(5));
            s.setSoluong(c.getInt(6));
            s.setAnh(c.getInt(7));
            list.add(s);
        }
        return list;
    }

    //xóa sách
    public boolean deleteSach(int masach) {
        try {
            int result = db.delete(TABLE_NAME, "masach=?", new String[]{String.valueOf(masach)});
            if(result!=-1) return true;
        } catch (Exception e) {
            Log.e("DELETESACH",e.toString());
        }
        return false;
    }

    //lấy giá sách theo mã sách
    public int getgia(int masach){
        Cursor c=db.rawQuery("SELECT gia from Sach where masach=?",new String[]{String.valueOf(masach)});
        int ketqua=0;

        if(c.moveToNext()){
        ketqua=c.getInt(c.getColumnIndex("gia"));}
        return ketqua;
    }

    //lấy tên sách theo mã sách
    public String gettensach(int masach){
        Cursor c=db.rawQuery("SELECT tensach from Sach where masach=?",new String[]{String.valueOf(masach)});
        String ketqua="";

        if(c.moveToNext()){
           ketqua=c.getString(c.getColumnIndex("tensach"));}
        return ketqua;
    }



}
