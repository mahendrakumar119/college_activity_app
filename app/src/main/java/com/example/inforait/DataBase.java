package com.example.inforait;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class DataBase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "registration.db";
    public static final String TABLE_NAME = "register";
    public static final String TABLE_NAME1 = "admin1";
    public static final String TABLE_NAME2 = "events";
    public static final String TABLE_NAME3 = "enrolled";
    public static final String COL_1 = "ID";

    public static final int VERSION = 14;
    public static final String COL_2 = "firstname";
    public static final String COL_3 = "lastname";
    public static final String COL_4 = "username";
    public static final String COL_5 = "password";
    public static final String COL_6 = "image";

    public static final String Admin_COL_2 = "ACODE";
    public static final String Admin_COL_3 = "ANAME";
    public static final String Admin_COL_4 = "PWD";
    public static final String Event_COL_1 = "ID";
    public static final String Event_COL_2 = "acode";
    public static final String Event_COL_3 = "etitle";
    public static final String Event_COL_4 = "sdescription";
    public static final String Event_COL_5 = "ldescription";
    public static final String Event_COL_6 = "interested";

    public static final String Enrolled_COL_1 = "USERNAME";
    public static final String Enrolled_COL_2 = "ID";
    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE register (ID INTEGER PRIMARY KEY AUTOINCREMENT,FIRSTNAME TEXT,LASTNAME TEXT,USERNAME TEXT,PASSWORD TEXT,IMAGE BLOB)");
        sqLiteDatabase.execSQL("CREATE TABLE admin1 (ACODE TEXT PRIMARY KEY,ANAME TEXT,PWD TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE events (ID INTEGER PRIMARY KEY AUTOINCREMENT,ACODE TEXT,ETITLE TEXT,SDESCRIPTION TEXT,LDESCRIPTION TEXT ,INTERESTED INTEGER ,FOREIGN KEY(ACODE) REFERENCES admin1(ACODE))");
        sqLiteDatabase.execSQL("CREATE TABLE enrolled (USERNAME TEXT,ID TEXT,FOREIGN KEY(USERNAME) REFERENCES register(USERNAME),FOREIGN KEY(ID) REFERENCES register(ID))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME1);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME2);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME3);

        onCreate(sqLiteDatabase);
    }

    //To add normal user to db
    public long addUser(String fname, String lname, String user, String pass, byte[] img) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstname", fname);
        contentValues.put("lastname", lname);
        contentValues.put("username", user);
        contentValues.put("password", pass);
        contentValues.put("image", img);
        long res = db.insert("register", null, contentValues);
        db.close();
        return res;
    }

    //To add admin user to db
    public long addAdmin(String acode, String aname, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ACODE", acode);
        contentValues.put("ANAME", aname);
        contentValues.put("PWD", pass);

        long res = db.insert("admin1", null, contentValues);
        db.close();
        return res;
    }

    //To Authenticate user
    public Cursor checkUser(String username, String password) {
        String[] columns = {COL_6};
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_4 + "=?" + " and " + COL_5 + "=?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        return cursor;
    }

    //To get user details
    public Cursor getProfileDetails(String username) {
        String[] columns = {COL_2, COL_3};
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_4 + "=?";
        String[] selectionArgs = {username};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        // Cursor  cursor = db.rawQuery("select * from register where username =?",new String[]{username});
        return cursor;
    }

    //To authenticate admin user
    public Cursor checkAdminUser(String acode, String pwd) {
        String[] columns = {Admin_COL_2};
        SQLiteDatabase db = getReadableDatabase();
        String selection = Admin_COL_2 + "=?" + " and " + Admin_COL_4 + "=?";
        String[] selectionArgs = {acode, pwd};

        Cursor cursor = db.query(TABLE_NAME1, columns, selection, selectionArgs, null, null, null);
        return cursor;
    }

    //    public long addEvent(String acode, String etitle, String sdescription, String ldescription) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("acode", acode);
//        contentValues.put("etitle", etitle);
//        contentValues.put("sdescription", sdescription);
//        contentValues.put("ldescription", ldescription);
//
//
//        long res = db.insert("events", null, contentValues);
//        db.close();
//        return res;
//    }
    public long addEvent(String acode, String etitle, String sdescription, String ldescription) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("acode", acode);
        contentValues.put("etitle", etitle);
        contentValues.put("sdescription", sdescription);
        contentValues.put("ldescription", ldescription);
        contentValues.put("interested", 0);



        long res = db.insert("events", null, contentValues);
        db.close();
        return res;
    }

    public Cursor getEventDetails(String acode) {
        String[] columns = {Event_COL_1,Event_COL_3, Event_COL_4, Event_COL_5,Event_COL_6};
        SQLiteDatabase db = getReadableDatabase();
        String selection = Event_COL_2 + "=?";
        String[] selectionArgs = {acode};

        Cursor cursor = db.query(TABLE_NAME2, columns, selection, selectionArgs, null, null, null);
        return cursor;
    }
    public Cursor getAllEventDetails() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from events",null);
        return cursor;
    }

//   public void buttonClicked(int id) {
//      SQLiteDatabase db = this.getWritableDatabase();
//       ContentValues contentValues = new ContentValues();
//       contentValues.put("interested", 1);
//      db.update(TABLE_NAME2,contentValues, Event_COL_1 + " = ?",new String[]{String.valueOf(id)});
//    }
}


