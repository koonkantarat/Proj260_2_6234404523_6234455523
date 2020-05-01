package com.example.ohmycost;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.HashMap;

public class graphmonth extends AppCompatActivity {

    DBHelper DB;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphmonth);
        DB = new DBHelper(this);
        btnBack = findViewById(R.id.btnBack);
        setupPieChart();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(graphmonth.this,graph.class);
                startActivity(intent1);
            }
        });
    }

    public void setupPieChart(){
        String month = getIntent().getExtras().getString("month");
        HashMap<String, Integer> dataMonth=GetDataMonth(month);
        String[] type=new String[dataMonth.size()];
        int[] amount=new int[dataMonth.size()];
        int j=0;
        for (HashMap.Entry<String, Integer> entry : dataMonth.entrySet()) {
            type[j]=entry.getKey();
            amount[j]=entry.getValue();
            j++;
        }

        ArrayList<PieEntry> entries=new ArrayList<>();
        for(int i=0;i<type.length;i++){
            entries.add(new PieEntry(amount[i], type[i]));
        }

        PieDataSet dataset = new PieDataSet(entries,": Type");
        dataset.setSelectionShift(10);
        dataset.setValueTextSize(14);
        dataset.setFormSize(14);
        dataset.setColors(ColorTemplate.LIBERTY_COLORS);

        PieData data = new PieData(dataset);
        PieChart chart = findViewById(R.id.pie_Chart);
        chart.setData(data);
        chart.animateY(3000);
        chart.setHoleRadius(30);
        chart.setTransparentCircleRadius(40);
        chart.getDescription().setText("");

        dataset.setValueFormatter(new PercentFormatter(chart));
        chart.setUsePercentValues(true);
    }


    public HashMap<String,Integer> GetDataMonth(String month) {
        Cursor data = DB.getDataMonth(month);
        HashMap<String, Integer> dataMonth = new HashMap<>();
        while(data.moveToNext()){
            String type=data.getString(0);
            int amount=data.getInt(1);
            if(dataMonth.containsKey(type)){
                int oldAmount=dataMonth.get(type);
                dataMonth.remove(type);
                dataMonth.put(type,oldAmount+amount);
            }
            else
                dataMonth.put(type,amount);
        }
        return dataMonth;
    }

}
