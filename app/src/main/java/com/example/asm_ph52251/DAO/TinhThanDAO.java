package com.example.asm_ph51505.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm_ph51505.database.DbHelper;
import com.example.asm_ph51505.model.TinhThan;
import com.example.asm_ph51505.database.DbHelper;
import com.example.asm_ph51505.model.TinhThan;

import java.util.ArrayList;

public class TinhThanDAO {
    private DbHelper dbHelper;
    private SQLiteDatabase mDatabase;

    public TinhThanDAO(Context context){
        dbHelper = new DbHelper(context);
        mDatabase = dbHelper.getWritableDatabase();
    }
    // lay ds
    public ArrayList<TinhThan> getDS(){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ArrayList<TinhThan> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM BAIVIET",null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                TinhThan tinhThan = new TinhThan();
                tinhThan.setId(cursor.getInt(0));
                tinhThan.setText(cursor.getString(1));
                tinhThan.setDate(cursor.getString(2));
                tinhThan.setIduser(cursor.getInt(3));
                list.add(tinhThan);
            }while (cursor.moveToNext());
        }
        return list;
    }

    //them ds
    public boolean themBVmoi(TinhThan tinhThan){
        mDatabase =dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("NOIDUNG", tinhThan.getText());
        contentValues.put("NGAYGIO", tinhThan.getDate());
        contentValues.put("ID_USER", tinhThan.getIduser());

        long check = mDatabase.insert("BAIVIET",null,contentValues);
        return check != -1;
    }

    //xoa ds
    public boolean XoaBV(int id){
        SQLiteDatabase sqliteDatabase = dbHelper.getWritableDatabase();
        int check = sqliteDatabase.delete( "BAIVIET", "ID = ?", new String[]{String.valueOf(id)});
        if(check <= 0) return false;
        return true;
    }

    // sua ds
    public boolean SuaBV(TinhThan tinhThan){
        mDatabase = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("NOIDUNG",tinhThan.getText());
        contentValues.put("NGAYGIO",tinhThan.getDate());
        contentValues.put("ID_USER",tinhThan.getIduser());

        int check = mDatabase.update("BAIVIET",contentValues,"ID = ?", new String[]{String.valueOf(tinhThan.getId())});
        if (check <= 0) return false;
        return true;
    }
}
