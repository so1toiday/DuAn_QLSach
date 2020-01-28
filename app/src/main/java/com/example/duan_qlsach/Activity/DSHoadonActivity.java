package com.example.duan_qlsach.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_qlsach.R;
import com.example.duan_qlsach.adapter.DanhSachHoaDonAdapter;
import com.example.duan_qlsach.dao.ChiTietHoaDonDAO;
import com.example.duan_qlsach.dao.HoaDonDAO;
import com.example.duan_qlsach.model.DanhSachHoaDon;
import com.example.duan_qlsach.model.Hoadon_tong;

import java.util.ArrayList;
import java.util.List;


public class DSHoadonActivity extends AppCompatActivity {
    RecyclerView re;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlsach);
        setTitle("HÓA ĐƠN");
        re = findViewById(R.id.re);

    }


    @Override
    protected void onResume() {
        super.onResume();
        ChiTietHoaDonDAO ctdao = new ChiTietHoaDonDAO(this);
        List<DanhSachHoaDon> list = ctdao.getdanhsachhoadon();
        danhsachhoadon(this,re,list);
    }

    //lấy danh sách hóa đơn và truyền vào list
    public void danhsachhoadon(Context context,RecyclerView re,List<DanhSachHoaDon> list) {
        List<Hoadon_tong> dshd = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSach() == null) continue;
            Hoadon_tong hd = new Hoadon_tong();
            HoaDonDAO h = new HoaDonDAO(context);
            hd.setMahd(list.get(i).getMahd());
            hd.setNgay(list.get(i).getNgay());
            hd.setTongtien(h.gettongtien(hd.getMahd()));
            String chuoi = "Tên Sách: " + list.get(i).getSach() + " - Số Lượng: " + list.get(i).getSoluong() + " - Tiền: "
                    + list.get(i).getTongtien()+" VNĐ";
            for (int j = i + 1; j < list.size(); j++) {
                //nếu 2 hd giống nhau
                if (list.get(i).getMahd() == list.get(j).getMahd() && i != j) {
                    chuoi += "\nTên Sách: " + list.get(j).getSach() + " - Số Lượng: " + list.get(j).getSoluong() + " - Tiền: "
                            + list.get(j).getTongtien()+" VNĐ";
                    list.set(j, new DanhSachHoaDon());
                }
            }
            hd.setChuoi(chuoi);
            dshd.add(hd);
        }
        DanhSachHoaDonAdapter adapter = new DanhSachHoaDonAdapter(context, dshd);
        re.setLayoutManager(new LinearLayoutManager(context));
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
                Intent i = new Intent(getBaseContext(), ThemHoaDonActivity.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
