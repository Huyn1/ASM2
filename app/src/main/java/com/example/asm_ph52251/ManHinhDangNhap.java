package com.example.asm_ph51505;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.asm_ph51505.DAO.NguoiDungDAO;
import com.example.asm_ph51505.DAO.NguoiDungDAO;


public class ManHinhDangNhap extends AppCompatActivity {

    EditText edtusername, edtpasssword;
    Button btndangnhap;
    TextView tvdangky;
    private NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_man_hinh_dang_nhap);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ManHinhDangNhap), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtusername = findViewById(R.id.editTextUsername);
        edtpasssword = findViewById(R.id.editTextPassword);
        btndangnhap = findViewById(R.id.buttonLogin);
        tvdangky = findViewById(R.id.textViewRegisterLink);

        nguoiDungDAO = new NguoiDungDAO(this);


        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtusername.getText().toString();
                String password = edtpasssword.getText().toString();

                boolean check = nguoiDungDAO.checklogin(user,password);

                if(check == true){
                    startActivity(new Intent(ManHinhDangNhap.this,MainActivity.class));
                    Toast.makeText(ManHinhDangNhap.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ManHinhDangNhap.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
                
            }
        });

        tvdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManHinhDangNhap.this, ManHinhDangKy.class));
            }
        });
    }
}