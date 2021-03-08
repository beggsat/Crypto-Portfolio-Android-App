package com.example.zching.cointrader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;


// source for image used on this activity : https://www.coindesk.com/ethereum-price/

public class Ethereum extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ethereum);
    }

    // On click method for INFO button displayed on xml
    public void ethereumHistoryClicked(View v) {
        Intent i = new Intent(v.getContext(), EthereumHistory.class);

        startActivity(i);
    }
}
