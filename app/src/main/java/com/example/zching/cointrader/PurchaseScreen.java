package com.example.zching.cointrader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;

public class PurchaseScreen extends AppCompatActivity implements Serializable , AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private static final String[] paths = {"Bitcoin", "Etherium"};
    private int choice = -1;
    private Coin coinBTC;
    private Coin coinETH;
    private Coin tempCoin = new Coin();
    private TextView coinName;
    private EditText coinPrice;
    private EditText amountPurchased;

    public PurchaseScreen() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_screen);
        setTitle("Add a new purchase");

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        coinBTC = (Coin) getIntent().getSerializableExtra("bitcoin");
        coinETH = (Coin) getIntent().getSerializableExtra("etherium");

        coinName = findViewById(R.id.coinSelect);
        coinPrice = findViewById(R.id.coinPrice);
        amountPurchased = findViewById(R.id.amountPurchased);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(PurchaseScreen.this, android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }



    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                coinName.setText(coinBTC.getCoinName());
                coinPrice.setText(Integer.toString(coinBTC.getCoinValue()), TextView.BufferType.EDITABLE);
                tempCoin = coinBTC;
                choice = 1;
                break;
            case 1:
                coinName.setText(coinETH.getCoinName());
                coinPrice.setText(Integer.toString(coinETH.getCoinValue()), TextView.BufferType.EDITABLE);
                tempCoin = coinETH;
                choice = 2;
                break;

                default: //do nothing

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        coinName.setText("No Coin Selected");
    }

    public void pSubmitClicked(View view) {
        double amount = Double.parseDouble(amountPurchased.getText().toString());    //getting a double from the edittext box
        tempCoin.setAmountOwned(tempCoin.getAmountOwned() + amount); //setting the coin

        tempCoin.setValueOwned(tempCoin.getCoinValue(), tempCoin.getAmountOwned());

        Intent intent = new Intent();


        if (choice == 1){
            intent.putExtra("returnkey", "1");
        }
        else if (choice == 2){
            intent.putExtra("returnkey", "2");
        }
        
        Bundle bundle = new Bundle();
        bundle.putSerializable("updatedCoin", tempCoin);

        intent.putExtras(bundle);

        setResult(RESULT_OK, intent);
        finish();
    }

    public void pCancelClicked(View view) {

        Intent intent = new Intent();
        setResult(1, intent);
        finish();
    }
}
