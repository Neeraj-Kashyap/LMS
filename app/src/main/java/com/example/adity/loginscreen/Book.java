package com.example.adity.loginscreen;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import org.w3c.dom.Text;

public class Book extends AppCompatActivity {

    ImageView i1,i2;
        FloatingActionButton res,iss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        i1=(ImageView)findViewById(R.id.back1);
        Bundle bund=getIntent().getExtras();
        String stuff=bund.getString("bookname");
        TextView tv=(TextView)findViewById(R.id.Book_name);
        tv.setText(stuff);
        stuff=bund.getString("0");
        TextView tv0=(TextView)findViewById(R.id.Book_author);
        tv0.setText(stuff);
        iss=(FloatingActionButton)findViewById(R.id.float1);
        res=(FloatingActionButton)findViewById(R.id.float2);


        iss.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
        {
            Toast.makeText(getApplicationContext(), "ISSUE BUTTON", Toast.LENGTH_SHORT).show();

        }
        });

        res.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "RESERVE BUTTON", Toast.LENGTH_SHORT).show();

            }
        });
        stuff=bund.getString("1");
        TextView tv1=(TextView)findViewById(R.id.cat1);
        tv1.setText(stuff);


        stuff=bund.getString("2");
        TextView tv2=(TextView)findViewById(R.id.cat3);
        tv2.setText(stuff);
        i2=(ImageView)findViewById(R.id.bookcover);
        Picasso.with(getApplicationContext()).load("https://covers.openlibrary.org/b/isbn/" + stuff + "-L.jpg?default=false").error(R.drawable.noimage).fit().placeholder(R.drawable.progress_animation).into(i2);



        stuff=bund.getString("3");
        TextView tv3=(TextView)findViewById(R.id.cat5);
        tv3.setText(stuff);

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
