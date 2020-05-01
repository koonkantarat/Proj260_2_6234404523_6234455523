package com.example.ohmycost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class graphcomparemonth extends AppCompatActivity {
    DBHelper DB;
    BarChart mChart;
    Button btn_Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphcomparemonth);
        DB = new DBHelper(this);
        btn_Back = findViewById(R.id.btn_back);
        mChart=findViewById(R.id.bar_chart);

        String monthSelect = getIntent().getExtras().getString("month");
        String[] monthList = monthSelect.split(" ");
        String[] month = new String[monthList.length];
        int[] expense = new int[monthList.length];
        for(int i=0;i<monthList.length;i++){
            String mSelect = monthList[i];
            month [i] = mSelect;
            expense[i] = getTotalMonth(mSelect);
        }

        ArrayList<BarEntry> data = new ArrayList<>();
        for(int i=0;i<expense.length;i++){
            data.add(new BarEntry(i,expense[i]));
        }
        BarDataSet barDataSet = new BarDataSet(data,"month");
        barDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData=new BarData(barDataSet);

        mChart.setFitBars(true);
        mChart.setData(barData);
        mChart.getDescription().setText("");
        mChart.animateXY(3000,5000);

        YAxis leftAxis = mChart.getAxisRight();
        leftAxis.setEnabled(false);
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(true);
        leftAxis.setSpaceTop(35f);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(month));
        xAxis.setTextSize(14);
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        mChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        mChart.getXAxis().setLabelRotationAngle(45);

        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(graphcomparemonth.this,graph.class);
                startActivity(intent1);
            }
        });
    }

    public Integer getTotalMonth(String month) {
        Cursor data = DB.getTotalMonth(month);
        ArrayList<Integer> listData = new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getInt(2));
        }
        int total = 0;
        for(int i=0;i<listData.size();i++){
            total= total + listData.get(i);
        }
        return total;
    }

}
