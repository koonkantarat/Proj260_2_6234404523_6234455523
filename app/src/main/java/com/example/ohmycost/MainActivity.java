package com.example.ohmycost;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.CalendarView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Dictionary;

public class MainActivity extends AppCompatActivity {

    private double total;
    private double expense,amount;
    private String totalMonth;
    private String date,day_d,year_y,month_m;

    private TextView theDate;
    private ListView typeListOut;
    private Button graph,add;

    private String Typeselect,Expenseput;

    DBHelper DB = new DBHelper(this);;
    DBHelper myDB = new DBHelper(this);
    Cursor data, data_a;
    ArrayList<String> theList = new ArrayList<>();
    ArrayList<String> list_final = new ArrayList<>();
    ArrayList<String> list_date_expense = new ArrayList<>();
    ArrayList<String> theList_cal = new ArrayList<>();
    ListAdapter listAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        graph = (Button)findViewById(R.id.butt_graph);
        add = (Button)findViewById(R.id.add);
        typeListOut = findViewById(R.id.TypelistView);
        TextView total_text = (TextView)findViewById(R.id.total);

        TextView total_month_text = (TextView)findViewById(R.id.monthlyTotal);

        Intent intent = getIntent();
        Typeselect = intent.getStringExtra("type");
        Expenseput = intent.getStringExtra("expense");



        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent page_graph = new Intent(MainActivity.this,graph.class);
                startActivity(page_graph);

            }
        });
        CalendarView calendar = (CalendarView)findViewById(R.id.calendarView);
        theDate = (TextView)findViewById(R.id.date);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                date = dayOfMonth + "/" + (month+1) + "/" + year;
                theDate.setText(date);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent page_add = new Intent(MainActivity.this,chooseExpense.class);
                String strDate = (String)theDate.getText();
                //arr_date = new String[3];

                if (date.length() == 0 ){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Select the date");
                    builder.setPositiveButton("เลือก", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(MainActivity.this,"OK",Toast.LENGTH_LONG).show();
                        }
                    });
                    builder.setNegativeButton("ออก", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Toast.makeText(MainActivity.this,"BYE!!!",Toast.LENGTH_LONG).show();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }else{
                    //for(int i = 0 ;i<date.length() ; i++){
                        //final = date.split("/");
                    //}
                    String[] separate = strDate.split("/");
                    day_d = separate[0];
                    month_m = separate[1];
                    year_y = separate[2];
                }

                page_add.putExtra("day",day_d);
                page_add.putExtra("month",month_m);
                page_add.putExtra("year",year_y);
                System.out.println("vhfirguitjgbodpkdf");
                System.out.println(day_d+month_m+year_y);
                startActivity(page_add);
            }
        });

        total_text.setText("Total : " + String.valueOf(getTotalDay()));
        total_month_text.setText("Totalmonth  : "+ String.valueOf(ListView()));

        typeListOut.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void setTotal(double expense) {
        this.expense = expense;
    }

    public double ListView(){
        data = myDB.getListContents_ex();
        //data_a = DB.getListDay(day_d,month_m,year_y);
        //boolean insert_i = DB.addData_date(day_d,month_m,year_y);
        double total_month = 0;
        if(data.getCount() == 0){
            Toast.makeText(MainActivity.this,"db was empty",Toast.LENGTH_LONG).show();
        }else{
            /*while (data.moveToNext()) {
                theList.add(data.getString(2));
            }
            System.out.println(theList);
            //System.out.println(data.getString(3));
            ArrayList<String> x = new ArrayList<>();
            //x.add(data.getString(3));
            //System.out.println(x);//สร้าง ArrayList เพื่อเก็บข้อมูลประเภทที่ผู้ใช้ป้อนเข้ามาใน Database
            SQLiteDatabase mydata = myDB.getWritableDatabase();
            Cursor cursor = mydata.rawQuery("SELECT * FROM " + DBHelper.TABLE_NAME, null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                x.add(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TYPE)));
                x.add(cursor.getString(cursor.getColumnIndex(DBHelper.COL_EXPENSE)));
                x.add(cursor.getString(cursor.getColumnIndex(DBHelper.COL_DAY)));
                x.add(cursor.getString(cursor.getColumnIndex(DBHelper.COL_MONTH)));
                x.add(cursor.getString(cursor.getColumnIndex(DBHelper.COL_YEAR)));
                cursor.moveToNext();
            }
            System.out.println(x);
            if( day_d == x.get(2) && month_m == x.get(3) && year_y == x.get(4)){
                list_final.add(data.getString(1) + ":          " + data.getString(2));
                while ( data.moveToNext()){
                    //list_final.add(x.get(0) + ":          " + x.get(1));
                    list_date_expense.add(x.get(1));
                }
                System.out.println(list_final);
                listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list_final);
                typeListOut.setAdapter(listAdapter);
            }*/

            if(data.getCount() == 0){
                Toast.makeText(MainActivity.this,"db was empty",Toast.LENGTH_LONG).show();
            }else{
                while (data.moveToNext()) {
                    theList.add(data.getString(1)+": " + data.getInt(2));
                    theList_cal.add(data.getString(2));
                    listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                    typeListOut.setAdapter(listAdapter);
                }
            }
            System.out.println(theList_cal);
            if (theList_cal.size() > 0){
                for(int i = 0; i < theList_cal.size(); i++){
                    double total_m = 0;
                    total_m = Double.parseDouble(theList_cal.get(i));
                    total_month = total_month + total_m;
                    System.out.println(total_m);
                }
            }

            /*if (list_date_expense.size() > 0){
                for(int i = 0; i < theList.size(); i++){
                    double total = 0;
                    total = Double.parseDouble(list_date_expense.get(i));
                    total_month = total_month + total;
                    System.out.println(total);
                }
            }*/
          }
        return total_month;
    }

    /*public double getTotalDay(String day, String month, String year) {
        Cursor data = DB.getListDay(day, month ,year);
        System.out.println(data);
        ArrayList<Integer> listData = new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getInt(1));
        }
        double total=0;
        for(int i=0;i<=listData.size()-1;i++){
            total+=listData.get(i);
        }
        return total;
    }*/

    public double getTotalDay() {
        if (list_date_expense.size() > 0) {
            for (int i = 0; i < theList.size(); i++) {
                double total_t = 0;
                total_t = Double.parseDouble(list_date_expense.get(i));
                total = total + total_t;
                System.out.println(total);
            }
        }
        return total;
    }
}
