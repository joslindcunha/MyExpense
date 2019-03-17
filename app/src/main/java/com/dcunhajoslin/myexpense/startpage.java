package com.dcunhajoslin.myexpense;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class startpage extends AppCompatActivity {
    Button button1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startpageactivity);
        button1 = findViewById(R.id.next);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp=getSharedPreferences("UserInfo",MODE_PRIVATE);
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
