package com.example.adity.loginscreen;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

public class getJSON_search extends AsyncTask<String,Void,String> {
    public static Recycler_View_Adapter adapter;
    Context c;
ArrayList mylist;
    int i;
    RecyclerView ob1;
    Application ob2;
    ImageView img;
    public getJSON_search(Context context, RecyclerView rv, Application ap, ImageView i)
    {
        ob1=rv;
        ob2=ap;
mylist=new ArrayList();
        c=context;
        img=i;

    }
    protected String doInBackground(String... arg0) {


           String search = arg0[0];

           String link;
           String data;
           BufferedReader bufferedReader;
           String result = "";
           String resultToDisplay = "";
           try {
               data = "?search=" + URLEncoder.encode(search, "UTF-8");
               String urlString = "http://issclibrary.esy.es/search.php" + data;
               URL url = new URL(urlString);

               HttpURLConnection con = (HttpURLConnection) url.openConnection();
               bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
               result = bufferedReader.readLine();
               return result;

           } catch (Exception e) {

               return new String("Exception : " + e.getMessage());
           }




    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    protected void onPostExecute(String result)
    {

        String jsonStr = result;
        JSONArray array;
        if (jsonStr != null) {
            try {

                JSONObject jsonObj = new JSONObject(jsonStr);
                array = jsonObj.getJSONArray("result");
                if ("Success".equals(jsonObj.getString("status"))) {
                    MainPage.triangle.setVisibility(View.GONE);
                    MainPage.offline.setVisibility(View.GONE);
                    MainPage.retry.setVisibility(View.GONE);
                    for (i = 0; i < array.length(); i++)
                    {
                        JSONObject jsonObject2 = array.getJSONObject(i);

                        mylist.add(new Data(jsonObject2.getString("Book_Title")+"", jsonObject2.getString("Author").toString() + "\n" + jsonObject2.getString("Category") + "\n" + jsonObject2.getString("ISBN") + "\n" + jsonObject2.getString("No_of_pages") + "\n",jsonObject2.getString("ISBN")+"",img));


                    }


                    Log.d("SIZE OF DATA", Integer.toString(mylist.size()));
                    RecyclerView recyclerView = ob1;
                    adapter = new Recycler_View_Adapter(mylist, ob2);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(c));
                    recyclerView.setItemAnimator(new SlideInUpAnimator());
                    RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
                    itemAnimator.setAddDuration(1000);
                    itemAnimator.setRemoveDuration(1000);
                    recyclerView.setItemAnimator(itemAnimator);

                }
                else
                    if("Unsuccessful".equals(jsonObj.getString("status"))) {
                        Toast.makeText(c, "No Results Found", Toast.LENGTH_SHORT).show();
                        MainPage.progress.dismiss();
                    }
                    } catch (JSONException e) {
                e.printStackTrace();
                if (!isNetworkAvailable()) {

                    MainPage.triangle.setVisibility(View.VISIBLE);
                    MainPage.offline.setVisibility(View.VISIBLE);
                    MainPage.retry.setVisibility(View.VISIBLE);
                }
                MainPage.progress.dismiss();
                Toast.makeText(c, "No Results Found!", Toast.LENGTH_SHORT).show();
            }
        }

    }




}
