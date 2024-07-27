package com.example.asm_ph51505.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(@Nullable Context context) {
        super(context, "Database",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_nguoidung = "CREATE TABLE NGUOIDUNG (\n" +
                "    TENDANGNHAP TEXT PRIMARY KEY,\n" +
                "    HOVATEN     TEXT,\n" +
                "    MATKHAU     TEXT,\n" +
                "    GIOITINH    TEXT,\n" +
                "    CHIEUCAO    REAL,\n" +
                "    CANNANG     REAL\n" +
                ");";
        db.execSQL(sql_nguoidung);
        String sql_baiviet = "CREATE TABLE BAIVIET ( ID      INTEGER PRIMARY KEY AUTOINCREMENT , NOIDUNG TEXT, NGAYGIO TEXT, ID_USER INTEGER REFERENCES NGUOIDUNG (TENDANGNHAP)  );";
        db.execSQL(sql_baiviet);

        //tao du lieu
        String sql_nguoidung1 ="INSERT INTO NGUOIDUNG VALUES ('cuongsp','LEMANHCUONG','abc',null,null,null);";
        db.execSQL(sql_nguoidung1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
            db.execSQL("DROP TABLE IF EXISTS BAIVIET");
            onCreate(db);
        }
    }
}
