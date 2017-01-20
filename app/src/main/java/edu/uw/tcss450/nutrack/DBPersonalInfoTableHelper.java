package edu.uw.tcss450.nutrack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ming on 1/19/2017.
 */

public class DBPersonalInfoTableHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "nutrack.db";

    public static final String TABLE_NAME = "personal_info";

    public static final String COLUMN_EMAIL = "email";

    public static final String COLUMN_PASSWORD = "password";

    public static final String COLUMN_JOINDATE = "join_date";

    public DBPersonalInfoTableHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE personal_info(" +
                "email TEXT PRIMARY KEY ASC," +
                "password TEXT," +
                "join_date NUMERIC" +
                ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertMember(String theEmail, String thePassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues memberValues = new ContentValues();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        memberValues.put(COLUMN_EMAIL, theEmail);
        memberValues.put(COLUMN_PASSWORD, thePassword);
        memberValues.put(COLUMN_JOINDATE, dateFormat.format(date));

        db.insert(TABLE_NAME, null, memberValues);
        return true;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM memberTable", null);
        return result;
    }

    public int getMemberSize() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM memberTable", null);
        return result.getCount();
    }
}