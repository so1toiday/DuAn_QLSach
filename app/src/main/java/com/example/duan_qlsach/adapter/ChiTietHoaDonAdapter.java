package com.example.duan_qlsach.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
//list phần giỏ hàng

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_qlsach.R;
import com.example.duan_qlsach.model.ChiTietHoaDon;

import java.util.List;

public class ChiTietHoaDonAdapter extends RecyclerView.Adapter<ChiTietHoaDonAdapter.viewholder> {
    List<ChiTietHoaDon> list;
    Context context;

    public ChiTietHoaDonAdapter(Context context, List<ChiTietHoaDon> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_danhsachhd, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        ChiTietHoaDon hdct = list.get(position);
        holder.soluong.setText(hdct.getSoluong() + "");
        holder.sach.setText(hdct.getTensach() + "");
        holder.tien.setText(hdct.getTongtien() + "");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView mahd, sach, soluong, tien;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            sach = itemView.findViewById(R.id.sach);
            soluong = itemView.findViewById(R.id.soluong);
            tien = itemView.findViewById(R.id.tien);
        }
    }
}
