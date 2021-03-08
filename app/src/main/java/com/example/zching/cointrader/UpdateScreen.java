package com.example.zching.cointrader;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import java.io.Serializable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class UpdateScreen extends AppCompatActivity implements Serializable , AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private static final String[] paths = {"Bitcoin", "Litecoin", "Ethereum"};
    private int choice = -1;
    private Coin coinBTC;
    private Coin coinLTC;
    private Coin coinETH;
    private Coin placehold = new Coin(); // Used later for transferring values
    private TextView coinName;
    private EditText newCoinValue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_screen);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        // Get coin objects from bundle
        coinBTC = (Coin) getIntent().getSerializableExtra("bitcoin");
        coinLTC = (Coin) getIntent().getSerializableExtra("litecoin");
        coinETH = (Coin) getIntent().getSerializableExtra("ethereum");

        // Get textView objects from view
        coinName = findViewById(R.id.coinName);
        newCoinValue = findViewById(R.id.inputNewValue);

        // Prepare spinner
        spinner = findViewById(R.id.updateSpinner);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(UpdateScreen.this, android.R.layout.simple_spinner_item, paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    // Switch case for the available items from the spinner
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0: // bitcoin case
                coinName.setText(coinBTC.getCoinName());
                placehold = coinBTC;
                choice = 1;
                break;
            case 1: // litecoin case
                coinName.setText(coinLTC.getCoinName());
                placehold = coinLTC;
                choice = 2;
                break;
            case 2: // ethereum case
                coinName.setText(coinETH.getCoinName());
                placehold = coinETH;
                choice = 3;
                break;

            default: // nothing selected yet
                coinName.setText("No coin selected");
        }
    }

    // Default textView display when nothing selected
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        coinName.setText("No coin selected");
    }


    // On click method for submit button
    // Gathers the changes we made to one of the coin's trade in values and sends it back to settings for the rest of the app to be updated
    public void submitClicked(View sc) {
        int newVal = Integer.parseInt(newCoinValue.getText().toString()); // get int entered from user input in edittext
        placehold.setCoinValue(newVal); // update the coins value
        placehold.setValueOwned(placehold.getCoinValue(), placehold.getAmountOwned()); // update how much owned with new value

        Intent i = new Intent();

        // Putting an extra into the intent so settings screen knows what we chose
        if (choice == 1) {
            i.putExtra("returnkey", "1"); // BTC choice
        }
        else if (choice == 2) {
            i.putExtra("returnkey", "2"); // LTC choice
        }
        else if (choice == 3) {
            i.putExtra("returnkey", "3"); // ETH choice
        }

        Bundle bundle = new Bundle();
        bundle.putSerializable("updatedCoin", placehold);
        i.putExtras(bundle);

        setResult(RESULT_OK, i);
        finish();
    }


    // On click method for the back button when user wants to cancel his/her selection for update
    // Sends back to settings screen with nothing changed
    public void backClicked(View bc) {
        Intent i = new Intent();
        setResult(1, i);
        finish();
    }

// --Commented out by Inspection START (12/5/2018 2:12 AM):
//    // On click method for refresh button
//    // Will be implemented later when live data is put into the app
//    // Refresh button function is to retrieve the live data from the source and update all of the values necessary for each coin
//    public void refreshClicked(View rc) {
//
//    }
// --Commented out by Inspection STOP (12/5/2018 2:12 AM)
}

