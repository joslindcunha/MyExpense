package com.dcunhajoslin.myexpense;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class viewExpense extends Fragment {

    ListView listView;
    ArrayList<HashMap<String, String>> detaillist;

    private static final String tag1 = "name";
    private static final String tag2 = "type";
    private static final String tag3 = "date";
    private static final String tag4 = "amount";

    DBManager dbManager;
    Cursor cursor;
    TextView textView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_view_expense, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);

       // detaillist = new ArrayList<HashMap<String, String>>();
        super.onActivityCreated(savedInstanceState);
        View v = getView();
        dbManager=new DBManager(getContext());
        dbManager.open();

            textView=v.findViewById(R.id.text_empty);
        // TO-DO check for empty

        cursor=dbManager.get_expenses();
//        HashMap<String, String> detaillist1 = new HashMap<String, String>();

        if(cursor.getCount()==0){
            textView.setVisibility(View.VISIBLE);

        }
        else {

            textView.setVisibility(View.INVISIBLE);
            //setContentView(R.layout.activity_view_expense);
            listView = v.findViewById(R.id.listview);
            ArrayList<ExpenseModel> list = new ArrayList<>();
            while (cursor.moveToNext()) {
                String exp_name = cursor.getString(cursor.getColumnIndex("name"));
                //   String exp_type=cursor.getString(cursor.getColumnIndex("type"));
                String exp_date = cursor.getString(cursor.getColumnIndex("date"));
                String exp_amount = cursor.getString(cursor.getColumnIndex("amount"));
                Log.i("TAG", "details " + exp_name + exp_date + exp_amount);


                list.add(new ExpenseModel(exp_date, exp_amount, exp_name));

            }


            ExpenseAdapter adapter = new ExpenseAdapter(getContext(), list);
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

