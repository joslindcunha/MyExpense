package com.dcunhajoslin.myexpense;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registerpage extends AppCompatActivity {

    EditText name,contact,email,pass,confirm_pass,address;
    CheckBox checkBox;
    Button register;
    DBManager mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registeractivity);
        //constructor
        mydb=new DBManager(this);
        mydb.open();
        //typecast
        name=findViewById(R.id.name);
        checkBox=findViewById(R.id.check);
        register=findViewById(R.id.register);
        contact=findViewById(R.id.phone);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        address=findViewById(R.id.address);
        confirm_pass=findViewById(R.id.cpass);







        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equals(" "))
                {
                    name.setError("This field cannot be empty");
                }

                else if( isStringcontains(name.getText().toString()))

                {
                    name.setError("Name shouldn't contain number");
                }

                else if(isspecialchar(name.getText().toString()))
                {
                    name.setError("Name shouldn't contain special character");
                }
                else if(contact.getText().length()<10)
                {
                    contact.setError("Please enter 10 digits");
                }
                else if(!isValidEmail(email.getText().toString())){
                    email.setError("Please enter valid email");
                }

                else if(!pass.getText().toString().equals(confirm_pass.getText().toString()))
                {
                    pass.setError("Passwords do not match");

                }
                else
                {
                    long i= mydb.insertContact(name.getText().toString(),contact.getText().toString(),email.getText().toString(),address.getText().toString(),pass.getText().toString());
                    Log.i("TAG","inertcontact returned "+String.valueOf(i));
                    if(i>0){


                        SharedPreferences sp = getSharedPreferences("UserInfo", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("name", name.getText().toString());
                        editor.putString("email", email.getText().toString());
                        editor.apply();
                        Toast.makeText(registerpage.this, "You have successfully registered", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(registerpage.this,expensemanager.class);
                        startActivity(intent);
                        finish();

                    }
                    else{
                        Toast.makeText(registerpage.this, "Register Error", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(registerpage.this,loginpage.class);
                        startActivity(intent);
                        finish();
                    }



                }

            }



        });


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true)
                {
                    register.setEnabled(true);

                }
                else
                {
                    register.setEnabled(false);
                    Toast.makeText(registerpage.this, "Accept the policy", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isStringcontains(String s)
    {
        Pattern p=Pattern.compile("[0-9]");
        Matcher m=p.matcher(s);
        return m.find();
    }

    public boolean isspecialchar(String s)
    {
        Pattern p=Pattern.compile("[^a-z0-9]",Pattern.CASE_INSENSITIVE);
        Matcher m=p.matcher(s);
        return m.find();
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

}

