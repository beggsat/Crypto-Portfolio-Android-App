package com.example.zching.cointrader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class comparePage extends AppCompatActivity {

    private Button bitcoinCompareButton;
    private Button ethereumCompareButton;
    private Button litecoinCompareButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_page);
        compareClick();
        compareClick2();
        compareClick3();
    }

    private void compareClick(){
        bitcoinCompareButton = findViewById(R.id.compare1);
        bitcoinCompareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //creates the intent to go to the bitcoin page from the home page.
                Intent compare = new Intent(comparePage.this,bitcoinCompare.class);

                //starts the intent to go to the other page which is the bitcoin stats page

                startActivity(compare);

            }
        });

    }

    private void compareClick3(){
        ethereumCompareButton = findViewById(R.id.compare3);
        ethereumCompareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //creates the intent to go to the bitcoin page from the home page.
                Intent compare = new Intent(comparePage.this,ethereumCompare.class);

                //starts the intent to go to the other page which is the bitcoin stats page

                startActivity(compare);

            }
        });

    }

    private void compareClick2(){
        litecoinCompareButton = findViewById(R.id.compare2);
        litecoinCompareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //creates the intent to go to the bitcoin page from the home page.
                Intent compare = new Intent(comparePage.this,litecoinCompare.class);

                //starts the intent to go to the other page which is the bitcoin stats page

                startActivity(compare);

            }
        });

    }
}
