package com.dcunhajoslin.myexpense;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by hp on 10-02-2019.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String CONTACTS_TABLE_NAME = "contacts";


    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_EMAIL = "email";
    public static final String CONTACTS_COLUMN_ADDRESS = "address";
    public static final String CONTACTS_COLUMN_PASSWORD = "password";
    public static final String CONTACTS_COLUMN_PHONE = "phone";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
        Log.i("TAG","db helper constrcutor ");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i("TAG","create tables  ");
        db.execSQL("create table if not exists contacts (name text,email text,phone text,address text,password text)");
        db.execSQL("create table  if not exists expenses (exp_id integer primary key autoincrement, date text,name text,email text,type text,amount text)");
        Log.i("TAG",db.getPath());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        db.execSQL("DROP TABLE IF EXISTS expenses");
        onCreate(db);
    }


}

