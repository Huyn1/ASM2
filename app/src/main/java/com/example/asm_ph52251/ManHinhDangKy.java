package com.example.asm_ph51505;

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

public class ManHinhDangKy extends AppCompatActivity {
    EditText edtHovaten, edtTendangnhap, edtpass, edtrepass;
    Button btndangky;
    TextView txtdangnhap;
    private NguoiDungDAO nguoiDungDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_man_hinh_dang_ky);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtHovaten = findViewById(R.id.editTextFullName);
        edtTendangnhap = findViewById(R.id.editTextTendangnhap);
        edtpass = findViewById(R.id.editTextPassword);
        edtrepass = findViewById(R.id.editTextRePassword);
        btndangky = findViewById(R.id.buttonRegister);
        txtdangnhap = findViewById(R.id.textViewLoginLink);

        nguoiDungDAO = new NguoiDungDAO(this);

        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname = edtHovaten.getText().toString();
                String username = edtTendangnhap.getText().toString();
                String pass = edtpass.getText().toString();
                String repass = edtrepass.getText().toString();

                if(!pass.equals(repass)){
                    Toast.makeText(ManHinhDangKy.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                } else {
                    boolean check = nguoiDungDAO.DangKy(fullname,username,pass);
                    if(check == false){

                        Toast.makeText(ManHinhDangKy.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(ManHinhDangKy.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}