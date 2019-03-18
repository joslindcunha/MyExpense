package com.dcunhajoslin.myexpense;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class startpage extends AppCompatActivity {
    Button button1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage);
        final SharedPreferences sp=getSharedPreferences("UserInfo",MODE_PRIVATE);
        SharedPreferences.Editor edit=sp.edit();
        edit.putBoolean("firstRun",false);
        edit.apply();
        button1 = findViewById(R.id.next);

        Log.i("TAG","first run "+sp.getBoolean("firstRun",true));
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=sp.getString("name",null);
                if(name!=null)
                {
                    Intent intent=new Intent(startpage.this,expensemanager.class);
                    startActivity(intent);
                }
                else{
                    Intent intent=new Intent(startpage.this,loginpage.class);
                    startActivity(intent);
                }

                finish();

            }
        });
    }
}
