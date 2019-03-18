package com.dcunhajoslin.myexpense;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class addexpense extends AppCompatActivity {
    DatePicker date;
    EditText name,type,amt;
    Button button1;
    DBHelper dbHelper;
    DBManager mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexpense);
        mydb=new DBManager(this);
        mydb.open();

        dbHelper = new DBHelper(this);


        button1 = findViewById(R.id.enter);
        date = findViewById(R.id.cal);
        name = findViewById(R.id.name);
       // type = findViewById(R.id.type);
        amt = findViewById(R.id.amt);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String day = ""+ date.getDayOfMonth();
                String month = "" + (date.getMonth()+1);
                String year = "" + date.getYear();
                String date=day+"-"+month+"-"+year;
                // display the values by using a toast
//                Toast.makeText(getApplicationContext(), day + "\n" + month + "\n" + year, Toast.LENGTH_LONG).show();
                long i= mydb.insert_expense(date,name.getText().toString(),Long.parseLong(amt.getText().toString()));
                Log.i("TAG",String.valueOf(i));
                if(i>0){
                    Toast.makeText(addexpense.this, "Data Entered", Toast.LENGTH_SHORT).show();


                    startActivity(new Intent(addexpense.this, expensemanager.class));
                    finish();


                }
                else{
                    Toast.makeText(addexpense.this, "Some error occurred", Toast.LENGTH_SHORT).show();


                    startActivity(new Intent(addexpense.this, addexpense.class));
                    finish();

                }

            }
        });
    }
}