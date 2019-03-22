package com.dcunhajoslin.myexpense;

import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
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
import java.util.Date;

public class Expenditure extends Fragment {
    DBManager dbManager;
    Cursor cursor;
    TextView expenditure,expname,expamount;
    ListView listView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_expenditure, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        View v = getView();
        expenditure=v.findViewById(R.id.today);

        listView=v.findViewById(R.id.listview_today);
        expname=v.findViewById(R.id.expname);
        expamount=v.findViewById(R.id.expamount);
        ArrayList<ExpenseModel> list = new ArrayList<>();

        Date c = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            c = Calendar.getInstance().getTime();


            SimpleDateFormat df = new SimpleDateFormat("yyyy-M-d");
            String formattedDate = df.format(c);

            Log.i("TAG","formatted date"+formattedDate);


            dbManager=new DBManager(getContext());
            dbManager.open();

            cursor=dbManager.get_todays_expenses(formattedDate);
            int count=0;
            Log.i("TAG","retuned "+cursor.getCount());
            if(cursor.getCount()>0){
                while (cursor.moveToNext()){
                    String exp_name=cursor.getString(cursor.getColumnIndex("name"));
                    //   String exp_type=cursor.getString(cursor.getColumnIndex("type"));
                    String exp_date=cursor.getString(cursor.getColumnIndex("date"));
                    String exp_amount=cursor.getString(cursor.getColumnIndex("amount"));
                    Log.i("TAG","details "+exp_name+exp_date+exp_amount);

                    count+=Integer.parseInt(exp_amount);
                    list.add(new ExpenseModel(exp_date,exp_amount,exp_name));



                }
                ExpenditureAdapter adapter = new ExpenditureAdapter(getContext(), list);
                listView.setAdapter(adapter);

                Log.i("TAG","total per day is "+count);
                expenditure.setText("Your Today's Expense is: "+String.valueOf(count));

            }
            else{
                expname.setVisibility(View.INVISIBLE);
                expamount.setVisibility(View.INVISIBLE);

                expenditure.setText("You have no Expense Today" );

            }



    cursor.close();

        }
    }
}
