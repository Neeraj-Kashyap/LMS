package com.example.adity.loginscreen;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * Created by adity on 24/11/2016.
 */

public class getJSON_recomend extends AsyncTask<String, Void, String> {
    Context c;
    ArrayList<SingleItemModel> mylist;
    int i, j;
    RecyclerView ob1;
    Application ob2;
    ImageView img, triangle;

    public getJSON_recomend(Context context, RecyclerView rv, Application ap, ImageView i) {
        ob1 = rv;
        ob2 = ap;
        mylist = new ArrayList();
        c = context;
        img = i;

    }

    protected String doInBackground(String... arg0) {


        String search = arg0[0];

        String link;
        String data;
        BufferedReader bufferedReader;
        String result = "";
        String resultToDisplay = "";
        try {
            data = "?u_id=" + URLEncoder.encode(search, "UTF-8");
            String urlString = "http://issclibrary.esy.es/recomend.php" + data;
            URL url = new URL(urlString);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            result = bufferedReader.readLine();
            return result;

        } catch (Exception e) {

            return new String("Exception : " + e.getMessage());
        }


    }

    protected void onPostExecute(String result) {

        String jsonStr = result;
        ArrayList<SectionDataModel> etc = new ArrayList<SectionDataModel>();

        JSONArray array, category;
        if (jsonStr != null) {
            try {

                JSONObject jsonObj = new JSONObject(jsonStr);

                if ("Success".equals(jsonObj.getString("status"))) {
                    MainPage.triangle.setVisibility(View.GONE);
                    MainPage.offline.setVisibility(View.GONE);
                    MainPage.retry.setVisibility(View.GONE);
                    category = jsonObj.getJSONArray("Categories");
                    for (j = 0; j < category.length(); j++) {
                        //Log.d("MYTAG",category.getString(j)+"");
                        mylist = new ArrayList<>();
                        array = jsonObj.getJSONArray(category.getString(j));
                        for (i = 0; i < array.length(); i++) {

                            JSONObject jsonObject2 = array.getJSONObject(i);

                            mylist.add(new SingleItemModel(jsonObject2.getString("Book_Title") + "", jsonObject2.getString("Author").toString() + "\n" + jsonObject2.getString("Category") + "\n" + jsonObject2.getString("ISBN") + "\n" + jsonObject2.getString("No_of_pages") + "\n", jsonObject2.getString("ISBN") + "",jsonObject2.getString("b_id"), img));
                            //Log.d("MYTAG",mylist.size()+"");

                        }
                        SectionDataModel dm = new SectionDataModel();
                        if(category.getString(j).equals("Recommended"))
                            dm.setHeaderTitle("Popular");
                            else
                        dm.setHeaderTitle(category.getString(j));
                        dm.setAllItemsInSection(mylist);

                        etc.add(dm);
                    }


                    new horizontalRecycler(c, ob1, etc);


                } else if ("Unsuccessful".equals(jsonObj.getString("status"))) {
                    Toast.makeText(c, "No Results Found", Toast.LENGTH_SHORT).show();
                    MainPage.progresbar.setVisibility(View.GONE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
                MainPage.progresbar.setVisibility(View.GONE);
                MainPage.triangle.setVisibility(View.VISIBLE);
                MainPage.offline.setVisibility(View.VISIBLE);
                MainPage.retry.setVisibility(View.VISIBLE);
                Toast.makeText(c, "No Results Found!", Toast.LENGTH_SHORT).show();
            }
        }

    }


}
