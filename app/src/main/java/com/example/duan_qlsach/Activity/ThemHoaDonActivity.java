package com.example.duan_qlsach.Activity;

import android.app.DatePickerDialog;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_qlsach.R;
import com.example.duan_qlsach.adapter.ChiTietHoaDonAdapter;
import com.example.duan_qlsach.function.checkform;
import com.example.duan_qlsach.dao.ChiTietHoaDonDAO;
import com.example.duan_qlsach.dao.HoaDonDAO;
import com.example.duan_qlsach.dao.SachDAO;
import com.example.duan_qlsach.model.ChiTietHoaDon;
import com.example.duan_qlsach.model.HoaDon;
import com.example.duan_qlsach.model.Sach;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ThemHoaDonActivity extends AppCompatActivity {
    RecyclerView re;
    SearchView searchView;
    EditText ngay, soluong;
    List<ChiTietHoaDon> list;
    ChiTietHoaDonAdapter adapter;
    List<Sach> listsach;
    TextView tvtongtien;
    int tongtien = 0;
    MatrixCursor cursor;
    int masach;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themhoadon);
        anhxa();
        list = new ArrayList<>();
        search();

    }

    //chức năng cho tìm kiếm sách
    private void search() {
        //danh sach list suggest
        SachDAO sachDAO = new SachDAO(this);
        listsach = sachDAO.selectall();
        //search
        CursorAdapter cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,
                null, new String[]{"text"}, new int[]
                {android.R.id.text1}, 0);
        searchView.setSuggestionsAdapter(cursorAdapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String[] columns = new String[]{"_id", "text"};
                cursor = new MatrixCursor(columns);
                for (int i = 0; i < listsach.size(); i++) {
                    if (listsach.get(i).getTensach().toLowerCase().contains(newText.toLowerCase())) {
                        cursor.addRow(new Object[]{listsach.get(i).getMasach(), listsach.get(i).getMasach() + "-" + listsach.get(i).getTensach()});
                    }
                }
                searchView.getSuggestionsAdapter().changeCursor(cursor);
                return true;
            }
        });
        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {
                return false;
            }

            @Override
            public boolean onSuggestionClick(int position) {
                cursor.moveToPosition(position);
                masach = cursor.getInt(0);
                String text = cursor.getString(1);
                searchView.setQuery(text, true);
                return false;
            }
        });
    }


    private void anhxa() {
        re = findViewById(R.id.re);
        ngay = findViewById(R.id.ngay);
        soluong = findViewById(R.id.soluong);
        tvtongtien = findViewById(R.id.tvtongtien);
        searchView = findViewById(R.id.sachthat);
    }

    //thềm vào giỏ hàng
    public void themvaogiohang(View view) {
        if (!checkform.isEmpty(ngay, soluong) && !searchView.getQuery().equals("")) {
            SachDAO s = new SachDAO(this);
            ChiTietHoaDon cthd = new ChiTietHoaDon();
            cthd.setSoluong(Integer.parseInt(soluong.getText().toString()));
            cthd.setMasach(masach);
            cthd.setTensach(s.gettensach(cthd.getMasach()));
            cthd.setTongtien(s.getgia(cthd.getMasach()) * cthd.getSoluong());
            list.add(cthd);
            adapter = new ChiTietHoaDonAdapter(ThemHoaDonActivity.this, list);
            re.setLayoutManager(new LinearLayoutManager(this));
            re.setAdapter(adapter);
            tongtien += cthd.getTongtien();
            tvtongtien.setText("Thành tiền: " + tongtien);
        }else {
            Toast.makeText(this, "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }

    }

    //thêm vào csdl
    public void them(View view) {
        if (!checkform.isEmpty(ngay, soluong) && !searchView.getQuery().equals("")) {
            HoaDonDAO hddao = new HoaDonDAO(this);
            HoaDon hoadon = new HoaDon();
            hoadon.setNgay(ngay.getText().toString());
            hoadon.setTongtien(tongtien);
            if (hddao.inserthoadon(hoadon)) {
                ChiTietHoaDonDAO cthddao = new ChiTietHoaDonDAO(this);
                int mahd = hddao.getlasthoadon().getMahd();
                for (int i = 0; i < list.size(); i++) {
                    ChiTietHoaDon hd = list.get(i);
                    hd.setMahd(mahd);
                    cthddao.insertchitiethoadon(hd);
                }
                Toast.makeText(this, "THÊM THÀNH CÔNG", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "THÊM THẤT BẠI", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }

    }

    //chọn ngày
    public void chonngay(View view) {
        Calendar c = Calendar.getInstance();
        final int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String day=dayOfMonth+"";
                String month1=(month+1)+"";
                if(dayOfMonth<10){
                    day=0+""+dayOfMonth;
                }
                if(month<9){
                    month1=0+""+(month+1);
                }
                ngay.setText(year + "-" + month1 + "-" + day);
            }
        }, year, month, day);
        dialog.show();
    }
}
