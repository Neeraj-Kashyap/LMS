package com.example.adity.loginscreen;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by adity on 24/11/2016.
 */

public class View_Holder extends RecyclerView.ViewHolder {

    CardView cv;
    TextView title;
    TextView description;
    ImageView imageView;

    View_Holder(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardView);
        cv.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {

                Intent mainIntent = new Intent(MainPage.c, Book.class);
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mainIntent.putExtra("bookname",title.getText().toString());
                String arr[]=description.getText().toString().split("\n");

                for(int i=0;i<arr.length;i++)
                    mainIntent.putExtra(i+"",arr[i].toString());
                MainPage.c.startActivity(mainIntent);
                            }
        });
        title = (TextView) itemView.findViewById(R.id.title);
        description = (TextView) itemView.findViewById(R.id.description);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
    }
}