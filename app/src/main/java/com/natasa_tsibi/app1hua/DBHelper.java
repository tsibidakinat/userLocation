package com.natasa_tsibi.app1hua;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Natasa on 5/12/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "User.db";
    private static final String TABLE_NAME = "user_table";
    private static final String COL_1 = "id";
    private static final String COL_2 = "userid";
    private static final String COL_3 = "longitude";
    private static final String COL_4 = "latidude";
    private static final String COL_5 = "dt";
    private static final String DELETE_TABLE = "DROP TABLE IF EXISTS " +TABLE_NAME;
//    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +TABLE_NAME + "("+
//                    COL_1+" INTEGER (11) PRIMARY KEY NOT NULL AYTOINCREMENT,"+
//                    COL_2+" TEXT (10) NOT NULL," +
//                    COL_3+" REAL NOT NULL," +
//                    COL_4+" REAL NOT NULL," +
//                    COL_5+" TEXT (20) NOT NULL" +
//                    ")";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + COL_2 + " TEXT (10) NOT NULL,"
            + COL_3 + " REAL NOT NULL,"
            + COL_4 + " REAL NOT NULL,"
            + COL_5 + " TEXT (20) NOT NULL" + ")";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }

    public boolean insertUser (User user) {
        long result;
        SQLiteDatabase db = this.getWritableDatabase();
        //ContentValues contentValues;
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, user.getUid());
        contentValues.put(COL_3, user.getLongitute());
        contentValues.put(COL_4, user.getLatitude());
        contentValues.put(COL_5, user.getTimestamp());

        result = db.insert(TABLE_NAME, null, contentValues);
        db.close();

        // return false if result is -1, else return true(seccessful insert)
        return result!=-1;
    }

    public boolean updateUser ( User newUser) {
        long result;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,newUser.getId());
        contentValues.put(COL_2,newUser.getUid());
        contentValues.put(COL_3,newUser.getLongitute());
        contentValues.put(COL_4,newUser.getLatitude());
        contentValues.put(COL_5,newUser.getTimestamp());

        result = db.update(TABLE_NAME, contentValues, COL_1 + "=?",new String[]{ String.valueOf(COL_1) });
        db.close();

        // return false if result is -1, else return true(seccessful insert)
        return result!=-1;

    }

    public List<User> getUsers() {
        List<User> usersList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();
        // Select All query
        Cursor cursor = db.query(TABLE_NAME,null,null,new String[]{},null,null,null);
        // adding to list while loop all rows
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COL_1)));
                user.setId(id);
                user.setUid(cursor.getString(cursor.getColumnIndexOrThrow(COL_2)));
                user.setLongitute(Float.parseFloat(cursor.getString(cursor.getColumnIndexOrThrow(COL_3))));
                user.setLatitude(Float.parseFloat(cursor.getString(cursor.getColumnIndexOrThrow(COL_4))));
                user.setTimestamp(cursor.getString(cursor.getColumnIndexOrThrow(COL_5)));

                usersList.add(user); // add user to list
            } while (cursor.moveToNext());
        }
        // return list of users
        return usersList;
    }

    public List<User> searchUsers(String uid, String dt) {
        List<User> usersList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();
        // Select query
        Cursor cursor = db.query(TABLE_NAME,null,COL_2 + "=? OR " + COL_5 + "=?",new String[]{uid,dt},null,null,null);
        // adding result to list while loop all rows
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(COL_1)));
                user.setId(id);
                user.setUid(cursor.getString(cursor.getColumnIndexOrThrow(COL_2)));
                user.setLongitute(Float.parseFloat(cursor.getString(cursor.getColumnIndexOrThrow(COL_3))));
                user.setLatitude(Float.parseFloat(cursor.getString(cursor.getColumnIndexOrThrow(COL_4))));
                user.setTimestamp(cursor.getString(cursor.getColumnIndexOrThrow(COL_5)));

                usersList.add(user); // add user to list
            } while (cursor.moveToNext());
        }

        // return list
        return usersList;
    }

}
