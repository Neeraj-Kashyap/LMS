package com.example.adity.loginscreen;

import android.widget.ImageView;

/**
 * Created by adity on 24/11/2016.
 */

public class Data {
    public String title;
    public String description;
    public String isbn;
    public ImageView image;
public String b_id;
    Data(String title, String description, String isbn ,String b_id, ImageView imageId) {
        this.title = title;
        this.description = description;
        this.isbn=isbn;
        this.image = imageId;
        this.b_id=b_id;
    }
}

