package com.dcunhajoslin.myexpense;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class addexpense extends Fragment {

    EditText name,type,amt,edittext;
    Button button1;
    DBHelper dbHelper;
    DBManager mydb;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_addexpense, container, false);
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_addexpense);
        super.onActivityCreated(savedInstanceState);
        View v = getView();
        mydb = new DBManager(getContext());
        mydb.open();

        dbHelper = new DBHelper(getContext());


        button1 = v.findViewById(R.id.enter);
        // date = v.findViewById(R.id.cal);
        name = v.findViewById(R.id.name);
        // type = v.findViewById(R.id.type);
        amt = v.findViewById(R.id.amt);
        edittext = v.findViewById(R.id.Birthday);


        //    IMP

//        check for date picker
//        https://github.com/enrimilan/Android-Datepicker-Example

        final Calendar myCalendar;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            myCalendar = Calendar.getInstance();
        } else {
            myCalendar = null;
        }

        final EditText edittext = (EditText) v.findViewById(R.id.Birthday);
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
                    new DatePickerDialog(getActivity(), date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = null;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    String day = "" + myCalendar.get(Calendar.YEAR);
                    String month = "" + (myCalendar.get(Calendar.MONTH) + 1);


                    String year = "" + myCalendar.get(Calendar.DAY_OF_MONTH);
                    date = day + "-" + month + "-" + year;
                }
                // display the values by using a toast
//                Toast.makeText(getApplicationContext(), day + "\n" + month + "\n" + year, Toast.LENGTH_LONG).show();
                long i = mydb.insert_expense(date, name.getText().toString(), Long.parseLong(amt.getText().toString()));
                Log.i("TAG", "date  " + date);
                if (i > 0) {
                    Toast.makeText(getActivity(), "Data Entered", Toast.LENGTH_SHORT).show();


                    startActivity(new Intent(getActivity(), NavigationActivity.class));
                   // finish();


                } else {
                    Toast.makeText(getActivity(), "Some error occurred", Toast.LENGTH_SHORT).show();


                    startActivity(new Intent(getActivity(), addexpense.class));
                   // finish();

                }

            }
        });

    }

}