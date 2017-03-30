package com.example.adity.loginscreen;

/**
 * Created by adity on 28/03/2017.
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
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

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * Created by adity on 27/09/2016.
 */
public class getJSON_issueList extends AsyncTask<String,Void,String> {

    Context c;
    TableLayout stk;


    Button ob;
    public getJSON_issueList(Context context,TableLayout t)
    {
        c=context;
stk=t;

        ob=new Button(c);

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

            data="?u_id="+URLEncoder.encode(getJSON.u_id.toString(),"UTF-8");
            String urlString = "http://issclibrary.esy.es/issueList.php"+data;
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

        String jsonStr = result;
        JSONArray array,list;
         Toast.makeText(c, result, Toast.LENGTH_SHORT).show();
        if(jsonStr!=null)
        {
            try {

                JSONObject jsonObj = new JSONObject(jsonStr);
                array = jsonObj.getJSONArray("result");
                if ("Success".equals(jsonObj.getString("status"))) {
                    GradientDrawable gd = new GradientDrawable(
                            GradientDrawable.Orientation.TOP_BOTTOM,
                            new int[] {Color.parseColor("#C0C0C0"), Color.parseColor("#505050")});
                    gd.setGradientCenter(0.f, 1.f);
                    gd.setLevel(2);



                    TableRow tbrow0=new TableRow(c);
                    tbrow0.setLayoutParams(new TableLayout.LayoutParams(
                            TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.MATCH_PARENT,1.0f));
                    TextView tv   =new TextView(c);
                    tv.setText("Sr.no");
                    tv.setTextColor(Color.BLACK);
                    tv.setGravity(Gravity.CENTER);
                    tbrow0.addView(tv);
                    TextView tv1   =new TextView(c);
                    tv1.setText("Book Details");
                    tv1.setGravity(Gravity.CENTER);
                    tv1.setTextColor(Color.BLACK);
                    tbrow0.addView(tv1);
                    TextView tv2   =new TextView(c);
                    tv2.setText("Date");
                    tv2.setTextColor(Color.BLACK);
                    tv2.setGravity(Gravity.CENTER);
                    tbrow0.addView(tv2);

                    stk.addView(tbrow0);
                    list = jsonObj.getJSONArray("result");
                    for (int j = 0; j < list.length(); j++) {
                        JSONObject jsonObject2 = array.getJSONObject(j);
                        TableRow tbrow = new TableRow(c);
                        tbrow.setLayoutParams(new TableLayout.LayoutParams(
                                TableLayout.LayoutParams.MATCH_PARENT,
                                TableLayout.LayoutParams.MATCH_PARENT));
                        TextView t1v = new TextView(c);
                        t1v.setText("" + j);
                        t1v.setTextColor(Color.WHITE);
                        t1v.setGravity(Gravity.CENTER);
                        tbrow.addView(t1v);
                        TextView t2v = new TextView(c);
                        t2v.setText(jsonObject2.getString("Book_Title")+"\n"+jsonObject2.getString("Category"));
                        t2v.setTextColor(Color.WHITE);
                        t2v.setGravity(Gravity.CENTER);
                        tbrow.addView(t2v);
                        TextView t3v = new TextView(c);
                        t3v.setText(jsonObject2.getString("start_date"));
                        t3v.setTextColor(Color.WHITE);
                        t3v.setGravity(Gravity.CENTER);
                        tbrow.addView(t3v);
                        TableRow tbrow2 = new TableRow(c);
                        Button bt=new Button(c);
                        bt.setText("Cancel");
                        bt.setBackgroundColor(Color.RED);
                        bt.setGravity(Gravity.CENTER);
                        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,android.widget.TableRow.LayoutParams.MATCH_PARENT);
                        lp.span=3;
                        bt.setLayoutParams(lp);
                        tbrow2.addView(bt);
                        tbrow2.setLayoutParams(new TableLayout.LayoutParams(
                                TableLayout.LayoutParams.MATCH_PARENT,
                                TableLayout.LayoutParams.MATCH_PARENT));
                        stk.addView(tbrow);
                        stk.addView(tbrow2);
                    }

                }
                else
                if("Unsuccessful".equals(jsonObj.getString("status"))) {
                    Toast.makeText(c, "No Results Found", Toast.LENGTH_SHORT).show();
                    MainPage.progress.dismiss();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Toast.makeText(c, "Couldn't get any JSON data.", Toast.LENGTH_SHORT).show();
        }
    }
}



