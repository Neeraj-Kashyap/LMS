package com.example.adity.loginscreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.w3c.dom.Text;

public class Book extends AppCompatActivity {

    ImageView i1,i2;

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

        stuff=bund.getString("1");
        TextView tv1=(TextView)findViewById(R.id.cat1);
        tv1.setText(stuff);


        stuff=bund.getString("2");
        TextView tv2=(TextView)findViewById(R.id.cat3);
        tv2.setText(stuff);
        i2=(ImageView)findViewById(R.id.bookcover);
        Picasso.with(getApplicationContext()).load("https://covers.openlibrary.org/b/isbn/"+stuff+"-L.jpg").fit().into(i2);



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
