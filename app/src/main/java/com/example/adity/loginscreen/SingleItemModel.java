package com.example.adity.loginscreen;

import android.widget.ImageView;

/**
 * Created by adity on 16/02/2017.
 */

public class SingleItemModel {


    public String name;
    public String url;
    public String description;
    public String isbn;
    ImageView i;

    public SingleItemModel() {
    }

    SingleItemModel(String name, String description, String isbn, ImageView i) {
        this.name = name;
        this.description = description;
        this.isbn = isbn;
        this.i = i;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
