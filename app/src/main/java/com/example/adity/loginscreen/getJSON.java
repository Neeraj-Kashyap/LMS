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
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by adity on 27/09/2016.
 */
public class getJSON extends AsyncTask<String,Void,String> {
    ProgressDialog loading;
Context c;
public static String user_role,Name,Email;
   public getJSON(Context context)
    {
        c=context;

    }
    @Override
    protected String doInBackground(String... arg0) {

        // URL to call
        String userName = arg0[0];
        String passWord=arg0[1];

        String link;
        String data;
        BufferedReader bufferedReader;
        String result="";
        String resultToDisplay = "";
        try {

            data="?username="+URLEncoder.encode(userName,"UTF-8");
            data +="&password="+URLEncoder.encode(passWord,"UTF-8");
            String urlString = "http://issclms.esy.es/connn.php"+data;
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
       if(jsonStr!=null)
       {
           try{
               JSONObject jsonObj = new JSONObject(jsonStr);
               String status=jsonObj.getString("status");
           //    Toast.makeText(c,"Sign-In Successful!",Toast.LENGTH_LONG).show();

               if(status.equals("Success"))
                {
                     Toast.makeText(c,"Sign-In Successful!",Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(MainActivity.fa, MainPage.class);
                    user_role=""+jsonObj.getString("user_role");

                    Name=""+jsonObj.getString("Name");
                    Email=""+jsonObj.getString("Email");

                    Log.d("success",status+" "+Name+" "+Email);


                    /*MainActivity.fa.startActivity(intent);
                    MainActivity.fa.finish();
                    */


                }
               else
                    if(status.equals("Unsuccessful"))
                    {
                        Toast.makeText(c,"Please Check Username Password!",Toast.LENGTH_LONG).show();

                    }
               else
                        Toast.makeText(c, "Couldn't connect to remote database.", Toast.LENGTH_SHORT).show();
           }catch(JSONException e)
           {
               e.printStackTrace();
               Toast.makeText(c, "Error parsing JSON data.", Toast.LENGTH_SHORT).show();
           }
           }
        else
       {
           Toast.makeText(c, "Couldn't get any JSON data.", Toast.LENGTH_SHORT).show();
       }
       }
    }



