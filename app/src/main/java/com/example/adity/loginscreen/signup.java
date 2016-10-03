package com.example.adity.loginscreen;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class signup extends Activity implements OnItemSelectedListener,View.OnClickListener,TextWatcher {
String spiner,spiner1;
    Button signup,cancel;
    EditText fname,lname,uname,pass,cpass,email;
    Spinner spinner;
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_signup);
         spinner = (Spinner) findViewById(R.id.spinner);

         fname=(EditText)findViewById(R.id.fname);
         lname=(EditText)findViewById(R.id.lname);
         uname=(EditText)findViewById(R.id.uname);
         pass=(EditText)findViewById(R.id.pass);
         cpass=(EditText)findViewById(R.id.cpass);
         email=(EditText)findViewById(R.id.email);
         signup=(Button)findViewById(R.id.signup_signup);
         cancel=(Button)findViewById(R.id.signup_cancel);
         spiner=spinner.getSelectedItem().toString();
        signup.setOnClickListener(this);
        db=new DatabaseHandler(getApplicationContext());
        cancel.setOnClickListener(this);
        fname.addTextChangedListener(this);
        lname.addTextChangedListener(this);
        uname.addTextChangedListener(this);
        pass.addTextChangedListener(this);
        cpass.addTextChangedListener(this);
        email.addTextChangedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        boolean valid;
        if(signup.getId()==view.getId()) {
            if (fname.getText().toString().isEmpty() || fname.length() < 3 || !fname.getText().toString().matches("^[A-Za-z]+$")) {
                valid = false;
            } else {
                valid=true;
            }


            if (lname.getText().toString().isEmpty() || lname.length() < 3 || !lname.getText().toString().matches("^[A-Za-z]+$")) {
                valid = false;
            } else {
                valid=true;
            }

            if(uname.getText().toString().isEmpty() || uname.getText().toString().length()<8 )
            {
                valid=false;
            }
            else {
               valid=true;
            }
            if(pass.getText().toString().isEmpty() || pass.getText().toString().length()<8 || pass.getText().toString().length()>40 )
                valid=false;
            else
                valid=true;

            if(cpass.getText().toString().equals(pass.getText().toString()))
                valid=true;
            else
                valid=false;

            if(!email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+") || email.getText().toString().isEmpty())
                valid=false;
            else
                valid=true;

            if (db.usernameavailability(uname.getText().toString()))
                valid = true;
            else
                valid=false;

            if(valid==true)
            {

                db.signup("'"+ spinner.getSelectedItem().toString() +"','"+fname.getText().toString()+" "+lname.getText().toString()+"','"+uname.getText().toString()+"','"+pass.getText().toString()+"','"+email.getText().toString()+"'");
                Toast.makeText(getApplicationContext(),"SignUp Successfull!",Toast.LENGTH_LONG).show();

                Intent intent1 = new Intent(signup.this, MainActivity.class);
                startActivity(intent1);
                finish();
            }
            else
                Toast.makeText(getApplicationContext(),"SignUp Unsuccessfull!",Toast.LENGTH_LONG).show();

        }
        else
        if(cancel.getId()==view.getId())
        {
            Intent intent1 = new Intent(signup.this, MainActivity.class);
            startActivity(intent1);
            finish();
        }
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
        if(editable.hashCode()==fname.getText().hashCode())
        {
            if(fname.getText().toString().isEmpty() || fname.length() < 3 || !fname.getText().toString().matches("^[A-Za-z]+$"))
                fname.setError("Please Enter Valid Name");
            else
                fname.setError(null);
        }

        if(editable.hashCode()==lname.getText().hashCode())
        {
            if(lname.getText().toString().isEmpty() || lname.length() < 3 || !lname.getText().toString().matches("^[A-Za-z]+$"))
            lname.setError("Please Enter Valid Last Name!");
            else
                lname.setError(null);
        }

        if(editable.hashCode()==uname.getText().hashCode()) {
            if (uname.getText().toString().isEmpty() || uname.getText().toString().length() < 8)
                uname.setError("Invalid Username");
            else {
                if (db.usernameavailability(uname.getText().toString()))
                    uname.setError(null, tick);
                else
                    uname.setError("Username Already Taken!");
            }
        }

        if(editable.hashCode()==pass.getText().hashCode())
        {
            if(pass.getText().toString().isEmpty() || pass.getText().toString().length()<8 || pass.getText().toString().length()>40)
                pass.setError("Password To Short");
            else
                pass.setError(null);
        }

        if(editable.hashCode()==cpass.getText().hashCode()) {
            if (pass.getText().toString().equals(cpass.getText().toString())) {

                cpass.setError("Match",tick);

            }else
                cpass.setError("Password Does Not Match");

        }

        if(editable.hashCode()==email.getText().hashCode())
        {
            if(!email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+") || email.getText().toString().isEmpty())
                email.setError("Invalid Email ID!");
            else
                email.setError(null);
        }
    }
}
