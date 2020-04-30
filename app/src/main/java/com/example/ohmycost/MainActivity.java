package com.example.ohmycost;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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

    private TextView theDate;
    private ListView typeListOut;
    private Button graph,add;

    DBHelper DB;
    DBHelper myDB = new DBHelper(this);
    Cursor data, mydata;
    ArrayList<String> theList = new ArrayList<>();
    ArrayList<String> list_final = new ArrayList<>();
    ArrayList<String> arr = new ArrayList<>();
    ListAdapter listAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        graph = (Button)findViewById(R.id.butt_graph);
        add = (Button)findViewById(R.id.add);
        typeListOut = findViewById(R.id.TypelistView);

        TextView total_month_text = (TextView)findViewById(R.id.monthlyTotal);
        double totalMonth_1 = ListView();
        total_month_text.setText("Total : "+ String.valueOf(totalMonth_1));

        final TextView total = (TextView)findViewById(R.id.total);


        //data = myDB.getListContents_ex();
        DB = new DBHelper(this);
        mydata = DB.getListExpense();



        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent page_graph = new Intent(MainActivity.this,graph.class);
                startActivity(page_graph);

            }
        });
        CalendarView calendar = (CalendarView)findViewById(R.id.calendarView);
        theDate = (TextView)findViewById(R.id.date);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent page_add = new Intent(MainActivity.this,chooseExpense.class);
                String strDate = (String)theDate.getText();
                if (strDate.length() == 0 ){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Select the date");
                    builder.setPositiveButton("เลือก", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(MainActivity.this,"OK",Toast.LENGTH_SHORT).show();
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
                }
                startActivity(page_add);
            }
        });

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth +"/"+month+"/"+year;
                theDate.setText(date);
                //total.setText("Total = "+Double.toString(getTotal())); //ไม่แน่ใจว่าควรอยู๋ตรงไหน
            }
        });

        typeListOut.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        ListView();
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
        double total_month = 0;
        if(data.getCount() == 0){
            Toast.makeText(MainActivity.this,"db was empty",Toast.LENGTH_LONG).show();
        }else{
            while (data.moveToNext()) {
                theList.add(data.getString(2));
                listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                typeListOut.setAdapter(listAdapter);
            }
            System.out.println(theList);
            if (theList.size() > 0){
                for(int i = 0; i < theList.size(); i++){
                    double total_m = 0;
                    total_m = Double.parseDouble(theList.get(i));
                    total_month = total_month + total_m;
                    System.out.println(total_m);
                }
            }
            //setTotalMonth(total_month);
        }
        return total_month;

    }

   /* public void setTotalMonth(double total_month) {
        this.total_month = total_month;
    }

    public double getTotal(){
        total = total + this.expense;
        return total; //ค่าใช้จ่ายรายวัน
    }

    public String getTotalMonth() {
        //Cursor mydata = myDB.getListExpense();
        //data.moveToFirst();
        /*for (int i = 0 ; i < arr.size() ; i++){
            amount = Double.parseDouble(arr.get(i));
            total_month = total_month + amount;
        }
        if (listAdapter != null) {
            ArrayList<Double> listData = new ArrayList<>();
            for (int j = 0; j < mydata.getCount(); j++) {
                while (mydata.moveToPosition(j)) {
                    listData.add(mydata.getDouble(1));
                    total_month = total_month + mydata.getDouble(1);
                }
            }
        }
        System.out.println(myDB);*

        System.out.println(total_month);
        //System.out.println(theList);

        return "Total = "+total_month;
    }*/

}
