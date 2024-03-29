package com.dcunhajoslin.myexpense;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;



public class SplashActivity extends Activity {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                 SharedPreferences sp=getSharedPreferences("UserInfo",MODE_PRIVATE);
                Log.i("TAG","first run "+sp.getBoolean("firstRun",false));
                if(sp.getBoolean("firstRun",true)){
                    Intent intent=new Intent(SplashActivity.this,startpage.class);
                    startActivity(intent);
                    finish();
                }
                else
                {Intent intent;
                    if(sp.getString("name",null)!=null){
                        intent=new Intent(SplashActivity.this,NavigationActivity.class);
                    }
                    else {
                        intent=new Intent(SplashActivity.this,loginpage.class);
                    }



                    startActivity(intent);
                    finish();
                }

            }
        },3000);



    }
}