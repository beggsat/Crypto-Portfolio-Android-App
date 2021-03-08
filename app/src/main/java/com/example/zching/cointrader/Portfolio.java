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

public class Portfolio extends AppCompatActivity implements Serializable{

    private final int MY_REQUEST = 1;
    private final int SETTINGS_REQUEST = 2;
    private final int SELL_REQUEST = 3;
    private Coin coin1;
    private TextView coinName1;
    private TextView coinOwned1;
    private TextView coinPerformance1;
    private TextView coinValue1;
    private Coin coin2;
    private TextView coinName2;
    private TextView coinOwned2;
    private TextView coinPerformance2;
    private TextView coinValue2;
    private TextView walletValue;
    private Double totalValue; // Used to get and test the changes of the large number displayed on portfolio screen for total wallet value
    private SharedPreferences sharedPref; //shared pref file

    public Portfolio() {
        Coin coin1 = new Coin();
        Coin coin2 = new Coin();
        totalValue = 0.0;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio_screen);
        poundConversion();
        euroConversion();
        yenConversion();
        compareClick();

        // Initialize coin objects
        coin1 = (Coin) getIntent().getSerializableExtra("bitcoin");
        coin2 = (Coin) getIntent().getSerializableExtra("ethereum");

        // Get coin 1's textViews
        coinName1 = findViewById(R.id.coin1Name);
        coinOwned1 = findViewById(R.id.coin1Owned);
        coinPerformance1 = findViewById(R.id.coin1Performance);
        coinValue1 = findViewById(R.id.coin1Value);

        // Get coin 2's textViews
        coinName2 = findViewById(R.id.coin2Name);
        coinOwned2 = findViewById(R.id.coin2Owned);
        coinPerformance2 = findViewById(R.id.coin2Performance);
        coinValue2 = findViewById(R.id.coin2Value);

        // Get walletValue textView to set later
        walletValue = findViewById(R.id.walletValue);


        populatePortfolio();

        String MY_PREFS_NAME = "coinValues";
        sharedPref = getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE); //Getting the data from the shared preference file

        String restoredText = sharedPref.getString("text", null);  //validating that the file has data
        if (restoredText != null) {
            double storedCoin1AmountOwned = getDouble(sharedPref,"coin1AmountOwned", 0.005); //0.005 is the default value.
            double storedCoin2AmountOwned = getDouble(sharedPref,"coin2AmountOwned", 0.051); //0 is the default value.
            coin1.setAmountOwned(storedCoin1AmountOwned);
            coin2.setAmountOwned(storedCoin2AmountOwned);
        }



    }

    // Hard coded coin info to mock wallet's display
    private void populatePortfolio() {
//        coin1.setCoinName("Bitcoin");
//        coin1.setAmountOwned(0.005);
//        coin1.setCoinValue(100);
        coin1.setValueOwned(coin1.getCoinValue(), coin1.getAmountOwned());
        String placehold = String.format("%.2f", coin1.getValueOwned());
        String valueString1 = '$' + placehold;
        double placeholdComp = Double.parseDouble(placehold);
        setBitcoinComp(placeholdComp);


//        coin2.setCoinName("Ethereum");
//        coin2.setAmountOwned(0.051);
//        coin2.setCoinValue(50);
        coin2.setValueOwned(coin2.getCoinValue(), coin2.getAmountOwned());
        String placehold2 = String.format("%.2f", coin2.getValueOwned());
        String valueString2 = '$' + placehold2;
        double placeholdComp2 = Double.parseDouble(placehold2);
        setEthereumComp(placeholdComp2);


        coinName1.setText(coin1.getCoinName());
        coinOwned1.setText(Double.toString(coin1.getAmountOwned()));
        coinPerformance1.setText("+5%");
        coinValue1.setText(valueString1);

        coinName2.setText(coin2.getCoinName());
        coinOwned2.setText(Double.toString(coin2.getAmountOwned()));
        coinPerformance2.setText("-0.03%");
        coinValue2.setText(valueString2);

        double total = coin1.getValueOwned() + coin2.getValueOwned();
        String placehold3 = String.format("%.2f", total);
        String placehold4 = '$' + placehold3;
        walletValue.setText(placehold4);

    }

