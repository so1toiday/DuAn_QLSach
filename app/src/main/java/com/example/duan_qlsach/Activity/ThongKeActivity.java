package com.example.duan_qlsach.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.duan_qlsach.R;
import com.example.duan_qlsach.dao.ChiTietHoaDonDAO;
import com.example.duan_qlsach.dao.HoaDonDAO;
import com.example.duan_qlsach.model.DanhSachHoaDon;


import java.util.Calendar;

import java.util.List;

public class ThongKeActivity extends AppCompatActivity {
    TextView tvhomnay, tvthangnay, tvnamnay, tongtien, soluong;
    RecyclerView re;
    Button chonngay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        anhxa();
        thongke();

    }

    //thống kê sách theo ngày
    private void ThongkeTheoNgay(String ngay) {
        DSHoadonActivity dsHoadonActivity = new DSHoadonActivity();
        ChiTietHoaDonDAO ctdao = new ChiTietHoaDonDAO(this);
        HoaDonDAO hd = new HoaDonDAO(this);
        tongtien.setText(hd.gettongtien(ngay) + " VNĐ");
        soluong.setText(ctdao.getsoluongsach(ngay) + "");
        List<DanhSachHoaDon> list = ctdao.getdanhsachhoadon(ngay);
        dsHoadonActivity.danhsachhoadon(this, re, list);

    }

    //thống kê chung
    private void thongke() {
        HoaDonDAO hoaDonDAO = new HoaDonDAO(this);
        tvhomnay.setText("Hôm nay: " + hoaDonDAO.thongkengay());
        tvthangnay.setText("Tháng nay: " + hoaDonDAO.thongkethang());
        tvnamnay.setText("Năm nay: " + hoaDonDAO.thongkenam());
    }


    private void anhxa() {
        tvhomnay = findViewById(R.id.tvhomnay);
        tvthangnay = findViewById(R.id.tvthangnay);
        tvnamnay = findViewById(R.id.tvnamnay);
        re = findViewById(R.id.re);
        chonngay = findViewById(R.id.chongay);
        tongtien = findViewById(R.id.tongtien);
        soluong = findViewById(R.id.soluong);
    }

    //chọn ngày
    public void chonngay(View view) {
        Calendar c = Calendar.getInstance();
        final int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String month1 = month + 1 + "";
                String day = dayOfMonth + "";
                if (month < 9) {
                    month1 = 0 + month1;
                }
                if (dayOfMonth < 10) {
                    day = 0 + day;
                }
                chonngay.setText(year + "-" + month1 + "-" + day);
                ThongkeTheoNgay(year + "-" + month1 + "-" + day);
            }
        }, year, month, day);
        dialog.show();
    }

    //chuyển sang activity biểu đồ
    public void bieudo(View view) {
        Intent intent = new Intent(this, bieudoActivity.class);
        startActivity(intent);
    }
}
