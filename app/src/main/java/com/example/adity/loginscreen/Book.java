package com.example.adity.loginscreen;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Book extends AppCompatActivity {
    public static ProgressDialog progress;
    ImageView i1,i2;
        FloatingActionButton res,iss;
    TextView tv,b,tv1,tv2,tv3,tv0;
    String data,jsonStr;
    BufferedReader bufferedReader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        progress=new ProgressDialog(this);
        progress.setMessage("Processing...");
        progress.setCancelable(false);
        i1=(ImageView)findViewById(R.id.back1);
        Bundle bund=getIntent().getExtras();
        String stuff=bund.getString("bookname");
         tv=(TextView)findViewById(R.id.Book_name);
        tv.setText(stuff);
        stuff=bund.getString("0");

         tv0=(TextView)findViewById(R.id.Book_author);
        tv0.setText(stuff);
        iss=(FloatingActionButton)findViewById(R.id.float1);
        res=(FloatingActionButton)findViewById(R.id.float2);


        iss.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
        {
            if(getJSON.loginflag) {
                new AlertDialog.Builder(Book.this)
                        .setTitle("Do you really want to Issue?")
                        .setMessage(tv.getText().toString())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                progress.show();
                                new getJSON_Issue( getApplicationContext(), b.getText().toString(),getJSON.u_id, new AlertDialog.Builder(Book.this)).execute();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
            }
            else
            {
                new AlertDialog.Builder(Book.this)
                .setTitle("You Need To Login First!")
                .setPositiveButton("OK",null).show();
            }

        }
        });

        res.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if(getJSON.loginflag) {
                    Intent mainIntent = new Intent(Book.this, Reserve.class);
                    mainIntent.putExtra("bookname", tv.getText().toString());
                    mainIntent.putExtra(0 + "", tv0.getText().toString());
                    mainIntent.putExtra(1 + "", tv1.getText().toString());
                    mainIntent.putExtra(2 + "", tv2.getText().toString());
                    mainIntent.putExtra(3 + "", tv3.getText().toString());
                    mainIntent.putExtra("b_id", b.getText().toString());

                    startActivity(mainIntent);
                }
                else
                {
                    new AlertDialog.Builder(Book.this)
                            .setTitle("You Need To Login First!")
                            .setPositiveButton("OK",null).show();
                }
            }
        });
        stuff=bund.getString("1");
         tv1=(TextView)findViewById(R.id.cat1);
        tv1.setText(stuff);


        stuff=bund.getString("2");
        tv2=(TextView)findViewById(R.id.cat3);
        tv2.setText(stuff);
        i2=(ImageView)findViewById(R.id.bookcover);
        Picasso.with(getApplicationContext()).load("https://covers.openlibrary.org/b/isbn/" + stuff + "-L.jpg?default=false").error(R.drawable.noimage).fit().placeholder(R.drawable.progress_animation).into(i2);



        stuff=bund.getString("3");
         tv3=(TextView)findViewById(R.id.cat5);
        tv3.setText(stuff);
        stuff=bund.getString("b_id");
        b=(TextView)findViewById(R.id.b_id1);
        b.setText(stuff);
        /*
        stuff=bund.getString("4");
        TextView tv4=(TextView)findViewById(R.id.cat7);
        tv4.setText(stuff);*/
        i1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                finish();
            }
        });
    }





    public void onBackPressed()
    {
        finish();
    }
}
