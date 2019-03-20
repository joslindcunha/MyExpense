package com.dcunhajoslin.myexpense;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class loginpage extends AppCompatActivity {

    Button button;
    TextView textView;
    EditText username;
    EditText password;
    DBManager db;
    DBHelper dh;

    private SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button = findViewById(R.id.login);
        textView = findViewById(R.id.signUp);
        username=findViewById(R.id.user);
        password=findViewById(R.id.password);
        db=new DBManager(this);
        db.open();
        dh=new DBHelper(this);

        database =dh.getWritableDatabase();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uname=username.getText().toString();
                String pwd=password.getText().toString();
                if(username.getText().toString().equals(""))
                {
                    username.setError("Please enter username");

                }
                else if(password.getText().toString().equals(""))
                {
                    password.setError("Password cannot be empty");

                }
                else{


                String ans=Login(uname,pwd);

                if(ans!=null) {
                    Toast.makeText(loginpage.this, "Login successful", Toast.LENGTH_SHORT).show();
                    SharedPreferences sp = getSharedPreferences("UserInfo", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("name", ans);
                    editor.putString("email", uname);
                    editor.apply();

                    Intent intent = new Intent(loginpage.this, NavigationActivity.class);

                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(loginpage.this, "Enter valid username and password", Toast.LENGTH_SHORT).show();

                }
                }


            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(loginpage.this,registerpage.class);
                startActivity(intent);
            }
        });
    }
    public String Login(String username,String password)
    {
        Log.i("TAG","trying to validating login ");
        try
        {
            String i = null;
            Cursor c;
            db=new DBManager(this);
            db.open();
            c = database.rawQuery("select name from contacts where email =" + "\""+ username.trim() + "\""+" and password="+ "\""+ password.trim() + "\"",new String[]{});
            c.moveToFirst();


                i = c.getString(0);
                Log.i("TAG","cursor i=  "+i);
                Log.i("TAG","i  "+i);



            c.close();
            return i;
        }
        catch(Exception e)
        {
            //e.printStackTrace();
            Log.i("TAG","error "+e.toString());
            return null;
        }

    }
}
