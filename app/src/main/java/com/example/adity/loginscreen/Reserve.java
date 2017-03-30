package com.example.adity.loginscreen;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;

import static android.R.attr.maxDate;
import static android.R.attr.minDate;

/**
 * Created by adity on 30/03/2017.
 */

public class Reserve extends Activity {
    public static ProgressDialog progress;
    ImageView i1,i2;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    Button submit,cancel;
    EditText start_date,end_date;
    String b_id;
    TextView tv;
    ImageView img;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.reserve);
        progress=new ProgressDialog(this);
        progress.setMessage("Processing...");
        progress.setCancelable(false);
        dateView = (TextView) findViewById(R.id.start_date);

        year = getJSON_date.year;

        month = getJSON_date.month;
        day = getJSON_date.day;
        showDate1(year, month+1, day);
    submit=(Button)findViewById(R.id.submitreserve);
        cancel=(Button)findViewById(R.id.cancelreserve);
        cancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                finish();
            }
        });
        img=(ImageView)findViewById(R.id.back1reserve);
        img.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                finish();
            }
        });
        start_date=(EditText)findViewById(R.id.start_date);
        end_date=(EditText)findViewById(R.id.end_date);
        start_date.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
              setDate(v,999);
            }
        });
        end_date.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                setDate(v,888);
            }
        });
        Bundle bund=getIntent().getExtras();
        String stuff=bund.getString("bookname");
        tv=(TextView)findViewById(R.id.Book_namereserve);
        tv.setText(stuff);
        stuff=bund.getString("2");
        b_id=bund.getString("b_id");
        i2=(ImageView)findViewById(R.id.bookcoverreserve);
        Picasso.with(getApplicationContext()).load("https://covers.openlibrary.org/b/isbn/" + stuff + "-L.jpg?default=false").error(R.drawable.noimage).fit().placeholder(R.drawable.progress_animation).into(i2);
    submit.setOnClickListener(new View.OnClickListener(){
        public void onClick(View v) {
            if (!start_date.getText().toString().equals("") && !end_date.toString().equals(""))
            {
                progress.show();
                new getJSON_reserve(getApplicationContext(), b_id.toString(), getJSON.u_id, new AlertDialog.Builder(Reserve.this), start_date.getText().toString(), end_date.getText().toString()).execute();
            }
        }
    });
    }


    @SuppressWarnings("deprecation")
    public void setDate(View view,int id) {
        if(id==999)
        showDialog(id);
        else
            if(id==888)
                showDialog(id);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        DatePickerDialog pickerDialog=null;
        if (id == 999)
         pickerDialog = new DatePickerDialog(this,myDateListener, year, month, day);
       else
           if(id==888)
            pickerDialog = new DatePickerDialog(this,myDateListener1, year, month, day);

        Calendar c1 = Calendar.getInstance();

        c1.set(year,month,day,c1.get(Calendar.HOUR_OF_DAY)-1,c1.get(Calendar.MINUTE)-1,c1.get(Calendar.SECOND)-30);
        //c1.add(Calendar.DAY_OF_MONTH,-1);
        long minDate = c1.getTime().getTime();
        pickerDialog.getDatePicker().setMinDate(minDate);

        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);

        c.add(Calendar.MONTH, 18 ); // Subtract 6 months
        long maxDate = c.getTime().getTime(); // Twice!
        pickerDialog.getDatePicker().setMaxDate(maxDate);


            return pickerDialog;


    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day

                    showDate1(arg1, arg2+1, arg3);
                }
            };
    private DatePickerDialog.OnDateSetListener myDateListener1 = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day

                    showDate2(arg1, arg2+1, arg3);
                }
            };

    private void showDate1(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(year).append("-")
                .append(month).append("-").append(day));
    }

    private void showDate2(int year, int month, int day) {
        end_date.setText(new StringBuilder().append(year).append("-")
                .append(month).append("-").append(day));
    }
}
