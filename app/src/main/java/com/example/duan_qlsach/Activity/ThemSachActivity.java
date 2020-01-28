package com.example.duan_qlsach.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan_qlsach.R;
import com.example.duan_qlsach.function.checkform;
import com.example.duan_qlsach.dao.SachDAO;
import com.example.duan_qlsach.dao.TheLoaiDAO;
import com.example.duan_qlsach.model.Sach;
import com.example.duan_qlsach.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class ThemSachActivity extends AppCompatActivity {
    Spinner sp_theloai;
    List<String> listtl;
    EditText masach, tensach, gia, soluong, tacgia, nxb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themsach);
        setTitle("SÁCH");
        anhxa();
        TheLoaiDAO tldao = new TheLoaiDAO(ThemSachActivity.this);
        List<TheLoai> list = tldao.selectall();

        //lấy list spinner
        listtl = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String a = list.get(i).getMatl() + ". " + list.get(i).getTentl();
            listtl.add(a);
        }
        ArrayAdapter adapter = new ArrayAdapter(getApplication(), android.R.layout.simple_spinner_dropdown_item, listtl);
        sp_theloai.setAdapter(adapter);
    }

    private void anhxa() {
        masach = findViewById(R.id.masach);
        tensach = findViewById(R.id.tensach);
        nxb = findViewById(R.id.nxb);
        gia = findViewById(R.id.gia);
        soluong = findViewById(R.id.soluong);
        tacgia = findViewById(R.id.tacgia);
        sp_theloai = findViewById(R.id.sp_theloai);
    }

    //chuyển activity danh sách
    public void show(View view) {
        startActivity(new Intent(getBaseContext(), DSSachActivity.class));
        finish();
    }


    //chuyển activity thêm thể loại
    public void themtheloai(View view) {
        startActivity(new Intent(this, ThemTheLoaiActivity.class));
        finish();
    }


    //thêm vào csdl
    public void them(View view) {
        if (!checkform.isEmpty(masach, tensach, gia, soluong, tacgia, nxb)) {
            Sach s = new Sach();
            s.setMasach(Integer.parseInt(masach.getText().toString()));
            s.setSoluong(Integer.parseInt(soluong.getText().toString()));
            s.setGia(Integer.parseInt(gia.getText().toString()));
            s.setTensach(tensach.getText().toString());
            s.setNxb(nxb.getText().toString());
            s.setTacgia(tacgia.getText().toString());
            s.setMatl(sp_theloai.getSelectedItemPosition());
            SachDAO sachDAO = new SachDAO(this);
            if(sachDAO.insertSach(s)){
                Toast.makeText(this, "THÊM THÀNH CÔNG", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "BẠN PHẢI NHẬP ĐẦY ĐỦ THÔNG TIN", Toast.LENGTH_SHORT).show();
        }
    }
}
