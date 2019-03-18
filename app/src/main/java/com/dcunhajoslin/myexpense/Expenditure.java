package com.dcunhajoslin.myexpense;

import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class Expenditure extends AppCompatActivity {
    DBManager dbManager;
    Cursor cursor;
    TextView expenditure;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenditure);
        expenditure=findViewById(R.id.today);

        listView=findViewById(R.id.listview_today);
        ArrayList<ExpenseModel> list = new ArrayList<>();

        Date c = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            c = Calendar.getInstance().getTime();


            SimpleDateFormat df = new SimpleDateFormat("yyyy-M-d");
            String formattedDate = df.format(c);

            Log.i("TAG","formatted date"+formattedDate);


            dbManager=new DBManager(this);
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
                ExpenditureAdapter adapter = new ExpenditureAdapter(this, list);
                listView.setAdapter(adapter);

                Log.i("TAG","total per day is "+count);
                expenditure.setText("Your Today's Expense is: "+String.valueOf(count));

            }
            else{
                expenditure.setText("You have no Expense Today" );

            }



    cursor.close();

        }
    }
}
