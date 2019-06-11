package com.dcunhajoslin.myexpense;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registerpage extends AppCompatActivity {

    EditText name, contact, email, pass, confirm_pass, address;
    TextView checkBox;
    Button register;
    DBManager mydb;

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //constructor
        mydb = new DBManager(this);
        mydb.open();
        //typecast
        name = findViewById(R.id.name);
        checkBox = findViewById(R.id.check);
        register = findViewById(R.id.register);
        contact = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        address = findViewById(R.id.address);
        confirm_pass = findViewById(R.id.cpass);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name.getText().toString().equals(" ") || name.getText().toString().length() == 0) {
                    name.setError("This field cannot be empty");
                } else if (isStringcontains(name.getText().toString()))

                {
                    name.setError("Name shouldn't contain number");
                } else if (isspecialchar(name.getText().toString())) {
                    name.setError("Name shouldn't contain special character");
                } else if (contact.getText().length() < 10) {
                    contact.setError("Please enter 10 digits");
                } else if (!isValidEmail(email.getText().toString())) {
                    email.setError("Please enter valid email");
                }
                else if(address.getText().toString().length()==0){
                    address.setError("Address cannot be empty");
                }
                else if(pass.getText().toString().length()==0){
                    pass.setError("Passwords cannot be empty");
                }
                else if (!pass.getText().toString().equals(confirm_pass.getText().toString())) {
                    confirm_pass.setError("Passwords do not match");

                }

//                else if(!checkBox.isChecked()){
//                    Toast.makeText(registerpage.this,"You need to accept the policies to register",Toast.LENGTH_SHORT).show();
//                }
                else {
                    long i = mydb.insertContact(name.getText().toString(), contact.getText().toString(), email.getText().toString(), address.getText().toString(), pass.getText().toString());
                    Log.i("TAG", "inertcontact returned " + String.valueOf(i));
                    if (i > 0) {


                        SharedPreferences sp = getSharedPreferences("UserInfo", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("name", name.getText().toString());
                        editor.putString("email", email.getText().toString());
                        editor.apply();
                        Toast.makeText(registerpage.this, "You have successfully registered", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(registerpage.this, NavigationActivity.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(registerpage.this, "Registration Error, Try again", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(registerpage.this, loginpage.class);
                        startActivity(intent);
                        finish();
                    }


                }

            }


        });


//        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked == true) {
//                    register.setEnabled(true);
//
//                } else {
//                    register.setEnabled(false);
//                    Toast.makeText(registerpage.this, "Accept the policy", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    public boolean isStringcontains(String s) {
        Pattern p = Pattern.compile("[0-9]");
        Matcher m = p.matcher(s);
        return m.find();
    }

    public boolean isspecialchar(String s) {
        Pattern p = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(s);
        return m.find();
    }

}

