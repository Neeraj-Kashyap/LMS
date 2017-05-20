package com.example.adity.loginscreen;

/**
 * Created by adity on 28/03/2017.
 */
import android.app.Application;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

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
import java.lang.reflect.Array;
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
    PieChart pieChart;
    public static Recycler_View_Adapter adapter1;
    Button ob;
     ArrayList mylist;
ArrayList myList1,mylist2;
    ImageView img;
    RecyclerView ob1;
    Application ob2;
    public getJSON_issueList(RecyclerView rv, Application ap,Context context, PieChart pie, ImageView i)
    {
        ob1=rv;
        ob2=ap;
        c=context;
mylist=new ArrayList<Data>();
pieChart=pie;
        ob=new Button(c);
        mylist2=new ArrayList();
img=i;
        myList1=new ArrayList<ArrayList>();
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

            data="?UserId="+URLEncoder.encode(getJSON.u_id.toString(),"UTF-8");
            String urlString = "http://issclibrary.esy.es/Report1.1.php"+data;
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
        JSONArray array,list,count;

        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        if(jsonStr!=null)
        {
            try {

                JSONObject jsonObj = new JSONObject(jsonStr);
                array = jsonObj.getJSONArray("result");
                if ("Success".equals(jsonObj.getString("status"))) {
                    list = jsonObj.getJSONArray("result");
                    count=jsonObj.getJSONArray("Count");

                    for(int z=0;z<count.length();z++)
                    {
                        JSONObject jsonObject=count.getJSONObject(z);
                    yEntrys.add(new PieEntry(jsonObject.getInt("Count"),jsonObject.getString("Category")));
                        xEntrys.add(jsonObject.getString("Category"));
                        Log.d("CountJJ",jsonObject.getInt("Count")+"");
                        for (int j = 0; j < jsonObject.getInt("Count"); j++) {
                            JSONObject jsonObject2 = list.getJSONObject(z);
                            mylist=new ArrayList();
                            if(jsonObject2.getString("status").equals("read")) {
                                mylist.add(new Data(jsonObject2.getString("Book_Title") + "", jsonObject2.getString("Category").toString() + "\n" + jsonObject2.getString("start_date") + "\n" + jsonObject2.getString("ISBN") + "\n" + "Granted", jsonObject2.getString("ISBN") + "", jsonObject2.getString("Book_id"), img));
                                mylist2.add(new Data(jsonObject2.getString("Book_Title") + "", jsonObject2.getString("Category").toString() + "\n" + jsonObject2.getString("start_date") + "\n" + jsonObject2.getString("ISBN") + "\n" + "Granted", jsonObject2.getString("ISBN") + "", jsonObject2.getString("Book_id"), img));

                            }else {
                                mylist.add(new Data(jsonObject2.getString("Book_Title") + "", jsonObject2.getString("Category").toString() + "\n" + jsonObject2.getString("start_date") + "\n" + jsonObject2.getString("ISBN") + "\n" + "Pending", jsonObject2.getString("ISBN") + "", jsonObject2.getString("Book_id"), img));
                                mylist2.add(new Data(jsonObject2.getString("Book_Title") + "", jsonObject2.getString("Category").toString() + "\n" + jsonObject2.getString("start_date") + "\n" + jsonObject2.getString("ISBN") + "\n" + "Pending", jsonObject2.getString("ISBN") + "", jsonObject2.getString("Book_id"), img));
                            }
                            Log.d("Mylistbook",jsonObject2.getString("Book_Title")+j);
                        }

                        myList1.add(mylist);

                    }

                    Log.d("re1",result);
                    Log.d("MYLIST",((Data)((ArrayList)myList1.get(1)).get(0)).getTitle()+"");
                    PieDataSet pieDataSet = new PieDataSet(yEntrys, "Issues Legend");
                    pieDataSet.setSliceSpace(2);
                    pieDataSet.setValueTextSize(12);

                    //add colors to dataset
                    ArrayList<Integer> colors = new ArrayList<>();
                    colors.add(Color.CYAN);
                    colors.add(Color.YELLOW);
                    colors.add(Color.GREEN);
                    colors.add(Color.MAGENTA);
                    colors.add(Color.GRAY);
                    colors.add(Color.BLUE);
                    colors.add(Color.RED);



                    pieDataSet.setColors(colors);

                    //add legend to chart
                    Legend legend = pieChart.getLegend();
                    legend.setForm(Legend.LegendForm.CIRCLE);
                    legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

                    //create pie data object
                    PieData pieData = new PieData(pieDataSet);
                    pieChart.setData(pieData);
                    pieChart.invalidate();
                    pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                        @Override
                        public void onValueSelected(Entry e, Highlight h) {
                            //Log.d("PIECHART"," Entry "+e.toString()+"\n");

                            Log.d("PIECHART"," Highlight "+mylist.size()+"\n");

                            RecyclerView recyclerView = ob1;
                            adapter1 = new Recycler_View_Adapter(((ArrayList)myList1.get(Math.round(h.getX()))), ob2);
                            recyclerView.setAdapter(adapter1);
                            recyclerView.setLayoutManager(new LinearLayoutManager(c));
                            recyclerView.setItemAnimator(new SlideInUpAnimator());
                            RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
                            itemAnimator.setAddDuration(1000);
                            itemAnimator.setRemoveDuration(1000);
                            recyclerView.setItemAnimator(itemAnimator);

                        }



                        @Override
                        public void onNothingSelected() {

                        }
                    });
                        /*
                    for (int j = 0; j < list.length(); j++) {
                        JSONObject jsonObject2 = array.getJSONObject(j);
                    }*/

                    pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                        @Override
                        public void onValueSelected(Entry e, Highlight h) {
                            //Log.d("PIECHART"," Entry "+e.toString()+"\n");

                            Log.d("PIECHART"," Highlight "+mylist.size()+"\n");

                            RecyclerView recyclerView = ob1;
                            adapter1 = new Recycler_View_Adapter(((ArrayList)myList1.get(Math.round(h.getX()))), ob2);
                            recyclerView.setAdapter(adapter1);
                            recyclerView.setLayoutManager(new LinearLayoutManager(c));
                            recyclerView.setItemAnimator(new SlideInUpAnimator());
                            RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
                            itemAnimator.setAddDuration(1000);
                            itemAnimator.setRemoveDuration(1000);
                            recyclerView.setItemAnimator(itemAnimator);

                        }



                        @Override
                        public void onNothingSelected() {

                        }
                    });
                        /*
                    for (int j = 0; j < list.length(); j++) {
                        JSONObject jsonObject2 = array.getJSONObject(j);
                    }*/
                    pieChart.setOnChartGestureListener(new OnChartGestureListener() {
                        @Override
                        public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

                        }

                        @Override
                        public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

                        }

                        @Override
                        public void onChartLongPressed(MotionEvent me) {


                            RecyclerView recyclerView = ob1;
                            adapter1 = new Recycler_View_Adapter(mylist2, ob2);
                            recyclerView.setAdapter(adapter1);
                            recyclerView.setLayoutManager(new LinearLayoutManager(c));
                            recyclerView.setItemAnimator(new SlideInUpAnimator());
                            RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
                            itemAnimator.setAddDuration(1000);
                            itemAnimator.setRemoveDuration(1000);
                            recyclerView.setItemAnimator(itemAnimator);
                        }

                        @Override
                        public void onChartDoubleTapped(MotionEvent me) {



                        }

                        @Override
                        public void onChartSingleTapped(MotionEvent me) {

                        }

                        @Override
                        public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

                        }

                        @Override
                        public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

                        }

                        @Override
                        public void onChartTranslate(MotionEvent me, float dX, float dY) {

                        }
                    });

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



