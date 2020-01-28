package com.example.duan_qlsach.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_qlsach.R;
import com.example.duan_qlsach.model.Hoadon_tong;

import java.util.List;
//list hóa đơn
public class DanhSachHoaDonAdapter extends RecyclerView.Adapter<DanhSachHoaDonAdapter.viewholder> {
    List<Hoadon_tong> list;
    Context context;

    public DanhSachHoaDonAdapter(Context context, List<Hoadon_tong> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_danhsachhoadon, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Hoadon_tong dshd = list.get(position);
        holder.mahd.setText("Mã HD: "+dshd.getMahd() + "");
        holder.chuoi.setText("Chi tiết: \n"+dshd.getChuoi());
        holder.ngay.setText("Ngày: "+dshd.getNgay());
        holder.tongtien.setText("Tổng tiền: "+dshd.getTongtien() + " VNĐ");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView mahd, tongtien, ngay, chuoi;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            mahd = itemView.findViewById(R.id.mahd);
            chuoi = itemView.findViewById(R.id.chuoi);
            tongtien = itemView.findViewById(R.id.tongtien);
            ngay = itemView.findViewById(R.id.ngay);
        }
    }
}
