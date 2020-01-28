package com.example.duan_qlsach.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.duan_qlsach.database.DatabaseHelper;
import com.example.duan_qlsach.model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class NguoiDungDAO {
    public static final String TABLE_NAME = "NguoiDung";
    public static final String SQL_NGUOI_DUNG = "CREATE TABLE NguoiDung(username text primary key," +
            "pass text," +
            "phone text," +
            "fullname text," +
            "address text)";
    public static final String ADMIN = "insert into " + TABLE_NAME + " values('ADMIN','ADMIN','0339758521','quyetdaica','ha noi')";
    private static final String TAG = "NguoiDungDAO";
    private SQLiteDatabase db;
    private DatabaseHelper dt;

    public NguoiDungDAO(Context context) {
        dt = new DatabaseHelper(context);
        db = dt.getWritableDatabase();
    }

    //thêm người dùng
    public boolean insertNguoiDung(NguoiDung nd) {
        ContentValues values = new ContentValues();
        values.put("username", nd.getUsername());
        values.put("pass", nd.getPass());
        values.put("phone", nd.getPhone());
        values.put("fullname", nd.getFullname());
        values.put("address", nd.getAddress());
        long check = db.insert(TABLE_NAME, null, values);
        try {
            if (check == -1) {
                return false;
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return false;
        }
        return true;
    }

    //update người dùng
    public boolean SuaNguoiDung(NguoiDung nd, String username) {
        ContentValues values = new ContentValues();
        values.put("username", nd.getUsername());
        values.put("pass", nd.getPass());
        values.put("phone", nd.getPhone());
        values.put("fullname", nd.getFullname());
        values.put("address", nd.getAddress());
        try {
            int result = db.update(TABLE_NAME, values, "username=?", new String[]{username});
            if (result == -1) return false;

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    //lấy 10 người dùng
    public List<NguoiDung> get10NguoiDung() {
        List<NguoiDung> list = new ArrayList<>();
        Cursor c = db.rawQuery("select * from " + TABLE_NAME, null);
        int i = 0;
        while (c.moveToNext() && i <= 9) {
            i++;
            NguoiDung nd = new NguoiDung();
            nd.setUsername(c.getString(0));
            nd.setPass(c.getString(1));
            nd.setPhone(c.getString(2));
            nd.setFullname(c.getString(3));
            nd.setAddress(c.getString(4));
            list.add(nd);
        }
        return list;
    }


    //xóa người dùng
    public int delete(String user) {
        return db.delete(TABLE_NAME, "username=?", new String[]{user});
    }


    //kiểm tra đăng nhập
    public boolean isLogin(NguoiDung nd) {
        String sql = "SELECT username,pass from " + TABLE_NAME + " where username=? and pass=?";
        Cursor c = db.rawQuery(sql, new String[]{nd.getUsername(), nd.getPass()});
        if (c.moveToFirst()) {
            return true;
        }
        return false;
    }

    //thay đổi mật khẩu
    public boolean changepass(NguoiDung nd) {

        ContentValues values = new ContentValues();
        values.put("pass", nd.getPass());
        int a = db.update(TABLE_NAME, values, "username=?", new String[]{nd.getUsername()});
        if (a != -1) return true;

        return false;
    }


}
