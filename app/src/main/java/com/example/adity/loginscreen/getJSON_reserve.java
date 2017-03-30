package com.example.adity.loginscreen;


import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
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

public class getJSON_reserve extends AsyncTask<String,Void,String> {

    Context c;
    AlertDialog.Builder alertDialog;
    String b_id,u_id,start_date,end_date;
    public getJSON_reserve(Context context, String b_id, String u_id,AlertDialog.Builder etc,String date1,String date2)
    {
        this.b_id=b_id;
        this.u_id=u_id;
        alertDialog=etc;
        start_date=date1;
        end_date=date2;
        c=context;


    }
    protected String doInBackground(String... arg0) {




        String link;
        String data;
        BufferedReader bufferedReader;
        String result = "";
        String resultToDisplay = "";
        try {

            data = "?u_id=" + URLEncoder.encode(u_id, "UTF-8");
            data+="&b_id="+URLEncoder.encode(b_id,"UTF-8");
            data+="&date1="+URLEncoder.encode(start_date,"UTF-8");
            data+="&date2="+URLEncoder.encode(end_date,"UTF-8");
            String urlString = "http://issclibrary.esy.es/reserve_android.php" + data;

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
        JSONArray array;

        try {
            if (jsonStr != null)
            {
                JSONObject jsonObj = new JSONObject(jsonStr);
                if ("Successful".equals(jsonObj.getString("status"))) {
                    Reserve.progress.dismiss();
                    alertDialog
                            .setTitle("Successfully Reserve Request Sent")
                            .setPositiveButton("OK", null).show();

                } else if ("Unsuccessful".equals(jsonObj.getString("status"))) {
                    Reserve.progress.dismiss();
                        alertDialog
                                .setTitle("Could not Reserve Book!")
                                .setPositiveButton("OK", null).show();
//if user have given 2 requests to issue Books


                }
            }

        }catch(Exception e)
        {
            e.printStackTrace();
        }



    }

}
