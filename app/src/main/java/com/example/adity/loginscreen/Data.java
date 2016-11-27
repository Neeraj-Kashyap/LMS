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

    Data(String title, String description, String isbn , ImageView imageId) {
        this.title = title;
        this.description = description;
        this.isbn=isbn;
        this.image = imageId;
    }
}

