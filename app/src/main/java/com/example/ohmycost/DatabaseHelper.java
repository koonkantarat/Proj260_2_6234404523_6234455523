package com.example.ohmycost;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "yourtyp.db";
    private static final String DB_TABLE = "type_Table";

    private static final  String ID = "ID";
    private static final  String NAME = "Types";

    private static final  String CREATE_TABLE = " CREATE TABLE "+ DB_TABLE+" ( "+
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +NAME+ " TEXT "+ ")";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        onCreate(db);
    }

    public boolean insertData(String type){
        SQLiteDatabase SQdb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, type);

        long result = SQdb.insert(DB_TABLE, null, contentValues);
        return result != -1;
    }

    public Cursor getlistContents(){
        SQLiteDatabase SQdb = this.getWritableDatabase();
        Cursor data = SQdb.rawQuery("SELECT * FROM "+ DB_TABLE, null);
        return data;
    }
}


