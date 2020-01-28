package com.example.duan_qlsach.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan_qlsach.R;
import com.example.duan_qlsach.dao.NguoiDungDAO;
import com.example.duan_qlsach.model.NguoiDung;

public class DoiMatKhauActivity extends AppCompatActivity {
    EditText edNewPassword, edNewPasswordnhaplai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doimatkhau);
        anhxa();
    }

    private void anhxa() {
        edNewPassword = findViewById(R.id.edNewPassword);
        edNewPasswordnhaplai = findViewById(R.id.edNewPassword1);
    }

    //đổi mật khẩu
    public void doimatkhau(View view) {
        String matkhau = edNewPassword.getText().toString();
        String nhapmatkhau = edNewPasswordnhaplai.getText().toString();
        if (matkhau.equals(nhapmatkhau)) {
            NguoiDung nd = new NguoiDung(ManHinhChinhActivity.taikhoan, matkhau);
            NguoiDungDAO dungDAO = new NguoiDungDAO(this);
            if (dungDAO.changepass(nd)) {
                Toast.makeText(this, "THAY DOI THANH CONG", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "THAY DOI THAT BAI", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "NHAP LAI MAT KHAU KHONG DUNG", Toast.LENGTH_SHORT).show();
        }
    }

}
