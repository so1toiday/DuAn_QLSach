package com.example.duan_qlsach.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan_qlsach.database.DatabaseHelper;
import com.example.duan_qlsach.model.ChiTietHoaDon;
import com.example.duan_qlsach.model.DanhSachHoaDon;
import com.example.duan_qlsach.model.ThongKeSachBanChay;

import java.util.ArrayList;
import java.util.List;

public class ChiTietHoaDonDAO {
    DatabaseHelper dt;
    SQLiteDatabase db;
    public static final String SQL = "CREATE TABLE ChiTietHoaDon(" +
            "mahdct INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "mahd INT ,masach INT,soluong INT,FOREIGN KEY (mahd) REFERENCES HoaDon(mahd)," +
            "FOREIGN KEY (masach) REFERENCES Sach(masach))";
    static final String TABLE_NAME = "ChiTietHoaDon";

    public ChiTietHoaDonDAO(Context context) {
        dt = new DatabaseHelper(context);
        db = dt.getWritableDatabase();
    }

    //thêm hóa đơn
    public boolean insertchitiethoadon(ChiTietHoaDon cthd){
        ContentValues values=new ContentValues();
        values.put("mahd",cthd.getMahd());
        values.put("masach",cthd.getMasach());
        values.put("soluong",cthd.getSoluong());
        long a= db.insert(TABLE_NAME,null,values);
        if(a!=-1) return true;
        return false;
    }

//lấy danh sách hóa đơn
    public List<DanhSachHoaDon> getdanhsachhoadon(){
        List<DanhSachHoaDon> list=new ArrayList<>();
        Cursor c=db.rawQuery("SELECT ChiTietHoaDon.mahd,Sach.tensach,HoaDon.ngay" +
                ",Sach.gia*ChiTietHoaDon.soluong,ChiTietHoaDon.soluong from HoaDon inner join ChiTietHoaDon on" +
                " HoaDon.mahd=ChiTietHoaDon.mahd inner join Sach on ChiTietHoaDon.masach=Sach.masach",null);
        while (c.moveToNext()){
            DanhSachHoaDon ds=new DanhSachHoaDon();
            ds.setMahd(c.getInt(0));
            ds.setSach(c.getString(1));
            ds.setNgay(c.getString(2));
            ds.setTongtien(c.getInt(3));
            ds.setSoluong(c.getInt(4));
            list.add(ds);
        }
        return list;
    }

    //lấy danh sách hóa đơn theo ngày
    public List<DanhSachHoaDon> getdanhsachhoadon(String ngay){
        List<DanhSachHoaDon> list=new ArrayList<>();
        Cursor c=db.rawQuery("SELECT ChiTietHoaDon.mahd,Sach.tensach,HoaDon.ngay" +
                ",Sach.gia*ChiTietHoaDon.soluong,ChiTietHoaDon.soluong from HoaDon inner join ChiTietHoaDon on" +
                " HoaDon.mahd=ChiTietHoaDon.mahd inner join Sach on ChiTietHoaDon.masach=Sach.masach where HoaDon.ngay=?", new String[]{ngay});
        while (c.moveToNext()){
            DanhSachHoaDon ds=new DanhSachHoaDon();
            ds.setMahd(c.getInt(0));
            ds.setSach(c.getString(1));
            ds.setNgay(c.getString(2));
            ds.setTongtien(c.getInt(3));
            ds.setSoluong(c.getInt(4));
            list.add(ds);
        }
        return list;
    }


    //lấy sách bán chay theo ngày
    public List<ThongKeSachBanChay> getsachbanchaytheongay(){
        List<ThongKeSachBanChay> list=new ArrayList<>();
        Cursor c=db.rawQuery("SELECT Sach.masach,Sach.tensach,HoaDon.ngay,sum(ChiTietHoaDon.soluong) as soluong1" +
                " from HoaDon inner join ChiTietHoaDon on HoaDon.mahd=ChiTietHoaDon.mahd " +
                "inner join Sach on ChiTietHoaDon.masach=Sach.masach " +
                "where HoaDon.ngay=date('now')" +
                " group by Sach.masach,Sach.tensach " +
                " order by soluong1 DESC ",null);
        while (c.moveToNext()){
            ThongKeSachBanChay tk=new ThongKeSachBanChay();
            tk.setMasach(c.getInt(0));
            tk.setTensach(c.getString(1));
            tk.setNgay(c.getString(2));
            tk.setSoluong(c.getInt(3));
            list.add(tk);
        }
        return list;
    }

