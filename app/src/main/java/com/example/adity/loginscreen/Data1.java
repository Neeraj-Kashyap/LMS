package com.example.adity.loginscreen;

import android.widget.ImageView;

/**
 * Created by adity on 24/11/2016.
 */

public class Data1 {
    public String title;
    public String description;
    public String isbn;
    public String status;
    public ImageView image;
    public String b_id;
    Data1(String title,String description, String isbn ,String b_id, String status, ImageView imageId) {
        this.title = title;
        this.description = description;
        this.isbn=isbn;
        this.status=status;
        this.image = imageId;
        this.b_id=b_id;
    }

    String getTitle()
    {
        return title;
    }
}

