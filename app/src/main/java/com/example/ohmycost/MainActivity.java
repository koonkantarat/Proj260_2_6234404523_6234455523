package com.example.ohmycost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.CalendarView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;

public abstract class MainActivity extends AppCompatActivity implements list{

    private double total;
    private double expense;
    private double total_month;
    private Dictionary sec1;
    private TextView theDate;
    protected ArrayList list;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button graph = (Button)findViewById(R.id.butt_graph);
        final Button add = (Button)findViewById(R.id.add);
        TextView total_month = (TextView)findViewById(R.id.monthlyTotal);
        total_month.setText("Total = "+Double.toString(getTotalMonth()));
        final TextView total = (TextView)findViewById(R.id.total);

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
                sec1.put(strDate, list);

                startActivity(page_add);
            }
        });

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth +"/"+month+"/"+year;
                theDate.setText(date);
                total.setText("Total = "+Double.toString(getTotal())); //ไม่แน่ใจว่าควรอยู๋ตรงไหน

            }
        });
        //ส่ delete ใช้กดปุ่มว่าลบอันนี้ แล้วววนหาใน list ว่าค่าที่เท่าไหร่แล้วก็ลบ
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void setTotal(double expense) {
        this.expense = expense;
    }

    public double getTotalMonth(){
        total_month = total_month + expense;
        return total_month; //ค่าใช้จ่ายรายเดือน
    }

    public double getTotal(){
        total = total + this.expense;
        return total; //ค่าใช้จ่ายรายวัน
    }
    //เอาชนิดมาเก็บใน list1
    public abstract void setType(chooseExpense type);
    //เอาเงินมาเก็บใน list2
    public abstract void setExpense(chooseExpense expense);
    //รวม list1,2 ไว้ใน list เพื่อใช้เป็น value ของ dict sec1 (ที่มี ันที่ป็นคีย์)
    public ArrayList combineList(chooseExpense listType ,chooseExpense listExpen){
        list.add(listType);
        list.add(listExpen);
        return list;
    }

    //business ligics
}
