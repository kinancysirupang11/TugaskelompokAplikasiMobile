package com.kinan.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "cruddb";
    protected static final String TABLE_NAME = "crud";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //context.deleteDatabase(DATABASE_NAME); //buka ini untuk delete database
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //membuat kolom id dg tipe integer dan sebagai primary key, dan input dg tipe text
        String sqlMutabaah = "CREATE TABLE " + TABLE_NAME + " " +
                "( ID INTEGER PRIMARY KEY, INPUT TEXT ) ";
        db.execSQL(sqlMutabaah);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + "";
        db.execSQL(sql);
        onCreate(db);
    }
}