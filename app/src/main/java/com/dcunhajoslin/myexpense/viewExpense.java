package com.dcunhajoslin.myexpense;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class viewExpense extends AppCompatActivity {

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

       // detaillist = new ArrayList<HashMap<String, String>>();
        dbManager=new DBManager(this);
        dbManager.open();

        cursor=dbManager.get_expenses();
//        HashMap<String, String> detaillist1 = new HashMap<String, String>();

        if(cursor.getCount()==0){

            setContentView(R.layout.activity_error);
//            Intent intent=new Intent(viewExpense.this,Error.class);
//            startActivity(intent);
        }
        else {
            setContentView(R.layout.activity_view_expense);
            listView=findViewById(R.id.listview);
            ArrayList<ExpenseModel> list = new ArrayList<>();
        while (cursor.moveToNext()){
            String exp_name=cursor.getString(cursor.getColumnIndex("name"));
         //   String exp_type=cursor.getString(cursor.getColumnIndex("type"));
            String exp_date=cursor.getString(cursor.getColumnIndex("date"));
            String exp_amount=cursor.getString(cursor.getColumnIndex("amount"));
            Log.i("TAG","details "+exp_name+exp_date+exp_amount);


           list.add(new ExpenseModel(exp_date,exp_amount,exp_name));

        }



            ExpenseAdapter adapter = new ExpenseAdapter(this, list);
            listView.setAdapter(adapter);
        }





//        ListAdapter adapter1;
//        adapter1 = new SimpleAdapter(viewExpense.this, detaillist, R.layout.expense_item,
//                new String[]{tag1,tag3,tag4}, new int[]{R.id.name,  R.id.date,R.id.amount});
//        listView.setAdapter(adapter1);
//


        cursor.close();



    }
}

