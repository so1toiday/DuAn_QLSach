package com.example.duan_qlsach.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_qlsach.R;
import com.example.duan_qlsach.model.ThongKeSachBanChay;

import java.util.List;

public class SachBanChayAdapter extends RecyclerView.Adapter<SachBanChayAdapter.viewholder> {
    Context context;
    List<ThongKeSachBanChay> list;

    public SachBanChayAdapter(Context context, List<ThongKeSachBanChay> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SachBanChayAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sachbanchay, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SachBanChayAdapter.viewholder holder, int position) {
        holder.stt.setText(position + 1 + "");
        holder.tensach.setText(list.get(position).getTensach());
        holder.soluong.setText(list.get(position).getSoluong() + "");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView stt, tensach, soluong;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            stt = itemView.findViewById(R.id.stt);
            tensach = itemView.findViewById(R.id.tensach);
            soluong = itemView.findViewById(R.id.soluong);
        }
    }
}
