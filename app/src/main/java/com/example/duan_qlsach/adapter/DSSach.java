package com.example.duan_qlsach.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_qlsach.R;
import com.example.duan_qlsach.dao.SachDAO;
import com.example.duan_qlsach.model.Sach;

import java.util.ArrayList;
import java.util.List;


public class DSSach extends RecyclerView.Adapter<DSSach.viewholder> implements Filterable {
    List<Sach> list1;
    List<Sach> list;
    Context context;

    public DSSach(Context context, List<Sach> list) {
        this.list = list;
        this.context = context;
        list1=list;
    }

    @NonNull
    @Override
    public DSSach.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sach, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DSSach.viewholder holder, final int position) {
        Sach s = list.get(position);
        holder.ten.setText(s.getTensach());
        holder.ma.setText(s.getMasach() + "");
        holder.gia.setText(s.getGia() + "đ");
        holder.anh.setImageResource(R.drawable.bookicon);
        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SachDAO s = new SachDAO(context);
                if (s.deleteSach(list.get(position).getMasach())) {
                    Toast.makeText(context, "XÓA THÀNH CÔNG", Toast.LENGTH_SHORT).show();
                    list.remove(position);
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "XÓA THẤT BẠI", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                List<Sach> listfilter=new ArrayList<>();
                if(charString.length()==0){
                    listfilter=list1;
                }else {
                    for (Sach s : list1) {
                        if (s.getTensach().toLowerCase().contains(charString.toLowerCase())) {
                            listfilter.add(s);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.count = listfilter.size();
                results.values = listfilter;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list= (List<Sach>) results.values;
                notifyDataSetChanged();
            }
        };

    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView ten, gia, ma;
        ImageView anh, xoa;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.ten);
            gia = itemView.findViewById(R.id.gia);
            ma = itemView.findViewById(R.id.ma);
            anh = itemView.findViewById(R.id.iv_anh);
            xoa = itemView.findViewById(R.id.xoa);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = getLayoutPosition();
                    Sach s = list.get(i);
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    View view = LayoutInflater.from(context).inflate(R.layout.dialog_sach, null);
                    builder.setView(view);
                    TextView masach = view.findViewById(R.id.masach);
                    TextView tensach = view.findViewById(R.id.tensach);
                    TextView theloai = view.findViewById(R.id.theloai);
                    TextView nxb = view.findViewById(R.id.nxb);
                    TextView tacgia = view.findViewById(R.id.tacgia);
                    TextView gia = view.findViewById(R.id.gia);
                    TextView soluong = view.findViewById(R.id.soluong);
                    masach.setText("Mã sách: " + s.getMasach());
                    tensach.setText("Tên sách: " + s.getTensach());
                    nxb.setText("NXB: " + s.getNxb());
                    theloai.setText("Mã thể loại: " + s.getMatl() + "");
                    tacgia.setText("Tác giả: " + s.getTacgia());
                    gia.setText("Giá: " + s.getGia() + "");
                    soluong.setText("Số lượng: " + s.getSoluong() + "");
                    builder.show();
                }
            });
        }
    }
}
