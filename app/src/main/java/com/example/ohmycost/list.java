package com.example.ohmycost;

import android.provider.BaseColumns;

public class list {

    private String type;
    private String expense;
    private int id;

    public list(){

    }
    public list (String type, String expense){
        this.type = type;
        this.expense = expense;
    }

    //database
    public static final String DATABASE_NAME = "list.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE = "list";

    public class column{
        public static final String ID = BaseColumns._ID;
        public static final String TYPE = "type";
        public static final String EXPENSE = "expense";
    }

    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public void setExpense(String expense){
        this.expense = expense;
    }
    public String getExpense(){
        return this.expense;
    }
    public int setID(int id){
        this.id = id;
        return id;
    }
}
