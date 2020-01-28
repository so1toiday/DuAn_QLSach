package com.example.duan_qlsach.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.duan_qlsach.R;
import com.example.duan_qlsach.dao.HoaDonDAO;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;

public class bieudoActivity extends AppCompatActivity {
BarChart barchar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bieudo);
        anhxa();
        bieudo();
    }

    private void anhxa() {
        barchar=findViewById(R.id.barchar);
    }

    private void bieudo() {
        HoaDonDAO hoaDonDAO=new HoaDonDAO(this);

        //khởi tạo list data
        ArrayList<BarEntry> entries=new ArrayList<>();
        entries.add(new BarEntry(0,hoaDonDAO.tongtienquy("01","02","03")));
        entries.add(new BarEntry(1,hoaDonDAO.tongtienquy("04","05","06")));
        entries.add(new BarEntry(2,hoaDonDAO.tongtienquy("07","08","09")));
        entries.add(new BarEntry(3,hoaDonDAO.tongtienquy("10","11","12")));

        //dataset
        BarDataSet dataset=new BarDataSet(entries, "THỐNG KÊ THEO QUÝ");
        //định nghĩa các quý
        final ArrayList<String> quy=new ArrayList<>();
        quy.add("QUÝ 1");
        quy.add("QUÝ 2");
        quy.add("QUÝ 3");
        quy.add("QUÝ 4");
        Description description = barchar.getDescription();
        description.setPosition(0,0);
        description.setText("");

        XAxis xAxis = barchar.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        xAxis.setLabelCount(4);
        xAxis.setValueFormatter(new ValueFormatter() {

            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return quy.get((int)value);
            }
        });
        //đinh nghĩa bardata
        BarData data = new BarData(dataset);
        barchar.setData(data);
    }

}
