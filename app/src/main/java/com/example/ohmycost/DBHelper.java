package com.example.ohmycost;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = "DBHelper";

    public static final String DATABASE_NAME = "mylist.db";
    public static final String TABLE_NAME = "mylist_data";
    public static final String COL_ID = "ID";
    public static final String COL_TYPE = "type";
    public static final String COL_EXPENSE = "expense";
    public static final String COL_DAY = "day";
    public static final String COL_MONTH = "month";
    public static final String COL_YEAR = "year";
    private static final int DATABASE_VERSION = 1;


    public DBHelper( @Nullable Context context) {
        super(context, "DB_list.db",null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_LIST_TABLE = "CREATE TABLE "+ TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_TYPE + " TEXT,"
                + COL_EXPENSE + " TEXT," + COL_DAY + " TEXT," + COL_MONTH + " TEXT," + COL_YEAR + " TEXT);";

        db.execSQL(CREATE_LIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String DROP_LIST_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(DROP_LIST_TABLE);

        onCreate(db);
    }

    public boolean addData_ex(String type,String expense,String day,String month,String year){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.COL_TYPE,type);
        contentValues.put(DBHelper.COL_EXPENSE,expense);
        contentValues.put(DBHelper.COL_DAY,day);
        contentValues.put(DBHelper.COL_MONTH,month);
        contentValues.put(DBHelper.COL_YEAR,year);
        //System.out.println(contentValues);
        Log.d(TAG,"database" + db ); //ครั้งแรกแอดค่าเป็น null
        Log.d(TAG, "addData: Adding " + type + ","+ expense + " to " + TABLE_NAME);
        Log.d(TAG, "addDate: Adding " + day + ","+ month + "," + year + " to " + TABLE_NAME);
        long result = db.insert(TABLE_NAME,null,contentValues);
        //System.out.println(db);
        db.close();
        //insert return -1
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean addData_date(String day,String month, String year){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.COL_DAY,day);
        contentValues.put(DBHelper.COL_MONTH,month);
        contentValues.put(DBHelper.COL_YEAR,year);
        Log.d(TAG, "addDate: " + day + "/"+ month + "/" + year + " to " + TABLE_NAME);
        long result = db.insert(TABLE_NAME,null,contentValues);
        //db.close();
        //insert return -1
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getListContents_ex(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data_contents = db.rawQuery("SELECT " + COL_ID + "," + COL_TYPE + "," + COL_EXPENSE +","+ COL_DAY + ","
                + COL_MONTH + ","+ COL_YEAR +" FROM " + TABLE_NAME,null);
        return data_contents;
    }

    /*public Cursor getListExpense(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+ COL_ID + "," + COL_EXPENSE + " FROM "+ TABLE_NAME ;
        Cursor data_Expense = db.rawQuery(query,null);
        Log.d(TAG,data_Expense + TABLE_NAME);
        return data_Expense;
    }*/

    public Cursor getListDay(String day, String month, String year){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME + " WHERE " + COL_DAY + " = '" + day + "'" + " AND "
                + COL_MONTH + " = '" + month + "'" + " AND " + COL_YEAR + " = '" + year + "'";
        //TABLE_NAME + " WHERE " + COL_DAY + " = '" + day + "'" + " AND " + COL_MONTH + " = '" + month + "'";
        Cursor data_Date = db.rawQuery(query,null);
        Log.d(TAG,data_Date + TABLE_NAME);
        return data_Date;
    }

    public Cursor getData(String day){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_ID + "," + COL_TYPE + "," + COL_EXPENSE + " FROM " +
                TABLE_NAME + " WHERE " + COL_DAY + " = '" + day + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getAmount(String day){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_TYPE + "," + COL_EXPENSE + " FROM " + TABLE_NAME + " WHERE " +
                COL_DAY + " = '" + day + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getDayMonth(String day,String month){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_TYPE + "," + COL_EXPENSE + " FROM " +
                TABLE_NAME + " WHERE " + COL_DAY + " = '" + day + "'" + " AND " + COL_MONTH + " = '" + month + "'"; //ใช้ where เช็ค month ไปแล้ว
        //COL_EXPENSE + " = '" + amount + "'"+ " AND " + COL_DAY + " = '" + day + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getTotalMonth(String month){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_ID + "," + COL_TYPE + "," + COL_EXPENSE + " FROM " +
                TABLE_NAME + " WHERE " + COL_MONTH + " = '" + month + "'"; //ใช้ where เช็ค month ไปแล้ว
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getType(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_TYPE + " FROM " +TABLE_NAME ;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getMonthYear(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_MONTH + " FROM " +TABLE_NAME + " ORDER BY " + COL_MONTH + " ASC";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getDataMonth(String month){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_TYPE + "," + COL_EXPENSE + " FROM " + TABLE_NAME +
                " WHERE " + COL_MONTH + " = '" + month + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemID(String day,String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_ID + " FROM " + TABLE_NAME + " WHERE " +
                COL_EXPENSE + " = '" + amount + "'"+ " AND " + COL_DAY + " = '" + day + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void updateName(String newName, int id, String oldName){
        SQLiteDatabase SQdb = this.getWritableDatabase();
        String query = "UPDATE "+ TABLE_NAME + " SET "+ COL_EXPENSE +
                " = '"+ newName + "' WHERE "+ COL_ID + " = '"+ id +"'"+
                " AND "+ COL_EXPENSE + " = '"+ oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to "+ newName);
        SQdb.execSQL(query);
        SQdb.close();
    }

    public void deleteName(int id, String expense){
        SQLiteDatabase SQdb = this.getWritableDatabase();
        String query = "DELETE FROM "+ TABLE_NAME + " WHERE "+ COL_ID +
                " = '" + id + "'" +  " AND " + COL_EXPENSE + "= '" + expense + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + expense + " from database.");
        SQdb.execSQL(query);
        SQdb.close();
    }
}