package com.example.adity.loginscreen;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * Created by adity on 18/02/2017.
 */

public class horizontalRecycler {

    public horizontalRecycler(Context context, RecyclerView rv, ArrayList<SectionDataModel> allSampleData) {


        RecyclerView my_recycler_view = rv;

        my_recycler_view.setHasFixedSize(true);

        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(context, allSampleData);

        my_recycler_view.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

        my_recycler_view.setAdapter(adapter);
    }
/*
    public void createDummyData(ArrayList t) {
        for (int i = 1; i <= 5; i++) {

            SectionDataModel dm = new SectionDataModel();

            dm.setHeaderTitle("Section " + i+"");

            ArrayList<SingleItemModel> singleItem = new ArrayList<SingleItemModel>();
            for (int j = 0; j <= 5; j++) {
                //singleItem.add(new SingleItemModel("Item " + j, "URL " + j));
            }

            dm.setAllItemsInSection(singleItem);

            allSampleData.add(dm);

        }
    }*/
}
