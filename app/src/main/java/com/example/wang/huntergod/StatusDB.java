package com.example.wang.huntergod;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wang on 2016/7/25.
 */
public class StatusDB extends SQLiteOpenHelper {
    final String tableName="DBtable";
    public StatusDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        final String create = "CREATE TABLE IF NOT EXISTS "
                + tableName + "("
                + "_id  INTEGER PRIMARY KEY,"
                + "EAT INTEGER,"    //0
                + "BATH INTEGER,"   //1
                + "PLAY INTEGER,"   //2
                + "HAPPY INTEGER,"  //3
                + "CHOOSE INTEGER"+");";//4

        db.execSQL(create);
    }

    public Cursor selectDB(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.query("DBtable", null, null, null, null, null, null);
        return cursor;
    }

    void upStatus(int status[]){
        SQLiteDatabase db=this.getWritableDatabase();
        SQLiteDatabase rdb=this.getReadableDatabase();
        Cursor cursor=rdb.query("DBtable", null, null, null, null, null, null);
        cursor.moveToFirst();
        ContentValues cv = new ContentValues();
        cv.put("EAT", status[0]);
        cv.put("BATH", status[1]);
        cv.put("PLAY", status[2]);
        cv.put("HAPPY", status[3]);
        cv.put("CHOOSE", status[4]);


        db.update("DBtable", cv, "_id=" + cursor.getInt(1), null);
        db.update("DBtable", cv, "_id=" + cursor.getInt(2), null);
        db.update("DBtable", cv, "_id=" + cursor.getInt(3), null);
        db.update("DBtable", cv, "_id=" + cursor.getInt(4), null);
        db.update("DBtable", cv, "_id=" + cursor.getInt(5), null);



        db.close();
    }

    void initials(){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("EAT", 5);
        cv.put("BATH", 5);
        cv.put("PLAY", 5);
        cv.put("HAPPY", 5);
        cv.put("CHOOSE", 0);

        db.insert("DBtable", null, cv);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        final String drop="DROP TABLE"+tableName;
        db.execSQL(drop);
        onCreate(db);
    }
}


