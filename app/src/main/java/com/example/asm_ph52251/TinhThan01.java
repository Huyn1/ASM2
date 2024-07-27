package com.example.asm_ph51505;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_ph51505.DAO.TinhThanDAO;
import com.example.asm_ph51505.adapter.TinhThanAdapter;
import com.example.asm_ph51505.model.TinhThan;
import com.example.asm_ph51505.DAO.TinhThanDAO;
import com.example.asm_ph51505.adapter.TinhThanAdapter;
import com.example.asm_ph51505.model.TinhThan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TinhThan01 extends AppCompatActivity {

    private TinhThanDAO tinhThanDAO;
    private RecyclerView rvTinhThan;
    private FloatingActionButton btnadd;
    private ArrayList<TinhThan> list ;
    private TinhThanAdapter adapter = adapter = new TinhThanAdapter(tinhThanDAO,this,list);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tinh_than01);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnadd = findViewById(R.id.btnaddbt);
        rvTinhThan = findViewById(R.id.rvTinhThan);
        tinhThanDAO = new TinhThanDAO(this);


        list = tinhThanDAO.getDS();
        LoadData();

        //adapter
//        ArrayList<TinhThan> list = tinhThanDAO.getDS();
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
//        rvTinhThan.setLayoutManager(linearLayoutManager);
//        TinhThanAdapter adapter = new TinhThanAdapter(tinhThanDAO,this,list);
//        rvTinhThan.setAdapter(adapter);

        //add
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

    }



    private void LoadData(){
        ArrayList<TinhThan> list = tinhThanDAO.getDS();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvTinhThan.setLayoutManager(linearLayoutManager);

        TinhThanAdapter adapter = new TinhThanAdapter(tinhThanDAO,this,list);
        rvTinhThan.setAdapter(adapter);
    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add,null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        //xu ly chuc nang trong dialog
        EditText edtContent = view.findViewById(R.id.edtContent1);
        EditText edtDate = view.findViewById(R.id.edtDate1);
        Button btnAdd1 = view.findViewById(R.id.btnAdd1);
        Button btnHuy = view.findViewById(R.id.btnHuy);

        btnAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = edtContent.getText().toString();
                String date = edtDate.getText().toString();
                int iduser = 0;

                if(content.length() == 0){
                    Toast.makeText(TinhThan01.this, "Vui lòng nhập nội dung", Toast.LENGTH_SHORT).show();
                }else{
                    //TinhThan tinhThan = new TinhThan(date, content);
                    TinhThan tinhThan = new TinhThan(date,content,iduser);
                    boolean check = tinhThanDAO.themBVmoi(tinhThan);
                    if (check){
                        //list.add(tinhThan);
                        //adapter.notifyDataSetChanged();
                        Toast.makeText(TinhThan01.this, "Bạn đã thêm thành công nội dung", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();// thoat dialog
                        LoadData();
                    } else{
                        Toast.makeText(TinhThan01.this, "Tải nội dung không thành công", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }



}