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

public class MainActivity extends AppCompatActivity {

    private double total;
    //private double expense_month;
    private double expense_day;
    private String totalMonth;
    private String date;
    private String textTotal;
    private String day_d;
    private String year_y;
    private String month_m;

    private Button[] btnAdd = new Button[1];
    private TextView theDate,text_list;
    private Button graph, add;

    DBHelper myDB = new DBHelper(this);
    Cursor data, data_a;
    ArrayList<String> theList = new ArrayList<>();
    ArrayList<String> theList_cal = new ArrayList<>();
    ArrayList<String> list_final = new ArrayList<>();
    ArrayList<String> list_date_expense = new ArrayList<>();
    ArrayList<String> listData = new ArrayList<>();
    ListAdapter listAdapter ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        graph = (Button) findViewById(R.id.butt_graph);
        //add = (Button) findViewById(R.id.add);
        theDate = (TextView) findViewById(R.id.date);
        text_list = findViewById(R.id.textlist);
        final TextView total_text = (TextView) findViewById(R.id.total);
        final TextView total_month_text = (TextView) findViewById(R.id.monthlyTotal);


        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent page_graph = new Intent(MainActivity.this, graph.class);
                startActivity(page_graph);

            }
        });
        CalendarView calendar = (CalendarView) findViewById(R.id.calendarView);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                final String day = dayOfMonth+"/"+(month+1)+"/"+year;
                String[] separate = day.split("/");
                day_d = separate[0];
                month_m = separate[1];
                year_y = separate[2];
                final String month_m = (month+1)+"/"+year;
                final String year_y = String.valueOf(year);
                theDate.setText(day);
                String item=getText(day);
                text_list.setText(item);
                total_text.setText(getTotalDay(day_d));

                btnAdd[0] = findViewById(R.id.add);
                btnAdd[0].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,chooseExpense.class);
                        intent.putExtra("day",day);
                        intent.putExtra("month",month_m);
                        intent.putExtra("year",year_y);

                        startActivity(intent);
                    }

                });
            }
        });

        total_text.setText(getTotalDay(day_d));
        total_month_text.setText(getTotalMonth(month_m));

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public String getText(String date) {
        Cursor data = myDB.getData(date);
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getString(1)+"  :                "+data.getInt(2));
        }
        String item="";
        for(int i=0;i<listData.size();i++){
            item+=listData.get(i)+"\n";
        }
        return item;
    }

    public String getTotalDay(String day) {
        Cursor data = myDB.getAmount(day);
        ArrayList<Integer> listData = new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getInt(1));
        }
        double totalDay = 0;
        for (int i = 0 ; i<theList_cal.size() ; i++){
            totalDay = totalDay + Double.parseDouble(theList_cal.get(i));
        }
        return "Total : " + totalDay;
    }

    public String getTotalMonth(String month) {
        Cursor data = myDB.getTotalMonth(month);
        ArrayList<Integer> listData = new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getInt(1));
        }
        int total=0;
        for(int i=0;i<=listData.size()-1;i++){
            total+=listData.get(i);
        }
        return "Total month= "+total+" Baht";
    }
    }

