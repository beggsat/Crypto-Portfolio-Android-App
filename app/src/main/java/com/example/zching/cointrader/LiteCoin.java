package com.example.zching.cointrader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

// source for image used on this activity : https://www.coindesk.com/market-center/litecoin/

public class LiteCoin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lite_coin);
    }


    // On click method for INFO button displayed on xml
    public void litecoinHistoryClicked(View v) {
        Intent i = new Intent(v.getContext(), LitecoinHistory.class);

        startActivity(i);
    }
}
