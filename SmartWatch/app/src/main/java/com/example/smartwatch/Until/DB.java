package com.example.smartwatch.Until;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {


    public DB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void deleteGioHang(){
        SQLiteDatabase database=  getWritableDatabase();
        database.execSQL("Delete FROM GIOHANG");
    }
    public void deleteUser(){
        SQLiteDatabase database=  getWritableDatabase();
        database.execSQL("Delete FROM User");
    }
    public void themUser(String sdt, String tenKH, String diaChi, String email,String matkhau){
        SQLiteDatabase database=  getWritableDatabase();
        database.execSQL("INSERT INTO User VALUES('"+sdt+"','"+tenKH+"','"+diaChi+"','"+email+"','"+matkhau+"')");
        System.out.println("======================================="+ "Thêm thành công");
    }
public void QueryData(String sql){
        SQLiteDatabase database= getWritableDatabase();
        database.execSQL(sql);
}

//    public Cursor GetData(String sql){
//        SQLiteDatabase database= getReadableDatabase();
//        return database.rawQuery(sql,null);
//    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
