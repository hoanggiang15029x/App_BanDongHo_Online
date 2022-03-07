package com.example.smartwatch.Object;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.smartwatch.Until.Database;

public class UpdateUser {
    public static void InsertUser(Context context, String sdt, String tenKH, String diaChi, String email,String matkhau) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sdt", sdt);
        contentValues.put("tenKH", tenKH);
        contentValues.put("diaChi", diaChi);
        contentValues.put("email", email);
        contentValues.put("matkhau", matkhau);
        SQLiteDatabase database = Database.initDatabase((Activity) context, "GioHang.db");
        database.insert("User", null, contentValues);
    }
}
