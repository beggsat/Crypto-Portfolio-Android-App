package com.example.zching.cointrader;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class UserStoryFiveTests {


    /*User Story:
    As a user,
    I want to be able to subtract coins from my wallet
    so that I can reflect selling the coins in my wallet.

    Scenario 1:
    Given I am on the portfolio page
    When I subtract coins from my wallet
    Then the values on the portfolio page should reflect that.

    Scenario 2:
    Given I subtract coins from my wallet
    When I try to subtract more than I own
    Then the values on the portfolio page should be unchanged

    Scenario 3:
    Given I am on the subtract page
    When I click the submit button
    Then I should be taken to the portfolio page and not the home screen*/



    // Scenario One
    @Test
    public void testValue() {
        Coin tester = new Coin();
        Double testValue = 128.0;
        tester.setValueOwned(128, 1.0);
        Double secondTestValue = tester.getValueOwned();
        assertEquals(testValue, secondTestValue);
    }

    // Test that the value of coin owned exists which has nothing
    @Test
    public void testEmptyValue() {
        Coin tester = new Coin();
        Double testValue = 0.0;
        tester.setValueOwned(99844, 0.0);
        Double secondTestValue = tester.getValueOwned();
        assertEquals(testValue, secondTestValue);
    }

    // Test that the coin's currency value has been calculated based on a whole coin's value and the amount owned
    @Test
    public void coinValueContribution() {
        Coin coin = new Coin();
        Double valueOne = coin.valueCalculator(100, 0.002);
        assertEquals(valueOne, (Double) 0.2);
        Double valueTwo = coin.valueCalculator(25, 0);
        assertEquals(valueTwo, (Double) 0.0);
    }


    //Scenario Two
    @Test
    public void testSetValue() {
        Coin tester = new Coin(); // blank coin with no values yet
        Double testValue = 90.0;
        tester.setValueOwned(120, 0.75);
        Double secondTestValue = tester.getValueOwned();
        assertEquals(testValue, secondTestValue);
    }

    // Test manipulation of wallet value
    @Test
    public void testGetWalletValue() {
        Portfolio myWallet = new Portfolio();
        Double initialValue = myWallet.getTotalValue();
        assertEquals((Double) 0.0, initialValue); // brand new portfolio, should not have a value
        myWallet.setTotalValue(28000.00);
        Double updatedValue = myWallet.getTotalValue();
        assertEquals((Double) 28000.00, updatedValue);

    }

    @Test
    public void testValueCalculator() {
        Coin coin = new Coin();
        Double valueOne = coin.valueCalculator(10, 0.002);
        assertEquals(valueOne, (Double) 0.02);
        Double valueTwo = coin.valueCalculator(10000, 0);
        assertEquals(valueTwo, (Double) 0.0);
    }


    //Scenario 3


    @Test
    public void testCoinSubtraction() {
        Portfolio myWallet = new Portfolio();
        Coin one = new Coin();
        one.setAmountOwned(20);
        Double originalOwned = one.getAmountOwned();
        Double userInput = 3.5;
        one.setAmountOwned(originalOwned - userInput);
        Double updatedOwned = one.getAmountOwned();
        assertNotEquals(originalOwned, updatedOwned);

    }
}