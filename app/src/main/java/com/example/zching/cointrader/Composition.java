package com.example.zching.cointrader;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

import static com.github.mikephil.charting.components.Legend.LegendPosition.LEFT_OF_CHART;

public class Composition extends AppCompatActivity {

    private static final String TAG = "Composition";

    private double btcComp;
    private double ethComp;
    // --Commented out by Inspection (12/5/2018 2:12 AM):Portfolio portfolio = new Portfolio();
    //    private float[] yData = {64.1f, 25.3f};
    private final float[] yData = new float[2];

    private final String[] xData = {"Bitcoin", "Ethereum"};
    private PieChart pieChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composition);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        btcComp = (double) getIntent().getSerializableExtra("bitcoin");
        ethComp = (double) getIntent().getSerializableExtra("etherium");


        populateCoins();


        Log.d(TAG, "onCreate: starting to create chart");

        pieChart = findViewById(R.id.idPieChart);

        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Wallet Composition");
        pieChart.setCenterTextSize(10);
        // pieChart.setDrawEntryLabels(true);

        addDataSet();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d(TAG, "onValueSelected: Value select from  chart.");
                Log.d(TAG, "onValueSelected: " + e.toString());
                Log.d(TAG, "onValueSelected: " + h.toString());

                int pos1 = e.toString().indexOf("(sum):");
                String value = e.toString().substring(pos1 + 17);

                for(int i = 0; i < yData.length; i++){
                    if(yData[i] == Float.parseFloat(value)){
                        pos1 = i;
                        break;
                    }
                }
                String coin = xData[pos1];
                Toast.makeText(Composition.this, "Coin " + coin + "\n" + "Value: $" + value , Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    private void populateCoins()
    {
        yData[0] = (float)btcComp;
        yData[1] = (float)ethComp;
    }

    private void addDataSet(){
        Log.d(TAG, "addDataSet started");
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i <yData.length; i++){
            yEntrys.add(new PieEntry(yData[i], i));
        }

        for(int i = 1; i< xData.length; i++){
            xEntrys.add(xData[i]);
        }

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Wallet Composition");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colors to the dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(255, 153,0));
        colors.add(Color.rgb(114, 114, 116));


        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(LEFT_OF_CHART);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

}
