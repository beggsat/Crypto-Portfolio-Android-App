package com.example.zching.cointrader;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity implements Serializable{

    private final int MY_REQUEST = 1;

    private Coin coin1;
    private TextView coinName1;
    private TextView coinValue1;
    private TextView coinOwned1;
    private Coin coin2;
    private TextView coinName2;
    private TextView coinValue2;
    private TextView coinOwned2;
    private Coin coin3;
    private TextView coinName3;
    private TextView coinValue3;
    private TextView coinOwned3;
    private Button bitcoinButton;
    private Button litecoinButton;
    private Button ethButton;
    private final String MY_PREFS_NAME = "coinValues";
    private SharedPreferences sharedPref; //shared pref file


    private ArrayList<Coin> coinList;

    private boolean openedYet = false;

    public HomeScreen() {
        coin1 = new Coin();
        coin2 = new Coin();
    }


    private void bitcoin(){
        bitcoinButton = findViewById(R.id.bitcoinButton);
        bitcoinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //creates the intent to go to the bitcoin page from the home page.
                Intent bitcoin = new Intent(HomeScreen.this,Bitcoin.class);

                //starts the intent to go to the other page which is the bitcoin stats page

                startActivity(bitcoin);

            }
        });

    }

    private void litecoinClick(){
        litecoinButton = findViewById(R.id.litecoinButton);
        litecoinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //creates the intent to go to the bitcoin page from the home page.
                Intent litecoinIntent = new Intent(HomeScreen.this,LiteCoin.class);

                //starts the intent to go to the other page which is the bitcoin stats page

                startActivity(litecoinIntent);

            }
        });

    }

    private void ethClick(){
        ethButton = findViewById(R.id.ethButton);
        ethButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //creates the intent to go to the bitcoin page from the home page.
                Intent ethIntent = new Intent(HomeScreen.this,Ethereum.class);

                //starts the intent to go to the other page which is the bitcoin stats page

                startActivity(ethIntent);

            }
        });

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        bitcoin();
        litecoinClick();
        ethClick();

        // Initialize coin objects
        coin1 = new Coin(); // blank new coin object
        coin2 = new Coin(); // blank new coin object
        coin3 = new Coin("Ethereum", 50, 0.051); // tests the parameterized constructor

        // Initialize arrayList
        coinList = new ArrayList<Coin>();
        coinList.add(coin1);
        coinList.add(coin2);
        coinList.add(coin3);

        // Get coin 1's textViews
        coinName1 = findViewById(R.id.coin1Name);
        coinValue1 = findViewById(R.id.coin1Value);
        coinOwned1 = findViewById(R.id.coin1Owned);

        // Get coin 2's textViews
        coinName2 = findViewById(R.id.coin2Name);
        coinValue2 = findViewById(R.id.coin2Value);
        coinOwned2 = findViewById(R.id.coin2Owned);

        // Get coin 3's textViews
        coinName3 = findViewById(R.id.coin3Name);
        coinValue3 = findViewById(R.id.coin3Value);
        coinOwned3 = findViewById(R.id.coin3Owned);


        // Checks if app has been opened before preventing remake of the data
        // If it has then get data withheld in the internal storage of app
        if (openedYet) {

        }
        else {
            populateHomeScreen();
        }

        sharedPref = getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE); //Getting the data from the shared preference file
        String restoredText = sharedPref.getString("text", null);  //validating that the file has data
        if (restoredText != null) {
            double storedCoin1AmountOwned = getDouble(sharedPref,"coin1AmountOwned", 0.005); //0.005 is the default value.
            double storedCoin2AmountOwned = getDouble(sharedPref,"coin2AmountOwned", 0.051); //0 is the default value.
            coin1.setAmountOwned(storedCoin1AmountOwned);
            coin3.setAmountOwned(storedCoin2AmountOwned); //coin3 is eth in the homescreen but stored as coin2 in portfolio
            updateHomeScreen();
        }
    }
    private double getDouble(final SharedPreferences prefs, final String key, final double defaultValue) {   //solution from stackoverflow to be able to retrieve doubles in shared pref file
        return Double.longBitsToDouble(prefs.getLong(key, Double.doubleToLongBits(defaultValue)));

    }

    // Hard coding the coin information into home screen
    // Runs all the object's setters to change the textView contents on screen
    private void populateHomeScreen() {

        coin1.setCoinName("Bitcoin");
        coin1.setCoinValue(100);
        coin1.setAmountOwned(0.005);

        coin2.setCoinName("litecoin");
        coin2.setCoinValue(20);
        coin2.setAmountOwned(0.0);

        coinName1.setText(coin1.getCoinName());
        coinValue1.setText(Integer.toString(coin1.getCoinValue()));
        coinOwned1.setText(Double.toString(coin1.getAmountOwned()));

        coinName2.setText(coin2.getCoinName());
        coinValue2.setText(Integer.toString(coin2.getCoinValue()));
        coinOwned2.setText(Double.toString(coin2.getAmountOwned()));

        coinName3.setText(coin3.getCoinName());
        coinValue3.setText(Integer.toString(coin3.getCoinValue()));
        coinOwned3.setText(Double.toString(coin3.getAmountOwned()));

        openedYet = true;
    }



    // On click method for home button, does nothing right now
    public void hHomeClicked(View hc) {
        updateHomeScreen();
    }

    // On click method for portfolio button
    public void hPortfolioClicked(View pc) {
        Intent i = new Intent(pc.getContext(), Portfolio.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("bitcoin", coin1);
        bundle.putSerializable("litecoin", coin2);
        bundle.putSerializable("ethereum", coin3);
        i.putExtras(bundle);
        this.startActivity(i);
    }

    // On click method for settings button
    // Sends values of the coins made from onCreate with a bundle to the settings screen to be modified
    public void hSettingsClicked(View sc) {
        Intent i = new Intent(this, Settings.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("bitcoin", coin1);
        bundle.putSerializable("litecoin", coin2);
        bundle.putSerializable("ethereum", coin3);
        i.putExtras(bundle);
        this.startActivityForResult(i, MY_REQUEST);
    }


    // On activity result from settings screen (because this is only one that calls for result as of now)
    // If correct codes are received, should update the respective coin's value by getting serialized coin from settings screen
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            if (resultCode == RESULT_OK && requestCode == MY_REQUEST) {
                coin1 = (Coin) data.getSerializableExtra("bitcoin");
                coin2 = (Coin) data.getSerializableExtra("litecoin");
                coin3 = (Coin) data.getSerializableExtra("ethereum");

                Toast.makeText(this, "Wrong Code", Toast.LENGTH_SHORT).show();

                updateHomeScreen();
            }
        }
    }

    private void updateHomeScreen() {
        coinValue1.setText(Integer.toString(coin1.getCoinValue()));
        coinValue2.setText(Integer.toString(coin2.getCoinValue()));
        coinValue3.setText(Integer.toString(coin3.getCoinValue()));

        coinOwned1.setText(Double.toString(coin1.getAmountOwned()));
        coinOwned2.setText(Double.toString(coin2.getAmountOwned()));
        coinOwned3.setText(Double.toString(coin3.getAmountOwned()));
    }


    public void updateCoinTester(Coin coin, int newVal) {
        coin.setCoinValue(newVal);
    }

// --Commented out by Inspection START (12/5/2018 2:12 AM):
//    public boolean updateValueTester(Coin coin, int newVal) {
//        int initial = coin.getCoinValue();
//        coin.setCoinValue(newVal);
//        int second = coin.getCoinValue();
//        if (initial != second) {
//            return true;
//        }
//        else { return false; }
//    }
// --Commented out by Inspection STOP (12/5/2018 2:12 AM)

// --Commented out by Inspection START (12/5/2018 2:12 AM):
//    // Testing purpose methods
//    public Coin getCoin1() {
//        return coin1;
//    }
// --Commented out by Inspection STOP (12/5/2018 2:12 AM)
// --Commented out by Inspection START (12/5/2018 2:12 AM):
//    public Coin getCoin2() {
//        return coin2;
//    }
// --Commented out by Inspection STOP (12/5/2018 2:12 AM)
// --Commented out by Inspection START (12/5/2018 2:12 AM):
//    public Coin getCoin3() {
//        return coin3;
//    }
// --Commented out by Inspection STOP (12/5/2018 2:12 AM)
    public String getCoin1TextValue() {
        return coinValue1.getText().toString();
    }
    public String getCoin2TextValue() {
        return coinValue2.getText().toString();
    }
}