// --Commented out by Inspection START (12/5/2018 2:12 AM):
//    // Populate method to help with testing, creates the coin objects but ignores the textViews
//    public void populateTester() {
//        coin1.setCoinName("Bitcoin");
//        coin1.setAmountOwned(0.005);
//        coin1.setCoinValue(100);
//        coin1.setValueOwned(coin1.getCoinValue(), coin1.getAmountOwned());
//        String placehold = String.format("%.2f", coin1.getValueOwned());
//        String valueString1 = '$' + placehold;
//
//        coin2.setCoinName("Ethereum");
//        coin2.setAmountOwned(0.051);
//        coin2.setCoinValue(50);
//        coin2.setValueOwned(coin2.getCoinValue(), coin2.getAmountOwned());
//        String placehold2 = String.format("%.2f", coin2.getValueOwned());
//        String valueString2 = '$' + placehold2;
//
//        double total = coin1.getValueOwned() + coin2.getValueOwned();
//        totalValue = total;
//        String placehold3 = String.format("%.2f", total);
//        String placehold4 = '$' + placehold3;
//    }
// --Commented out by Inspection STOP (12/5/2018 2:12 AM)

    private void compareClick(){
        Button compareClick = findViewById(R.id.compareButton);
        compareClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //creates the intent to go to the bitcoin page from the home page.
                Intent compare = new Intent(Portfolio.this,comparePage.class);

                //starts the intent to go to the other page which is the bitcoin stats page

                startActivity(compare);

            }
        });

    }

    // same method as right below; converts dollars to pound. Check below for more details.
    private void poundConversion(){
        Button pounds = findViewById(R.id.pounds);
        pounds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = walletValue.getText().toString(); //get the wallet amount
                String value2 = value.substring(1);
                double desiredValue = Double.parseDouble(value2);
                double pound = desiredValue * .76;
                String converted = Double.toString(pound);
                Toast.makeText(getApplicationContext(), "£" + converted,
                        Toast.LENGTH_LONG).show();

            }
        });

    }

    private void yenConversion(){
        Button yen = findViewById(R.id.yenCurrency);
        yen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = walletValue.getText().toString(); //get the wallet amount
                String value2 = value.substring(1);
                double desiredValue = Double.parseDouble(value2);
                double yen = desiredValue * 112.5;
                String converted = Double.toString(yen);
                Toast.makeText(getApplicationContext(), "¥" + converted,
                        Toast.LENGTH_LONG).show();

            }
        });

    }

    // method that's going to be used to test the yenConversion formula in the test cases
    public static double yenFormula(double input ){
        return input * 112.5;

    }
    // method that's going to be used to test the poundConversion formula in the test cases

    public static double poundFormula(double input ){
        return input * .76;

    }
    // method that's going to be used to test the euroConversion formula in the test cases

    public static double euroFormula(double input ){
        return input * .87;

    }

    // converts the dollar amount in the wallet to a foreign currency to view
    private void euroConversion(){
        Button euros = findViewById(R.id.euros);
        euros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = walletValue.getText().toString(); // gets the wallet amount to a string
                String value2 = value.substring(1); // takes out the dollar sign
                double desiredValue = Double.parseDouble(value2); // converts the amount to a double
                double pound = desiredValue * .87; // the double is multiplied by the exchange rate
                String converted = Double.toString(pound); //the converted amount is changed back to a string
                Toast.makeText(getApplicationContext(), "€" + converted,
                        Toast.LENGTH_LONG).show();

            }
        });

    }

    // On click for home button
    public void pHomeClicked(View hc) {
        Intent i = new Intent(hc.getContext(), HomeScreen.class);
        startActivity(i);
    }

    // On click for portfolio button, should do nothing right now
    public void pPortfolioClicked(View pc) {

    }

    // On click for settings button
    public void pSettingsClicked(View sc) {
        Intent i = new Intent(sc.getContext(), Settings.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("bitcoin", coin1);
        bundle.putSerializable("ethereum", coin2);
        i.putExtras(bundle);
        this.startActivityForResult(i, SETTINGS_REQUEST);
    }

    //for adding a purchase (can change the name later)
    public void pAddClicked(View view) {
        Intent i = new Intent(this, PurchaseScreen.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("bitcoin", coin1);
        bundle.putSerializable("etherium", coin2);
        i.putExtras(bundle);
        this.startActivityForResult(i, MY_REQUEST);
    }

    public void pSubtractClicked(View view) {
        Intent i = new Intent(this, SubtractScreen.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("bitcoin", coin1);
        bundle.putSerializable("etherium", coin2);
        i.putExtras(bundle);
        this.startActivityForResult(i, SELL_REQUEST);
    }

    public void pCompClicked(View comp){
        Intent i = new Intent(comp.getContext(), Composition.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("bitcoin", btcComp);
        bundle.putSerializable("etherium", ethComp);

        i.putExtras(bundle);

        startActivity(i);
    }

    public void pResetClicked(View comp){

        coin1.setAmountOwned(0.0);
        coin2.setAmountOwned(0.0);

        updatePortfolio();


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            // Called for purchases, linked to MY_REQUEST
            if (resultCode == RESULT_OK && requestCode == MY_REQUEST) {
                String result = data.getExtras().getString("returnkey");


                if (result.equals("1")) {
                    coin1 = (Coin) data.getSerializableExtra("updatedCoin");

                }
                else if (result.equals("2")) {
                    coin2 = (Coin) data.getSerializableExtra("updatedCoin");

                }

                updatePortfolio();

            }
            else if (resultCode == RESULT_OK && requestCode == SETTINGS_REQUEST) {
                coin1 = (Coin) data.getSerializableExtra("bitcoin");
                coin2 = (Coin) data.getSerializableExtra("ethereum");
                updatePortfolio();
            }
            else if (resultCode == RESULT_OK && requestCode == SELL_REQUEST){
                String result = data.getExtras().getString("returnkey");


                if (result.equals("1")) {
                    coin1 = (Coin) data.getSerializableExtra("updatedCoin");

                }
                else if (result.equals("2")) {
                    coin2 = (Coin) data.getSerializableExtra("updatedCoin");

                }

                updatePortfolio();


            }
        }
    }

    private void updatePortfolio() {
        coin1.setValueOwned(coin1.getCoinValue(), coin1.getAmountOwned());
        String placehold = String.format("%.2f", coin1.getValueOwned());
        String valueString1 = '$' + placehold;
        double placeholdComp = Double.parseDouble(placehold);
        setBitcoinComp(placeholdComp);

        coin2.setValueOwned(coin2.getCoinValue(), coin2.getAmountOwned());
        String placehold2 = String.format("%.2f", coin2.getValueOwned());
        String valueString2 = '$' + placehold2;
        double placeholdComp2 = Double.parseDouble(placehold);
        setBitcoinComp(placeholdComp2);

        coinOwned1.setText(Double.toString(coin1.getAmountOwned()));
     //   coinPerformance1.setText("+5%");
        coinValue1.setText(valueString1);

        coinOwned2.setText(Double.toString(coin2.getAmountOwned()));
       // coinPerformance2.setText("-0.0 3%");
        coinValue2.setText(valueString2);

        double total = coin1.getValueOwned() + coin2.getValueOwned();
        String ndew = '$' + Double.toString(total);
        walletValue.setText(ndew);

        SharedPreferences.Editor editor = sharedPref.edit(); //editing the shared pref file called coinValues
        editor.putString("text", "valid"); //string to show that file is valid
        putDouble(editor, "coin1AmountOwned", coin1.getAmountOwned());                       //putting in the values
        putDouble(editor,"coin2AmountOwned", coin2.getAmountOwned());
        editor.apply();  //updating the file
    }


    private void putDouble(final SharedPreferences.Editor edit, final String key, final double value) {   //solution from stackoverflow to be able to store doubles in shared pref file
        edit.putLong(key, Double.doubleToRawLongBits(value));
    }
    private double getDouble(final SharedPreferences prefs, final String key, final double defaultValue) {   //solution from stackoverflow to be able to retrieve doubles in shared pref file
        return Double.longBitsToDouble(prefs.getLong(key, Double.doubleToLongBits(defaultValue)));
    }

// --Commented out by Inspection START (12/5/2018 2:12 AM):
//    // Testing helper method
//    public void updatePortfolioTester() {
//        double total = coin1.getValueOwned() + coin2.getValueOwned();
//        String ndew = '$' + Double.toString(total);
//        totalValue = total;
//    }
// --Commented out by Inspection STOP (12/5/2018 2:12 AM)

// --Commented out by Inspection START (12/5/2018 2:12 AM):
//    // Basic get methods for testing verifications
//    public Coin getCoin1() {
//        return coin1;
//    }
// --Commented out by Inspection STOP (12/5/2018 2:12 AM)
// --Commented out by Inspection START (12/5/2018 2:12 AM):
//    public Coin getCoin2() {
//        return coin2;
//    }
// --Commented out by Inspection STOP (12/5/2018 2:12 AM)
    public Double getTotalValue() {
        return totalValue;
    }
    // Method only used for testing
    public void setTotalValue(double val) {
        this.totalValue = val;
    }


    private double btcComp;
    private void setBitcoinComp(Double d)
    {
        btcComp = d;
    }
// --Commented out by Inspection START (12/5/2018 2:12 AM):
//    public double getBitcoinComp()
//    {
//        return btcComp;
//    }
// --Commented out by Inspection STOP (12/5/2018 2:12 AM)

    private double ethComp;
    private void setEthereumComp(Double d)
    {
        ethComp = d;
    }
// --Commented out by Inspection START (12/5/2018 2:12 AM):
//    public double getEthereumComp()
//    {
//        return ethComp;
//    }
// --Commented out by Inspection STOP (12/5/2018 2:12 AM)


}
