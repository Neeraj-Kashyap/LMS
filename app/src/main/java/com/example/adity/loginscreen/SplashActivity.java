package com.example.adity.loginscreen;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {
    public static SharedPreferences pref;

    private final int SPLASH_DISPLAY_LENGTH = 2000;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      if(!new SessionManager(getApplicationContext()).isLoggedIn())
          Toast.makeText(getApplicationContext(), "nope boy", Toast.LENGTH_SHORT).show();
        else
          new getJSON(getApplicationContext(),true).execute(new SessionManager(getApplicationContext()).getKeyUsername(), new SessionManager(getApplicationContext()).getKeyPassword());
          //Toast.makeText(getApplicationContext(),new SessionManager(getApplicationContext()).getKeyUsername()+" "+new SessionManager(getApplicationContext()).getKeyPassword(), Toast.LENGTH_SHORT).show();


       setContentView(R.layout.activity_splash);
        android.app.ActionBar bar=getActionBar();
        

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashActivity.this,MainPage.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }


    }

