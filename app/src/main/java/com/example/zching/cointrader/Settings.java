package com.example.zching.cointrader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

import java.io.Serializable;

public class Settings extends AppCompatActivity implements Serializable{

    private final int MY_REQUEST = 1;

    // Coin variables to be received from whatever screen clicked settings
    private Coin coinBTC;
    private Coin coinLTC;
    private Coin coinETH;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        // Initialize coin variables from other screen
        coinBTC = (Coin) getIntent().getSerializableExtra("bitcoin");
        coinLTC = (Coin) getIntent().getSerializableExtra("litecoin");
        coinETH = (Coin) getIntent().getSerializableExtra("ethereum");


    }

    // On click for home button
    public void sHomeClicked(View hc) {
        Intent i = new Intent(hc.getContext(), HomeScreen.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("bitcoin", coinBTC);
        bundle.putSerializable("litecoin", coinLTC);
        bundle.putSerializable("ethereum", coinETH);
        i.putExtras(bundle);
        setResult(RESULT_OK, i);
        finish();
    }

    // On click for portfolio button
    public void sPortfolioClicked(View pc) {
        Intent i = new Intent(pc.getContext(), Portfolio.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("bitcoin", coinBTC);
        bundle.putSerializable("litecoin", coinLTC);
        bundle.putSerializable("ethereum", coinETH);
        i.putExtras(bundle);
        setResult(RESULT_OK, i);
        finish();
    }

    // On click for settings button, should do nothing right now
    public void sSettingsClicked(View sc) {

    }

    // Describe update function, include comment about how it will change with live data implementation
    public void updateClicked(View uc) {
        Intent i = new Intent(uc.getContext(), UpdateScreen.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("bitcoin", coinBTC);
        bundle.putSerializable("litecoin", coinLTC);
        bundle.putSerializable("ethereum", coinETH);
        i.putExtras(bundle);
        this.startActivityForResult(i, MY_REQUEST);
    }

    // Result will be from updateScreen finishing its function
    // Need to update whatever coin(s) changed during the update to be passed around the app whenever something else is called
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            if (resultCode == RESULT_OK && requestCode == MY_REQUEST) {
                String result = data.getExtras().getString("returnkey");

                if (result.equals("1")) { // user updated BTC
                    coinBTC = (Coin) data.getSerializableExtra("updatedCoin");
                }
                else if (result.equals("2")) { // user updated LTC
                    coinLTC = (Coin) data.getSerializableExtra("updatedCoin");
                }
                else if (result.equals("3")) { // user updated ETH
                    coinETH = (Coin) data.getSerializableExtra("updatedCoin");
                }
                Toast.makeText(this, "Wrong Code", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
