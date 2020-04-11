package com.example.ohmycost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.CalendarView;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private double total;
    private double expense;
    private double total_month;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button graph = (Button)findViewById(R.id.butt_graph);
        Button add = (Button)findViewById(R.id.add);
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
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent page_add = new Intent(MainActivity.this,chooseExpense.class);
                startActivity(page_add);
            }
        });
        CalendarView calendar = (CalendarView)findViewById(R.id.calendarView);
        final TextView theDate = (TextView)findViewById(R.id.date);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth +"/"+month+"/"+year;
                theDate.setText(date);
                total.setText("Total = "+Double.toString(getTotal())); //ไม่แน่ใจว่าควรอยู๋ตรงไหน
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

    public double getTotalMonth(){
        total_month = total_month + expense;
        return total_month; //ค่าใช้จ่ายรายเดือน
    }

    public double getTotal(){
        total = total + this.expense;
        return total; //ค่าใช้จ่ายรายวัน
    }
}
