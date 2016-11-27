package com.example.adity.loginscreen;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener{

    Button login,cancel,signup;
    public static ProgressDialog progress;
    EditText username,password;
    DatabaseHandler db;
    getJSON gd;
    public static Activity fa;
    public void onClick(View view) {



if(login.getId()==view.getId()) {

new getJSON(getApplicationContext()).execute(username.getText().toString(), password.getText().toString());
progress.show();

//finish();

    //username.setText(password.getText().toString());
   /* if (db.checklogin(username.getText().toString(), password.getText().toString()))
        Toast.makeText(getApplicationContext(),"Login Successfull!",Toast.LENGTH_LONG).show();
    else
        Toast.makeText(getApplicationContext(),"Error While Logging In!",Toast.LENGTH_LONG).show();
*/


}
        else
    if(cancel.getId()==view.getId())
    {
        Intent intent = new Intent(MainActivity.this, MainPage.class);
        startActivity(intent);
        finish();
    }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=(Button) findViewById(R.id.login);
        cancel=(Button) findViewById(R.id.cancel);
        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        login.setOnClickListener(this);
        cancel.setOnClickListener(this);
        db=new DatabaseHandler(getApplicationContext());
        fa=this;
        progress = new ProgressDialog(this);
        progress.setMessage("Signing In...");
        progress.setCancelable(false);


    }


}