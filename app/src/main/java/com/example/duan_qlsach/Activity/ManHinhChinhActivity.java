package com.example.duan_qlsach.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duan_qlsach.R;

public class ManHinhChinhActivity extends AppCompatActivity {
    static String taikhoan = "";
    static String matkhau = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahinhchinh);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent i;
        switch (item.getItemId()) {
            case R.id.doimatkhau:
                i = new Intent(getBaseContext(), DoiMatKhauActivity.class);
                startActivity(i);
                break;
            case R.id.dangxuat:
                SharedPreferences sharedPreferences = getSharedPreferences("nhomatkhau", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                taikhoan="";
                matkhau="";
                i = new Intent(getBaseContext(), DangNhapActivity.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void nguoidung(View view) {
        startActivity(new Intent(getBaseContext(), DSNguoidungAcitivity.class));
        overridePendingTransition(R.anim.enter, R.anim.exit);
    }

    public void sach(View view) {
        startActivity(new Intent(getBaseContext(), DSSachActivity.class));
    }

    public void theloai(View view) {
        startActivity(new Intent(getBaseContext(), DSTheloaiActivity.class));
    }

    public void hoadon(View view) {
        startActivity(new Intent(getBaseContext(), DSHoadonActivity.class));
    }

    public void sachbanchay(View view) {
        startActivity(new Intent(this, SachBanChayActivity.class));
    }

    public void thongke(View view) {
        startActivity(new Intent(this, ThongKeActivity.class));
    }
}
