package com.example.duan_qlsach.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan_qlsach.R;
import com.example.duan_qlsach.dao.NguoiDungDAO;
import com.example.duan_qlsach.model.NguoiDung;

import java.util.List;

public class DSNguoiDung extends RecyclerView.Adapter<DSNguoiDung.viewholder> {
    Context context;
    List<NguoiDung> list;

    public DSNguoiDung(Context context, List<NguoiDung> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new DSNguoiDung.viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, final int position) {
        final NguoiDung nd = list.get(position);
        if (position % 2 == 0) {
            holder.ivAnh.setImageResource(R.drawable.emtwo);
        }
        holder.tvTen.setText(nd.getFullname());
        holder.tvPhone.setText(nd.getPhone());
        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setMessage("Bạn muốn xóa " + nd.getFullname() + " không?");
                dialog.setTitle("Xóa " + nd.getFullname());
                dialog.setPositiveButton("XÓA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        NguoiDungDAO db = new NguoiDungDAO(context);
                        db.delete(nd.getUsername());
                        list.remove(position);
                        notifyDataSetChanged();
                    }
                });
                dialog.setNegativeButton("KHÔNG", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });
        holder.btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_suanguoidung, null);
                builder.setView(view);
                final EditText edUserName = view.findViewById(R.id.edUserName);
                final EditText edPassWord = view.findViewById(R.id.edPassWord);
                final EditText edFullName = view.findViewById(R.id.edFullName);
                final EditText edSDT = view.findViewById(R.id.edSDT);
                final EditText edAddress = view.findViewById(R.id.edAddress);
                Button btnThem = view.findViewById(R.id.btnThem);
                Button btnhuy = view.findViewById(R.id.btnHuy);
                final NguoiDungDAO db = new NguoiDungDAO(context);
                edUserName.setText(nd.getUsername());
                edPassWord.setText(nd.getPass());
                edFullName.setText(nd.getFullname());
                edSDT.setText(nd.getPhone());
                edAddress.setText(nd.getAddress());
                final AlertDialog alertDialog = builder.create();
                btnhuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                btnThem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NguoiDung nd = list.get(position);
                        nd.setPass(edPassWord.getText().toString());
                        nd.setPhone(edSDT.getText().toString());
                        nd.setAddress(edAddress.getText().toString());
                        nd.setFullname(edFullName.getText().toString());
                        db.SuaNguoiDung(nd, nd.getUsername());
                        list.set(position, nd);
                        notifyDataSetChanged();
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView tvTen, tvPhone;
        ImageButton btnSua, btnXoa;
        ImageView ivAnh;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            tvTen = itemView.findViewById(R.id.tvTen);
            tvPhone = itemView.findViewById(R.id.tvThongtin);
            btnSua = itemView.findViewById(R.id.btnSua);
            btnXoa = itemView.findViewById(R.id.btnXoa);
            ivAnh = itemView.findViewById(R.id.ivAnh);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View view = LayoutInflater.from(context).inflate(R.layout.dialog_thongtinnguoidung, null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setView(view);
                    int position = getLayoutPosition();
                    NguoiDung nd = list.get(position);
                    AlertDialog dialog = builder.create();
                    TextView tk = view.findViewById(R.id.tk);
                    TextView mk = view.findViewById(R.id.mk);
                    TextView phone = view.findViewById(R.id.phone);
                    TextView name = view.findViewById(R.id.name);
                    TextView address = view.findViewById(R.id.address);

                    tk.setText(nd.getUsername());
                    mk.setText(nd.getPass());
                    phone.setText(nd.getPhone());
                    name.setText(nd.getFullname());
                    address.setText(nd.getAddress());
                    dialog.show();
                }
            });
        }


    }
}
