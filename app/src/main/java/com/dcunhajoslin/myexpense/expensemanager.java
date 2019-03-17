package com.dcunhajoslin.myexpense;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class expensemanager extends AppCompatActivity {
    Button button,button2,logoutbutton;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sp=getSharedPreferences("UserInfo",MODE_PRIVATE);
        String name=sp.getString("name",null);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.view);
        logoutbutton=findViewById(R.id.logout);
        tv=findViewById(R.id.one);
        tv.setText("Hello "+name);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(expensemanager.this,viewExpense.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(expensemanager.this,addexpense.class);
                startActivity(intent);
            }
        });
        logoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp=getSharedPreferences("UserInfo",MODE_PRIVATE);
                sp.edit().clear().commit();
                finish();
                Toast.makeText(expensemanager.this, "Successfully Logged Out", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(expensemanager.this,loginpage.class);
                startActivity(intent);


            }
        });
    }
}
