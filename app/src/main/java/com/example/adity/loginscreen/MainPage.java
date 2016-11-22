package com.example.adity.loginscreen;

import android.animation.ValueAnimator;
import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

import java.lang.reflect.Field;

public class MainPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView name, email;
    NavigationView nav_view;
    EditText toolbarSearchView;
    LinearLayout searchContainer;
    ImageView searchClearButton;

    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private enum ActionDrawableState{
        BURGER, ARROW
    }
    private static void toggleActionBarIcon(ActionDrawableState state, final ActionBarDrawerToggle toggle, boolean animate) {
        if(animate) {
            float start = state == ActionDrawableState.BURGER ? 0f : 1.0f;
            float end = Math.abs(start - 1);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                ValueAnimator offsetAnimator = ValueAnimator.ofFloat(start, end);
                offsetAnimator.setDuration(300);
                offsetAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                offsetAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float offset = (Float) animation.getAnimatedValue();
                        toggle.onDrawerSlide(null, offset);
                    }
                });
                offsetAnimator.start();
            }else{
                //do the same with nine-old-androids lib :)
            }
        }else{
            if(state == ActionDrawableState.BURGER){
                toggle.onDrawerClosed(null);
            }else{
                toggle.onDrawerOpened(null);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        searchContainer = (LinearLayout) findViewById(R.id.search_container);
        toolbarSearchView = (EditText) findViewById(R.id.search_view);
        searchClearButton = (ImageView) findViewById(R.id.search_clear);
         drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);


      drawer.addDrawerListener(toggle);
        toggle.syncState();


        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
       // navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main_page);

        name = (TextView) headerView.findViewById(R.id.Name);
        email = (TextView) headerView.findViewById(R.id.Email);
        name.setText(getJSON.Name);
        email.setText(getJSON.Email);

       toolbarSearchView=(EditText)findViewById(R.id.search_view) ;
        searchClearButton = (ImageView) findViewById(R.id.search_clear);
        searchClearButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                toolbarSearchView.setText("");
            }

        });
        toolbarSearchView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                toggleActionBarIcon(ActionDrawableState.BURGER, toggle, true);
                toolbar.setNavigationOnClickListener(new View.OnClickListener()
                {
                    public void onClick(View v)
                    {


                        toggleActionBarIcon(ActionDrawableState.ARROW, toggle, true);
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(toolbar.getWindowToken(), 0);



                    }

                });
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
                        return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_share) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("MainPage Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}