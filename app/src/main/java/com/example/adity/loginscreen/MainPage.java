package com.example.adity.loginscreen;

import android.Manifest;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
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
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.content.ContextCompat;
import android.content.pm.PackageManager;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.example.adity.loginscreen.R;
import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Collection;

import static android.Manifest.permission.CAMERA;

public class MainPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
    public static final int RequestPermissionCode = 1;
    public static ImageView triangle;
    public static TextView offline;
    public static Button retry;
    public static MenuItem mymenu;
    public static ProgressDialog progress;
    public static ProgressBar progresbar;
    public static Context c;
    private static boolean toolbarHomeButtonAnimating;
    public int h=0,flag=0,backflag=0,menuflag=0;
    TextView name, email,userrole;
    NavigationView nav_view;
    EditText toolbarSearchView;
    LinearLayout searchContainer;
    ImageView camera,searchClearButton;
    EditText max;
    Button login;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    float alpha = 1.0f;
    float newAlpha = 1.0f;
    int overallXScroll = 0;
    ArrayList<SectionDataModel> allSampleData;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private CardView cv;

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
                offsetAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        toolbarHomeButtonAnimating = false;

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                toolbarHomeButtonAnimating = true;
                offsetAnimator.start();
               /* if(h==0)
                    h=1;
                else
                    h=0;*/
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

public void MainPage()
{

}

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        c=getApplicationContext();
        progress = new ProgressDialog(this);
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        max=(EditText)findViewById(R.id.search_view);
        max.setHintTextColor(getResources().getColor(R.color.black));
        offline = (TextView) findViewById(R.id.offline);
        triangle = (ImageView) findViewById(R.id.triangle);
        progresbar = (ProgressBar) findViewById(R.id.progressbar);
        retry = (Button) findViewById(R.id.retry);
        retry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainPage.triangle.setVisibility(View.GONE);
                MainPage.offline.setVisibility(View.GONE);
                MainPage.retry.setVisibility(View.GONE);
                progresbar.setVisibility(View.VISIBLE);
                new getJSON_recomend(getApplicationContext(), (RecyclerView) findViewById(R.id.recyclerview), getApplication(), (ImageView) findViewById(R.id.itemImage)).execute("1");

            }
        });
        searchContainer = (LinearLayout) findViewById(R.id.search_container);
        toolbarSearchView = (EditText) findViewById(R.id.search_view);
        searchClearButton = (ImageView) findViewById(R.id.search_clear);
        camera=(ImageView)findViewById(R.id.camerabut);
        camera.setOnClickListener(this);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        searchClearButton.setVisibility(View.GONE);
        max.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    Log.i("KEY","Enter pressed");
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                    new getJSON_search( getApplicationContext(), (RecyclerView) findViewById(R.id.recyclerview), getApplication(), (ImageView) findViewById(R.id.imageView)).execute(max.getText().toString());
                progress.show();
                }
                return false;
            }
        });


       toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);


      drawer.addDrawerListener(toggle);
       toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
       navigationView.setNavigationItemSelectedListener(this);
       View headerView = navigationView.getHeaderView(0);
        login=(Button)headerView.findViewById(R.id.login1);
        navigationView.getMenu().setGroupVisible(R.id.group1,false);
        login.setVisibility(View.VISIBLE);
        login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v )
            {
                Intent mainIntent = new Intent(MainPage.this,MainActivity.class);
                MainPage.this.startActivity(mainIntent);
                MainPage.this.finish();
            }
        });
        if(getJSON.Name!=null && getJSON.user_role!=null && getJSON.Email!=null ) {
login.setVisibility(View.GONE);
            flag=1;
            name = (TextView) headerView.findViewById(R.id.Name);
    email = (TextView) headerView.findViewById(R.id.Email);
    userrole=(TextView)headerView.findViewById(R.id.userrole);
    name.setText(getJSON.Name);
    email.setText(getJSON.Email);
    userrole.setText(getJSON.user_role);

            navigationView.getMenu().setGroupVisible(R.id.group1,true);
            menuflag=1;
                invalidateOptionsMenu();
        }
        RecyclerView rv=(RecyclerView)findViewById(R.id.recyclerview);

        cv=(CardView) findViewById(R.id.cardView) ;
