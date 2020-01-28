package com.example.duan_qlsach.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.duan_qlsach.R;
import com.example.duan_qlsach.dao.NguoiDungDAO;
import com.example.duan_qlsach.model.NguoiDung;

public class DangNhapActivity extends AppCompatActivity {
    EditText edUsername, edPassword;
    CheckBox chkRememberPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        edUsername = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.edPassWord);
        chkRememberPass = findViewById(R.id.chkRememberPass);

        //Luu mat khau khi dang nhap thanh cong
        SharedPreferences sharedPreferences = this.getSharedPreferences("nhomatkhau", Context.MODE_PRIVATE);
        boolean checked = sharedPreferences.getBoolean("check", false);
        if (checked) {
            String tk = sharedPreferences.getString("tk", "");
            String mk = sharedPreferences.getString("mk", "");
            edUsername.setText(tk);
            edPassword.setText(mk);
            chkRememberPass.setChecked(checked);
        }
    }


    public void checkLogin(View view) {
        NguoiDungDAO db = new NguoiDungDAO(this);
        String tk = edUsername.getText().toString();
        String mk = edPassword.getText().toString();
        ManHinhChinhActivity.taikhoan = tk;
        ManHinhChinhActivity.matkhau = mk;

        //check tai khoan va mat khau
        boolean result = db.isLogin(new NguoiDung(tk, mk));
        if (result) {
            startActivity(new Intent(DangNhapActivity.this, ManHinhChinhActivity.class));
            overridePendingTransition(R.anim.enter, R.anim.exit);
            SharedPreferences sharedPreferences =
                    this.getSharedPreferences("nhomatkhau", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            boolean check = chkRememberPass.isChecked();
            if (check) {
                editor.putString("tk", tk);
                editor.putString("mk", mk);
                editor.putBoolean("check", check);
            } else {
                editor.clear();
            }
            editor.commit();
            finish();
        } else {
            Toast.makeText(this, "DANG NHAP THAT BAI", Toast.LENGTH_SHORT).show();
        }
    }
}
