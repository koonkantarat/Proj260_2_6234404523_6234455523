package com.example.ohmycost;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {

    private final String TAG = getClass().getSimpleName();

    private SQLiteDatabase sqLiteDatabase;

    public DBHelper( Context context) {
        super(context, "DB_list.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_LIST_TABLE = String.format("CREATE TABLE %s "+" (%s TEXT PRIMARY KEY AUTOINCREMENT, %s TEXT) ",
                list.TABLE,
                list.column.ID,
                list.column.TYPE,
                list.column.EXPENSE);

        Log.i(TAG, CREATE_LIST_TABLE );

        //create friend table
        db.execSQL(CREATE_LIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_LIST_TABLE = "DROP TABLE IF EXISTS list";
        db.execSQL(DROP_LIST_TABLE);

        onCreate(db);
    }

    public List<String> getList(){
        List<String> lists = new ArrayList<>();

        sqLiteDatabase = this.getWritableDatabase(); //เข้าถึงการอ่าน database

        Cursor cursor = sqLiteDatabase.query
                (list.TABLE, null,null,null,null,null,null);
                //query ข้อมูลจาก table list ทั้งหมด = SELECT * FROM list

        if (cursor != null){
            cursor.moveToFirst();
        }

        //ส่งค่าไป lists
        while (!cursor.isAfterLast()){

            lists.add(cursor.getString(0)+" "+cursor.getDouble(1)); //อาจจะใส่ i

            cursor.moveToNext();
        }

        sqLiteDatabase.close();

        return lists;
    }

    public void addList(list list) {
        sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues(); //contentValues putค่า key(ช่ื่อ column),value
        values.put(com.example.ohmycost.list.column.TYPE ,list.getType());
        values.put(com.example.ohmycost.list.column.EXPENSE,list.getExpense());

        sqLiteDatabase.insert(com.example.ohmycost.list.TABLE,null,values);

        sqLiteDatabase.close();
    }
}