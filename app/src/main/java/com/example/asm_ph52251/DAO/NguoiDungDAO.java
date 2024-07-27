package com.example.asm_ph51505.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm_ph51505.database.DbHelper;
import com.example.asm_ph51505.database.DbHelper;

public class NguoiDungDAO {
    private DbHelper dbHelper;

    public NguoiDungDAO(Context context){
        dbHelper = new DbHelper(context);
    }

    //login
    public boolean checklogin(String username, String password){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NGUOIDUNG WHERE TENDANGNHAP = ? AND MATKHAU = ?",new String[]{username,password});
        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    //Dang ky
    public boolean DangKy(String username,String hoTen, String password){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("TENDANGNHAP",username);
        contentValues.put("HOVATEN",hoTen);
        contentValues.put("MATKHAU",password);

        long check = sqLiteDatabase.insert("NGUOIDUNG",null,contentValues);
        if(check != -1){
            return true;
        }else {
            return false;
        }
    }
}
