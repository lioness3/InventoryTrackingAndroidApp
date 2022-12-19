package com.example.project2_inventorytracker_joanncarter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;

    public DatabaseHelper(Context context) {
        super(context, "UserData.db", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase DB) {    // create database table
        DB.execSQL("create Table UserLogin(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists UserLogin");
    }

    public Boolean insertUserData(String username, String password){   // insert user login info into db
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);

        long result = DB.insert("UserLogin", null, contentValues);
        // verify data had been inserted
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean updateUserData(String username, String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);

        Cursor cursor = DB.rawQuery("Select * from UserLogin where username=?", new String[]{username});

        // verify data had been inserted
        if(cursor.getCount() > 0) {
            long result = DB.update("UserLogin", contentValues, "username=?", new String[]{username});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }
    public Boolean deleteUserData(String username ){   //delete user account
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from UserLogin where username=?", new String[]{username});

        // verify data had been inserted
        if(cursor.getCount() > 0) {
            long result = DB.delete("UserLogin", "username=?", new String[]{username});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }
    public Cursor getUserData( ) {   // get all data from table
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from UserLogin", null);
        return cursor;
    }

    public Boolean validateUserExists(String username){   // check to see if email has been used to create an account yet
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from UserLogin where username=?", new String[]{username});
        if(cursor.getCount() > 0) {
            return true;
        }else{
            return false;
        }
    }

    public Boolean validateUserPassword(String username, String password){   // check to see if user enters correct password
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from UserLogin where username=? and password=?", new String[]{username, password});
        if(cursor.getCount() > 0) {
            return true;
        }else{
            return false;
        }
    }
    public Boolean insertInventoryData(String itemName, Integer itemCount){    // insert items in db
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("itemName", itemName);
        contentValues.put("itemCount", itemCount);

        long result = DB.insert("UserLogin", null, contentValues);
        // verify data had been inserted
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean updateInventoryData( String itemName, Integer itemCount){  //update items in inventory
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("itemName", itemName);
        contentValues.put("itemCount", itemCount);

        Cursor cursor = DB.rawQuery("Select * from UserLogin where itemName=?", new String[]{itemName});

        // verify data had been inserted
        if(cursor.getCount() > 0) {
            long result = DB.update("UserLogin", contentValues, "itemName=?", new String[]{itemName});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }


}