toolbar.getMenu().setGroupVisible(R.id.group2,false);
       toolbarSearchView=(EditText)findViewById(R.id.search_view) ;
        searchClearButton = (ImageView) findViewById(R.id.search_clear);
        searchClearButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                //imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                imm.showSoftInput(toolbarSearchView,InputMethodManager.SHOW_IMPLICIT);
                toolbarSearchView.setText("");
            }

        });
        toolbarSearchView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                searchClearButton.setVisibility(View.VISIBLE);
                toggleActionBarIcon(ActionDrawableState.BURGER, toggle, true);
                h=1;
                camera.setVisibility(View.VISIBLE);
                menuflag=1;
                invalidateOptionsMenu();
                toolbar.setNavigationOnClickListener(new View.OnClickListener()
                {
                    public void onClick(View v) {
                        Log.d("htag",h+"");
                        if(h==1)
                        {
                        toggleActionBarIcon(ActionDrawableState.ARROW, toggle, true);
                        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                            h=0;
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(toolbar.getWindowToken(), 0);
                            if(flag==0) {
                                menuflag = 0;
                                invalidateOptionsMenu();

                            }camera.setVisibility(View.GONE);
                            searchClearButton.setVisibility(View.GONE);
                            toolbarSearchView.setText("");
                            if (Recycler_View_Adapter.list.size() != 0) {
                                getJSON_search.adapter.clear();
                                progresbar.setVisibility(View.VISIBLE);
                                new getJSON_recomend(getApplicationContext(), (RecyclerView) findViewById(R.id.recyclerview), getApplication(), (ImageView) findViewById(R.id.itemImage)).execute("1");
                            }
                        }
                        else {
                            if (drawer.isDrawerOpen(GravityCompat.START)) {
                                drawer.closeDrawer(GravityCompat.START);

                            } else
                                drawer.openDrawer(GravityCompat.START);
                        }

                    }

                });
            }
        });
        progresbar.setVisibility(View.VISIBLE);
        new getJSON_recomend(getApplicationContext(), (RecyclerView) findViewById(R.id.recyclerview), getApplication(), (ImageView) findViewById(R.id.itemImage)).execute("1");

        //new horizontalRecycler(this,(RecyclerView)findViewById(R.id.recyclerview));

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        toggle.syncState();
    }

    public void onClick(View v)
{
if(v.getId()==R.id.camerabut)
{
    int permission = ContextCompat.checkSelfPermission(this,
            CAMERA);
    if (permission != PackageManager.PERMISSION_GRANTED)
    {
        ActivityCompat.requestPermissions(MainPage.this,new String[]
                {
                        CAMERA,

                },1);
    }
    else
    {
        IntentIntegrator ig=new IntentIntegrator(this);
        ig.initiateScan();
    }

}
}

    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    IntentIntegrator ig=new IntentIntegrator(this);
                    ig.initiateScan();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public void onActivityResult(int requestCode,int resultCode,Intent intent)
    {
        IntentResult ir=IntentIntegrator.parseActivityResult(requestCode,resultCode,intent);
        if(ir!=null)
        {
            Toast.makeText(getApplicationContext(), ir.getContents(), Toast.LENGTH_SHORT).show();

            new getJSON_search( getApplicationContext(), (RecyclerView) findViewById(R.id.recyclerview), getApplication(), (ImageView) findViewById(R.id.imageView)).execute(ir.getContents());

        }
        else
            Toast.makeText(getApplicationContext(), "nope boy", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBackPressed() {
      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            if(backflag==0)
            {
                Toast.makeText(getApplicationContext(), "Press Back Again To Exit", Toast.LENGTH_SHORT).show();
                backflag=1;
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                  backflag=0;
                    }
                }, 3000);
            }
            else {
                backflag=0;
                super.onBackPressed();

            }
        }
    }

    public void newActivity()
{


      Log.d("Kissa","Re");
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);
        Log.d("MenuFlag",menuflag+"");
        if(menuflag==0) {
            menu.findItem(R.id.action_signin).setVisible(true);

        }
            else
        {
            menuflag=0;
            menu.findItem(R.id.action_signin).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_signin) {
            if (flag == 0) {
                Intent mainIntent = new Intent(MainPage.this, MainActivity.class);
                MainPage.this.startActivity(mainIntent);
                MainPage.this.finish();
            }
            else
                Toast.makeText(this, "Already Signed In!", Toast.LENGTH_SHORT).show();
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.reserve) {
            Toast.makeText(getApplicationContext(), "Not Implemented", Toast.LENGTH_SHORT).show();
            // Handle the camera action
        } else if (id == R.id.notification) {

            Toast.makeText(getApplicationContext(), "Not Yet Implemented", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.issue) {

            Toast.makeText(getApplicationContext(), "Not Yet Implemented", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.logout) {
            getJSON.Name=getJSON.user_role=getJSON.Email=null;
        flag=0;
            Intent mainIntent = new Intent(this,MainPage.class);
            this.startActivity(mainIntent);
            this.finish();
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

    private enum ActionDrawableState {
        BURGER, ARROW
    }
}