package com.example.duan_qlsach.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_qlsach.R;
import com.example.duan_qlsach.adapter.DSTheLoai;
import com.example.duan_qlsach.dao.TheLoaiDAO;
import com.example.duan_qlsach.model.TheLoai;

import java.util.List;

public class DSTheloaiActivity extends AppCompatActivity {
    RecyclerView re;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlsach);
        setTitle("THỂ LOẠI");
        re = findViewById(R.id.re);
        danhsachtheloai();
    }

    //lấy danh sách thể loại từ csdl truyền vào list
    private void danhsachtheloai() {
        TheLoaiDAO tldao = new TheLoaiDAO(DSTheloaiActivity.this);
        List<TheLoai> list = tldao.selectall();
        DSTheLoai adapter = new DSTheLoai(DSTheloaiActivity.this, list);
        re.setLayoutManager(new LinearLayoutManager(DSTheloaiActivity.this));
        re.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ql_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.them:
                Intent i = new Intent(getBaseContext(), ThemTheLoaiActivity.class);
                startActivity(i);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
