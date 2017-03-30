package com.example.adity.loginscreen;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by adity on 27/09/2016.
 */
public class getJSON_date extends AsyncTask<String,Void,String> {
    public static int year,month,day;
 public getJSON_date()
 {
     Calendar calendar = Calendar.getInstance();
     year = calendar.get(Calendar.YEAR);

     month = calendar.get(Calendar.MONTH);
     day = calendar.get(Calendar.DAY_OF_MONTH);
 }

    @Override
    protected String doInBackground(String... arg0) {

        // URL to call


        String link;
        String data;
        BufferedReader bufferedReader;
        String result="";
        String resultToDisplay = "";
        try {


            String urlString = "http://issclibrary.esy.es/time.php";
            URL url = new URL(urlString);
            HttpURLConnection con=(HttpURLConnection) url.openConnection();

            bufferedReader =new BufferedReader(new InputStreamReader(con.getInputStream()));
            result =bufferedReader.readLine();
            return result;
        } catch (Exception e) {

            return new String("Exception : " +e.getMessage());
        }


    }

    protected void onPostExecute(String result) {
        String jsonStr=result;
        // Toast.makeText(c, result, Toast.LENGTH_SHORT).show();
        if(jsonStr!=null)
        {
            try{
                JSONObject jsonObj = new JSONObject(jsonStr);
                String status=jsonObj.getString("status");
                //    Toast.makeText(c,"Sign-In Successful!",Toast.LENGTH_LONG).show();

                if(status.equals("Success"))
                {
                    String date=jsonObj.getString("date");
                    String arr[]=date.split("-");
                    year=Integer.parseInt(arr[0]);
                    day=Integer.parseInt(arr[1]);
                    month=Integer.parseInt(arr[2]);

                }


            }catch(JSONException e)
            {
                e.printStackTrace();

            }
        }

    }
}



