package com.dcunhajoslin.myexpense;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class addexpense extends AppCompatActivity {

    EditText name,type,amt,edittext;
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
       // date = findViewById(R.id.cal);
        name = findViewById(R.id.name);
       // type = findViewById(R.id.type);
        amt = findViewById(R.id.amt);
        edittext=findViewById(R.id.Birthday);


        //    IMP

//        check for date picker
//        https://github.com/enrimilan/Android-Datepicker-Example

        final Calendar myCalendar;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            myCalendar = Calendar.getInstance();
        }
        else{
            myCalendar=null;
        }

        final EditText edittext= (EditText) findViewById(R.id.Birthday);
            final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {


                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        myCalendar.set(Calendar.YEAR, year);

                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String myFormat = "MM/dd/yy"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                        edittext.setText(sdf.format(myCalendar.getTime()));
                    }
                }

            };



            edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    new DatePickerDialog(addexpense.this, date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date=null;

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                String day = ""+myCalendar.get(Calendar.YEAR);
                String month = ""+ (myCalendar.get(Calendar.MONTH)+1);


                    String year = "" + myCalendar.get(Calendar.DAY_OF_MONTH);
                    date= day + "-" + month + "-" + year;
                }
                // display the values by using a toast
//                Toast.makeText(getApplicationContext(), day + "\n" + month + "\n" + year, Toast.LENGTH_LONG).show();
                long i= mydb.insert_expense(date,name.getText().toString(),Long.parseLong(amt.getText().toString()));
                Log.i("TAG","date  "+date);
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