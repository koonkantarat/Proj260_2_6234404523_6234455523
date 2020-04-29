package com.example.ohmycost;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "type_table";

    private static final  String ID = "ID";
    private static final  String NAME = "types";


    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE "+ TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int j) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String type){
        SQLiteDatabase SQdb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, type);

        Log.d(TAG, "addData: Adding " + type + " to " + TABLE_NAME);
        long result = SQdb.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        }else {
            return true; }
    }

    public Cursor getData(){
        SQLiteDatabase SQdb = this.getWritableDatabase();
        Cursor data = SQdb.rawQuery("SELECT * FROM "+ TABLE_NAME, null);
        return data;
    }

    public Cursor getItemID(String name){
        SQLiteDatabase SQdb = this.getWritableDatabase();
        String query = " SELECT "+ ID + " FROM "+ TABLE_NAME + " WHERE "+ NAME + " = ' name '";
        Cursor data = SQdb.rawQuery(query, null);
        return data;
    }

    public void updateName(String newName, int id, String oldName){
        SQLiteDatabase SQdb = this.getWritableDatabase();
        String query = "UPDATE "+ TABLE_NAME + " SET "+ NAME +
                " = '"+ newName + "' WHERE "+ ID + " = '"+ id +"'"+
                " AND "+ NAME + " = '"+ oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to "+ newName);
        SQdb.execSQL(query);
        SQdb.close();
    }

    public void deleteName(int id, String name){
        SQLiteDatabase SQdb = this.getWritableDatabase();
        String query = "DELETE FROM "+ TABLE_NAME + " WHERE "+ ID +
                " = '" + id + "'" + " AND " + NAME + " = '"+ name+ "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        SQdb.execSQL(query);
        SQdb.close();
    }

}


