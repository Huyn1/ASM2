package com.example.asm_ph51505.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_ph51505.DAO.TinhThanDAO;
import com.example.asm_ph51505.R;
import com.example.asm_ph51505.model.TinhThan;
import com.example.asm_ph51505.DAO.TinhThanDAO;
import com.example.asm_ph51505.model.TinhThan;

import java.util.ArrayList;

public class TinhThanAdapter extends RecyclerView.Adapter<TinhThanAdapter.ViewHolder>{

    private Context context;
    private ArrayList<TinhThan> list;
    private final TinhThanDAO tinhThanDAO;

    public TinhThanAdapter(TinhThanDAO tinhThanDAO, Context context, ArrayList<TinhThan> list) {
        this.tinhThanDAO = tinhThanDAO;
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view= inflater.inflate(R.layout.item_listviewtinhthan,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtdate.setText(list.get(position).getDate());
        holder.txttext.setText(list.get(position).getText());


        // thao tac xoa
        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDel(list.get(holder.getAdapterPosition()).getText(),list.get(holder.getAdapterPosition()).getDate(),list.get(holder.getAdapterPosition()).getId());
            }
        });

        //thao tac sua
        holder.btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogUpdate(list.get(holder.getAdapterPosition()));
            }
        });



    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtdate, txttext;
        ImageView btnupdate, btndelete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtdate = itemView.findViewById(R.id.tvDate);
            txttext = itemView.findViewById(R.id.tvText);
            btnupdate = itemView.findViewById(R.id.btnUpdate);
            btndelete = itemView.findViewById(R.id.btnDelete);

        }
    }


    private void showDialogDel(String text,String date, int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("THÔNG BÁO");
        builder.setMessage("Bạn có muốn xoá bài viết này không?");
        builder.setPositiveButton("Xoá", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean check = tinhThanDAO.XoaBV(id);
                if(check){
                    Toast.makeText(context, "Xoá thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                } else {
                    Toast.makeText(context, "Xoá thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Huỷ",null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showDialogUpdate(TinhThan tinhThan){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_update,null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        EditText edtContent = view.findViewById(R.id.edtContent1);
        EditText edtDate = view.findViewById(R.id.edtDate1);
        Button btnSua1 = view.findViewById(R.id.btnSua1);
        Button btnHuy = view.findViewById(R.id.btnHuy);

        //dua dl edt
        edtContent.setText(tinhThan.getText());
        edtDate.setText(tinhThan.getDate());

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btnSua1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id =tinhThan.getId();
                String text = edtContent.getText().toString();
                String date = edtDate.getText().toString();
                int iduser = 0;

                if(text.trim().length() == 0 || date.trim().length() == 0){
                    Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    TinhThan tinhThanUpdate = new TinhThan(id,text,date,iduser);
                    boolean check = tinhThanDAO.SuaBV(tinhThanUpdate);
                    if(check){
                        Toast.makeText(context, "Update thành công!", Toast.LENGTH_SHORT).show();
                        list.clear();
                        list = tinhThanDAO.getDS();
                        notifyDataSetChanged();
                        alertDialog.dismiss();
                    }
                    else {
                        Toast.makeText(context, "Update thất bại!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }


    private void loadData(){
        list.clear();
        list = tinhThanDAO.getDS();
        notifyDataSetChanged();
    }
}
