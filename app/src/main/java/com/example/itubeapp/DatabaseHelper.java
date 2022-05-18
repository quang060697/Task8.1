package com.example.itubeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {

        super(context, "itube_db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USER_TABLE = "CREATE TABLE USERS(USERID INTEGER PRIMARY KEY AUTOINCREMENT ,FULLNAME TEXT, USERNAME TEXT, PASSWORD TEXT)";
        String CREATE_PLAYLIST_TABLE = "CREATE TABLE PLAYLISTS(PLAYLISTID INTEGER PRIMARY KEY AUTOINCREMENT ,USERNAME TEXT,URL TEXT)";
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(CREATE_PLAYLIST_TABLE);
    }
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String DROP_USER_TABLE = "DROP TABLE IF EXISTS USERS";
        String DROP_PLAYLIST_TABLE = "DROP TABLE IF EXISTS PLAYLISTS";
        sqLiteDatabase.execSQL(DROP_USER_TABLE);
        sqLiteDatabase.execSQL(DROP_PLAYLIST_TABLE);
        onCreate(sqLiteDatabase);
    }
    public long insertUser(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put("FULLNAME",user.getFullname());
        contentValues.put("USERNAME",user.getUsername());
        contentValues.put("PASSWORD",user.getPassword());

        long newRow = db.insert("USERS",null,contentValues);
        db.close();
        return newRow;
    }
    public boolean fetchUser(String username, String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("USERS", new String[]{"userid"}, "USERNAME=? and PASSWORD=?",new String[]{username, password}, null, null, null);
        int rowsCount = cursor.getCount();
        db.close();

        if (rowsCount > 0)
            return  true;
        else
            return false;
    }
    public long insertToPlaylist(Playlist playlist)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put("USERNAME",playlist.getUsername());
        contentValues.put("URL",playlist.getUrl());
        long newRow = db.insert("PLAYLISTS",null,contentValues);
        db.close();
        return newRow;
    }
    public List<Playlist> fetchAllPlaylist (String username){
        List<Playlist> orderList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = " SELECT * FROM PLAYLISTS WHERE USERNAME = '"+username.trim()+"'" ;
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst()) {
            do {
                String url = cursor.getString(2);
                Playlist playlist = new Playlist(url);
                orderList.add(playlist);
            } while (cursor.moveToNext());

        }

        return orderList;
    }
}
