package com.example.zching.cointrader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;


// source for image used on this activity https://www.coindesk.com/price/

public class Bitcoin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitcoin);
    }

    // On click method for INFO button displayed on xml
    public void bitcoinHistoryClicked(View v) {
        Intent i = new Intent(v.getContext(), BitcoinHistory.class);

        this.startActivity(i);
    }


}
