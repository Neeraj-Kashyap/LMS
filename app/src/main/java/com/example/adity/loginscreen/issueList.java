package com.example.adity.loginscreen;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TableLayout;

/**
 * Created by adity on 28/03/2017.
 */

public class issueList extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.issuelist);

        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        new getJSON_issueList(getApplicationContext(),stk).execute();
    }
}


