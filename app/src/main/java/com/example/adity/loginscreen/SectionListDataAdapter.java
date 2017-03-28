package com.example.adity.loginscreen;

/**
 * Created by adity on 16/02/2017.
 */


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adity.loginscreen.R;
import com.example.adity.loginscreen.SingleItemModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.util.ArrayList;

public class SectionListDataAdapter extends RecyclerView.Adapter<SectionListDataAdapter.SingleItemRowHolder> {

    Bitmap img;
    int width, height;
    private ArrayList<SingleItemModel> itemsList;
    private Context mContext;

    public SectionListDataAdapter(Context context, ArrayList<SingleItemModel> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;

    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_single_card, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        SingleItemModel singleItem = itemsList.get(i);

        holder.tvTitle.setText(singleItem.getName());
        holder.desc.setText(singleItem.getDescription());
        Picasso.with(mContext).load("https://covers.openlibrary.org/b/isbn/" + singleItem.isbn + "-L.jpg?default=false").error(R.drawable.noimage).fit().placeholder(R.drawable.progress_animation).into(holder.itemImage);


       /* Glide.with(mContext)
                .load(feedItem.getImageURL())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.bg)
                .into(feedListRowHolder.thumbView);*/
    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tvTitle, desc;

        protected ImageView itemImage;


        public SingleItemRowHolder(View view) {
            super(view);

            this.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            this.itemImage = (ImageView) view.findViewById(R.id.itemImage);

            this.desc = (TextView) view.findViewById(R.id.desc);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), tvTitle.getText(), Toast.LENGTH_SHORT).show();
                    Intent mainIntent = new Intent(MainPage.c, Book.class);
                    mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mainIntent.putExtra("bookname", tvTitle.getText().toString());
                    String arr[] = desc.getText().toString().split("\n");

                    for (int i = 0; i < arr.length; i++)
                        mainIntent.putExtra(i + "", arr[i].toString());
                    MainPage.c.startActivity(mainIntent);


                }
            });


        }

    }

}