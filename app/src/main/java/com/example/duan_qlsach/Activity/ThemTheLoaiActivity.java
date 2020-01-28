package com.example.duan_qlsach.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan_qlsach.R;
import com.example.duan_qlsach.function.checkform;
import com.example.duan_qlsach.dao.TheLoaiDAO;
import com.example.duan_qlsach.model.TheLoai;

public class ThemTheLoaiActivity extends AppCompatActivity {
    EditText etTentl, etMatl, etVitri, etMota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themtheloai);
        setTitle("THỂ LOẠI");
        etVitri = findViewById(R.id.etViTri);
        etMota = findViewById(R.id.etMoTa);
        etTentl = findViewById(R.id.etTentl);
        etMatl = findViewById(R.id.etMatl);
    }

    public void show(View view) {
        startActivity(new Intent(getBaseContext(), DSTheloaiActivity.class));
        finish();
    }


    //thêm vào csdl
    public void them(View view) {
        if (!checkform.isEmpty(etTentl, etMatl, etVitri, etMota)) {
            TheLoai tl = new TheLoai();
            tl.setMatl(Integer.parseInt(etMatl.getText().toString()));
            tl.setMota(etMota.getText().toString());
            tl.setVitri(etVitri.getText().toString());
            tl.setTentl(etTentl.getText().toString());
            TheLoaiDAO tldao = new TheLoaiDAO(ThemTheLoaiActivity.this);
            if (tldao.insertSach(tl)) {
                Toast.makeText(this, "THÀNH CÔNG", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "THẤT BẠI", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "BẠN PHẢI NHẬP ĐẦY ĐỦ THÔNG TIN", Toast.LENGTH_SHORT).show();
        }
    }
}