    //lấy sách bán chạy theo tuần
    public List<ThongKeSachBanChay> getsachbanchaytheotuan(){
        List<ThongKeSachBanChay> list=new ArrayList<>();
        Cursor c=db.rawQuery("SELECT Sach.masach,Sach.tensach,strftime('%W',HoaDon.ngay),sum(ChiTietHoaDon.soluong) as soluong1" +
                " from HoaDon inner join ChiTietHoaDon on HoaDon.mahd=ChiTietHoaDon.mahd " +
                "inner join Sach on ChiTietHoaDon.masach=Sach.masach " +
                "where strftime('%W',HoaDon.ngay)=strftime('%W','now') and strftime('%Y',HoaDon.ngay)=strftime('%Y','now') " +
                " group by Sach.masach,Sach.tensach " +
                " order by soluong1 DESC ",null);
        while (c.moveToNext()){
            ThongKeSachBanChay tk=new ThongKeSachBanChay();
            tk.setMasach(c.getInt(0));
            tk.setTensach(c.getString(1));
            tk.setNgay(c.getString(2));
            tk.setSoluong(c.getInt(3));
            list.add(tk);
        }
        return list;
    }

    //lấy sách bán chạy theo tháng
    public List<ThongKeSachBanChay> getsachbanchaytheothang(){
        List<ThongKeSachBanChay> list=new ArrayList<>();
        Cursor c=db.rawQuery("SELECT Sach.masach,Sach.tensach,strftime('%m',HoaDon.ngay) ,sum(ChiTietHoaDon.soluong) as soluong1" +
                " from HoaDon inner join ChiTietHoaDon on HoaDon.mahd=ChiTietHoaDon.mahd " +
                "inner join Sach on ChiTietHoaDon.masach=Sach.masach " +
                "where strftime('%m',HoaDon.ngay)=strftime('%m','now') and strftime('%Y',HoaDon.ngay)=strftime('%Y','now') " +
                " group by Sach.masach,Sach.tensach " +
                " order by soluong1 DESC ",null);
        while (c.moveToNext()){
            ThongKeSachBanChay tk=new ThongKeSachBanChay();
            tk.setMasach(c.getInt(0));
            tk.setTensach(c.getString(1));
            tk.setNgay(c.getString(2));
            tk.setSoluong(c.getInt(3));
            list.add(tk);
        }
        return list;
    }

    //lấy sách bán chạy theo năm
    public List<ThongKeSachBanChay> getsachbanchaytheonam(){
        List<ThongKeSachBanChay> list=new ArrayList<>();
        Cursor c=db.rawQuery("SELECT Sach.masach,Sach.tensach,strftime('%Y',HoaDon.ngay),sum(ChiTietHoaDon.soluong) as soluong1" +
                " from HoaDon inner join ChiTietHoaDon on HoaDon.mahd=ChiTietHoaDon.mahd " +
                "inner join Sach on ChiTietHoaDon.masach=Sach.masach " +
                "where strftime('%Y',HoaDon.ngay)=strftime('%Y','now') " +
                " group by Sach.masach,Sach.tensach " +
                " order by soluong1 DESC ",null);
        while (c.moveToNext()){
            ThongKeSachBanChay tk=new ThongKeSachBanChay();
            tk.setMasach(c.getInt(0));
            tk.setTensach(c.getString(1));
            tk.setNgay(c.getString(2));
            tk.setSoluong(c.getInt(3));
            list.add(tk);
        }
        return list;
    }

    //lấy số lượng sách
    public int getsoluongsach(String ngay){
        Cursor c = db.rawQuery("SELECT sum(ChiTietHoaDon.soluong) from " + TABLE_NAME+ " inner join HoaDon on " +
                "HoaDon.mahd=ChiTietHoaDon.mahd where ngay=?", new String[]{ngay});
        int ketqua = 0;
        if (c.moveToFirst()) {
            ketqua = c.getInt(0);
        }
        return ketqua;

    }



}
