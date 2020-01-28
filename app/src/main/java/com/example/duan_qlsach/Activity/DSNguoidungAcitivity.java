package com.example.duan_qlsach.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.duan_qlsach.R;
import com.example.duan_qlsach.adapter.DSNguoiDung;
import com.example.duan_qlsach.dao.NguoiDungDAO;
import com.example.duan_qlsach.model.NguoiDung;

import java.util.List;

public class DSNguoidungAcitivity extends AppCompatActivity {
    RecyclerView re;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlnguoidung);
        setTitle("NGƯỜI DÙNG");
        dsnguoidung();
    }

    //lấy danh sách sách từ csdl truyền vào list
    private void dsnguoidung() {
        re = findViewById(R.id.re);
        NguoiDungDAO db = new NguoiDungDAO(getBaseContext());
        List<NguoiDung> list = db.get10NguoiDung();
        DSNguoiDung adapter = new DSNguoiDung(DSNguoidungAcitivity.this, list);
        re.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        re.setHasFixedSize(true);
        re.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nguoidung, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.them:
                Intent i = new Intent(getBaseContext(), ThemNguoiDungActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.enter, R.anim.exit);
                finish();
                break;
            case R.id.doimatkhau:
                startActivity(new Intent(DSNguoidungAcitivity.this, DoiMatKhauActivity.class));
                overridePendingTransition(R.anim.enter, R.anim.exit);
                break;
            case R.id.dangxuat:
                SharedPreferences sharedPreferences = getSharedPreferences("nhomatkhau", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                i = new Intent(getBaseContext(), DangNhapActivity.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
