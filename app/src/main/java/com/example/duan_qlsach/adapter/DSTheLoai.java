package com.example.duan_qlsach.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_qlsach.R;
import com.example.duan_qlsach.dao.TheLoaiDAO;
import com.example.duan_qlsach.model.TheLoai;

import java.util.List;

public class DSTheLoai extends RecyclerView.Adapter<DSTheLoai.viewholder> {
    Context context;
    List<TheLoai> list;

    public DSTheLoai(Context context, List<TheLoai> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_dstheloai, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, final int position) {
        holder.matl.setText(list.get(position).getMatl() + "");
        holder.tentl.setText(list.get(position).getTentl());
        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TheLoaiDAO tl = new TheLoaiDAO(context);
                if (tl.deleteTheloai(list.get(position).getMatl())) {
                    Toast.makeText(context, "XOA THANH CONG", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "XOA THAT BAI", Toast.LENGTH_SHORT).show();
                }
                list.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView matl, tentl;
        ImageView xoa;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            matl = itemView.findViewById(R.id.matl);
            tentl = itemView.findViewById(R.id.tentl);
            xoa = itemView.findViewById(R.id.xoa);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TheLoai l = list.get(getLayoutPosition());
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    View view = LayoutInflater.from(context).inflate(R.layout.dialog_theloai, null);
                    builder.setView(view);
                    TextView matl = view.findViewById(R.id.matl);
                    TextView tentl = view.findViewById(R.id.tentl);
                    TextView vitri = view.findViewById(R.id.vitri);
                    TextView mota = view.findViewById(R.id.mota);
                    matl.setText("Mã thể loại: " + l.getMatl());
                    tentl.setText("Tên thể loại: " + l.getTentl());
                    vitri.setText("Vị trí: " + l.getVitri());
                    mota.setText("Mô tả: " + l.getMota());
                    builder.show();
                }
            });
        }
    }
}
