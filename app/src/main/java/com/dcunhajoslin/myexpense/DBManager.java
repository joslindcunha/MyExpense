package com.dcunhajoslin.myexpense;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by hp on 10-02-2019.
 */

public class DBManager {
    private DBHelper dbHelper;
    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;

    }

    public DBManager open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;

    }

    public long insertContact(String name, String phone, String email, String address, String password) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("address", address);
        contentValues.put("password", password);
        return database.insert("contacts", null, contentValues);

    }

    public Cursor get_values() {
        Cursor cursor;
        cursor = database.rawQuery("select * from contacts", new String[]{});
        return cursor;
    }

    public long update_data(String name)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "efg");
        return database.update("contacts", contentValues, "name = ? ", new String[]{name});

    }


    public long delete_data(String name)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put("name","efg");
        return  database.delete("contact","name = ?", new String[]{name});
    }

    public void close()
    {
        dbHelper.close();
    }

    public long insert_expense(String date,String name,long amount){
        SharedPreferences sp=context.getSharedPreferences("UserInfo",Context.MODE_PRIVATE);
        ContentValues contentValues=new ContentValues();
        contentValues.put("date",date);
        contentValues.put("name",name);
        String mail=sp.getString("email",null);
        Log.i("TAG","in insert table expense-- email "+mail);
        contentValues.put("email",mail);
        contentValues.put("amount",amount);
        return   database.insert("expenses",null,contentValues);
    }
    public Cursor get_expenses(){
        SharedPreferences sp=context.getSharedPreferences("UserInfo",Context.MODE_PRIVATE);
        ContentValues contentValues=new ContentValues();
        String mail=sp.getString("email",null);
        return database.rawQuery("select * from expenses where email=\""+mail+"\"",new String[]{});
    }

    public Cursor get_todays_expenses(String today){
        SharedPreferences sp=context.getSharedPreferences("UserInfo",Context.MODE_PRIVATE);
        ContentValues contentValues=new ContentValues();
        String mail=sp.getString("email",null);
        return database.rawQuery("select * from expenses where email=\""+mail+"\" and date=\""+today+"\"",new String[]{});
    }


}
