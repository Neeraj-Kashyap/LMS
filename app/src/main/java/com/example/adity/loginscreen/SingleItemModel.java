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
    public String b_id;
    ImageView i;

    public SingleItemModel() {
    }

    SingleItemModel(String name, String description, String isbn,String b_id, ImageView i) {
        this.name = name;
        this.description = description;
        this.isbn = isbn;
        this.i = i;
        this.b_id=b_id;
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

    public String getB_id() {
        return b_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
