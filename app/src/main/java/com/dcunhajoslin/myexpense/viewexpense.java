package com.dcunhajoslin.myexpense;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class viewexpense extends AppCompatActivity {
    ListView listView;
    ArrayList<HashMap<String, String>> detaillist;

    private static final String tag1 = "name";
    private static final String tag2 = "type";
    private static final String tag3 = "date";
    private static final String tag4 = "amount";

    DBManager dbManager;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        detaillist = new ArrayList<HashMap<String, String>>();
        dbManager=new DBManager(this);
        dbManager.open();

        cursor=dbManager.get_expenses();
        listView=findViewById(R.id.listview);


        while (cursor.moveToNext()){
            String exp_name=cursor.getString(cursor.getColumnIndex("name"));
         //   String exp_type=cursor.getString(cursor.getColumnIndex("type"));
            String exp_date=cursor.getString(cursor.getColumnIndex("date"));
            String exp_amount=cursor.getString(cursor.getColumnIndex("amount"));
            Log.i("TAG","details "+exp_name+exp_date+exp_amount);

            HashMap<String, String> detaillist1 = new HashMap<String, String>();
            detaillist1.put(tag1, exp_name);
           // detaillist1.put(tag2,exp_type);
            detaillist1.put(tag3,exp_date);
            detaillist1.put(tag4,exp_amount);
            detaillist.add(detaillist1);

        }
        ListAdapter adapter1;
        adapter1 = new SimpleAdapter(viewexpense.this, detaillist, R.layout.expense_layout,
                new String[]{tag1,tag3,tag4}, new int[]{R.id.name,  R.id.date,R.id.amount});
        listView.setAdapter(adapter1);
        cursor.close();



    }
}

