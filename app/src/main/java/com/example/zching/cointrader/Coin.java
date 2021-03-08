package com.example.zching.cointrader;

import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;

public class Coin extends AppCompatActivity implements Serializable {
    /*
        Coin's name held in string coinName
        Value of 1 coin in terms of USD held in int value
        How much user owns of coin is held in int amountOwned
        valueOwned relies on function to calculate amountOwned value in $
     */
    private String coinName;
    private int value;
    double amountOwned; // in terms of the coin, ex: 0.041
    private double valueOwned;

    public Coin() {
        coinName = "";
        value = 0;
        amountOwned = 0.0;
        valueOwned = 0.0;
    }

    public Coin(String name, int val, double bought) {
        this.coinName = name;
        this.value = val;
        this.amountOwned = bought;
        this.valueOwned = valueCalculator(this.value, this.amountOwned);
    }

    // Basic calculator to convert amount of coin owned into USD$
    public double valueCalculator(int val, double owned) {
        return val * owned;
    }

    // Get methods for coin's information
    public String getCoinName() {
        return this.coinName;
    }
    public int getCoinValue() {
        return this.value;
    }
    public double getAmountOwned() {
        return this.amountOwned;
    }
    public double getValueOwned() {
        return this.valueOwned;
    }

    // Set methods for coin's information
    public void setCoinName(String name) {
        this.coinName = name;
    }
    public void setCoinValue(int cval) {
        if (cval >= 0) {
            this.value = cval;
        }
        // Error, value can't be negative, retry

    }
    public void setAmountOwned(double amount) {
        if (amount >= 0.0) {
            this.amountOwned = amount;
        }
        // Error

    }
    public void setValueOwned(int val, double owned) {
        double conversion = valueCalculator(val, owned);
        if (conversion >= 0.0) {
            this.valueOwned = conversion;
        }
        // Error

    }
}
