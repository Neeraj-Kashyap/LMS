package com.example.adity.loginscreen;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener, TextWatcher {

    public static ProgressDialog progress;
    public static Activity fa;
    Button login, cancel, signup;
    EditText username,password;
    DatabaseHandler db;
    getJSON gd;

    public boolean checkText() {
        boolean i = true;
        if (username.getText().toString().equals(""))
            i = false;
        if (password.getText().toString().equals(""))
            i = false;
        if (username.length() > 30)
            i = false;
        if (password.length() > 30)
            i = false;
        return i;
    }

    public void onClick(View view) {



if(login.getId()==view.getId()) {
    if (checkText()) {
        new getJSON(getApplicationContext()).execute(username.getText().toString(), password.getText().toString());
        progress.show();
    } else
        Toast.makeText(getApplicationContext(), "Please Enter Valid Username or Password", Toast.LENGTH_SHORT).show();
/*
// {

*/
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
        username.addTextChangedListener(this);
        password.addTextChangedListener(this);
        db=new DatabaseHandler(getApplicationContext());
        fa=this;


        progress = new ProgressDialog(this);
        progress.setMessage("Signing In...");
        progress.setCancelable(false);


    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        Drawable tick = getResources().getDrawable(R.drawable.tick1);
        tick.setBounds(new Rect(0, 0, tick.getIntrinsicWidth(), tick.getIntrinsicHeight()));
        if (editable.hashCode() == username.getText().hashCode()) {
            if (username.getText().toString().isEmpty() || username.length() > 30) {
                username.setError("User-Name Length Allowed 30");

            } else {
                username.setError(null);

            }
        }

        if (editable.hashCode() == password.getText().hashCode()) {
            if (password.getText().toString().isEmpty() || password.length() > 30) {
                password.setError("Password Length Allowed 30");

            } else {
                password.setError(null);


            }
        }
    }
}