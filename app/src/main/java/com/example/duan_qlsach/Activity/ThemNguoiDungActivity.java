package com.example.duan_qlsach.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan_qlsach.R;
import com.example.duan_qlsach.function.checkform;
import com.example.duan_qlsach.dao.NguoiDungDAO;
import com.example.duan_qlsach.model.NguoiDung;


public class ThemNguoiDungActivity extends AppCompatActivity {
    EditText edusername, edpassword, edfullname, edsdt, edaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themnguoidung);
        setTitle("NGƯỜI DÙNG");
        anhxa();
    }

    private void anhxa() {
        edusername = findViewById(R.id.edUserName);
        edpassword = findViewById(R.id.edPassWord);
        edsdt = findViewById(R.id.edSDT);
        edfullname = findViewById(R.id.edFullName);
        edaddress = findViewById(R.id.edAddress);
    }

    public void danhsach(View view) {
        startActivity(new Intent(getBaseContext(), DSNguoidungAcitivity.class));
        finish();
    }

    //thềm vào csdl
    public void them(View view) {
        if (checkform.isEmpty(edaddress, edpassword, edsdt, edfullname, edaddress)) {
            Toast.makeText(this, "BẠN CHƯA NHẬP ĐẦY ĐỦ THÔNG TIN", Toast.LENGTH_SHORT).show();
        } else if (!checkform.isphone(edsdt)) {
            Toast.makeText(this, "SĐT KHÔNG ĐÚNG ĐỊNH DẠNG", Toast.LENGTH_SHORT).show();
        } else {
            NguoiDungDAO ndDAO = new NguoiDungDAO(getBaseContext());
            NguoiDung nd = new NguoiDung();
            nd.setUsername(edusername.getText().toString());
            nd.setPass(edpassword.getText().toString());
            nd.setFullname(edfullname.getText().toString());
            nd.setPhone(edsdt.getText().toString());
            nd.setAddress(edaddress.getText().toString());
            boolean check = ndDAO.insertNguoiDung(nd);
            if (check) {
                Toast.makeText(this, "THÊM THÀNH CÔNG", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "THÊM THẤT BẠI", Toast.LENGTH_SHORT).show();
            }
        }


    }


}
