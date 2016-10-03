package com.example.adity.loginscreen;

/**
 * Created by adity on 24/08/2016.
 */
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "login";
    private static final String TABLE_NAME = "login";
    private static final String COLUMN_ID = "id";

    Context c;
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    c=context;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Category table create query

        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_NAME + "("+ "type varchar(20),name varchar (50),username  varchar(50) primary key,password varchar(50),email varchar(50));";
        db.execSQL(CREATE_ITEM_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public boolean usernameavailability(String uname)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cur=db.rawQuery("select * from login where username='"+uname+"';",null);
        if(cur.moveToFirst())
        return false;
        else
            return true;
    }
    /**
     * Inserting new lable into lables table
     * */
    public boolean checklogin(String uname,String pass) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from login where username='" + uname + "';", null);
        if (cursor.moveToFirst()) {
            if (cursor.getString(3).toString().equals(pass))
                return true;
             else
                return false;

        }
        else
            return false;

    }


public void signup(String wtf)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String str="INSERT into login values("+wtf+")";
        db.execSQL(str);

    }

    /**
     * Getting all labels
     * returns list of labels
     * */
    public List<String> getAllLabels(){
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1));//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return list;
    }
}