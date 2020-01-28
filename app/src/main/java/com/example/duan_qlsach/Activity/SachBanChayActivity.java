package com.example.duan_qlsach.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.duan_qlsach.R;
import com.example.duan_qlsach.adapter.SachBanChayAdapter;
import com.example.duan_qlsach.dao.ChiTietHoaDonDAO;
import com.example.duan_qlsach.model.ThongKeSachBanChay;

import java.util.List;

public class SachBanChayActivity extends AppCompatActivity {
    RecyclerView re;
    Spinner sp;
    TextView tv;
    List<ThongKeSachBanChay> list;
    SachBanChayAdapter adapterre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach_ban_chay);
        anhxa();
        final ChiTietHoaDonDAO sachbanchay = new ChiTietHoaDonDAO(this);
        list = sachbanchay.getsachbanchaytheongay();//list sach
        String[] thongke = {"THEO NGÀY", "THEO TUẦN", "THEO THÁNG", "THEO NĂM"};
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, thongke);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (sp.getSelectedItem().toString()) {
                    case "THEO NGÀY":
                        list = sachbanchay.getsachbanchaytheongay();
                        adapterre = new SachBanChayAdapter(SachBanChayActivity.this, list);
                        re.setAdapter(adapterre);
                        break;
                    case "THEO TUẦN":
                        list = sachbanchay.getsachbanchaytheotuan();
                        adapterre = new SachBanChayAdapter(SachBanChayActivity.this, list);
                        re.setAdapter(adapterre);
                        break;
                    case "THEO THÁNG":
                        list = sachbanchay.getsachbanchaytheothang();
                        adapterre = new SachBanChayAdapter(SachBanChayActivity.this, list);
                        re.setAdapter(adapterre);
                        break;
                    case "THEO NĂM":
                        list = sachbanchay.getsachbanchaytheonam();
                        adapterre = new SachBanChayAdapter(SachBanChayActivity.this, list);
                        re.setAdapter(adapterre);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp.setAdapter(adapter);
        adapterre = new SachBanChayAdapter(this, list);
        re.setLayoutManager(new LinearLayoutManager(this));
        re.setAdapter(adapterre);
    }


    private void anhxa() {
        sp = findViewById(R.id.sp);
        re = findViewById(R.id.re);
        tv = findViewById(R.id.tv);
    }
}
