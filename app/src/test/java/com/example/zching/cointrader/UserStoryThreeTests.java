package com.example.zching.cointrader;

import org.junit.Test;

import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;
//
//import org.mockito.runners.MockitoJUnitRunner;
//import org.junit.runner.RunWith;


//@RunWith(MockitoJUnitRunner.class)

/*
    User Story 3 has three scenarios regarding the basic actions of the wallet/portfolio.
    Scenario 1: base portfolio screen shows the name of the coin and the information about what user owns
    Scenario 2: when a coin is successfully added to the wallet then the portfolio value is updated
    Scenario 3: when a coin is added the base portfolio screen should show an updated value of amount of given coin owned
 */

public class UserStoryThreeTests {

    // *** Scenario 1 Unit Tests ***
    /*
     Coins and their data are currently hard coded into the app because live data from an input has not been implemented.
     These tests are very basic because of this and will need updating when the data becomes more complex.
     These tests are testing our hard coded methods that create the coin objects and successfully assign information to them before we try
        to assign these values to a textView to be displayed.
     */

    // Tests that the coin is named
    @Test
    public void testValue() {
        Coin tester = new Coin();
        Double testValue = 50.0;
        tester.setValueOwned(50, 1.0);
        Double secondTestValue = tester.getValueOwned();
        assertEquals(testValue, secondTestValue);
    }

   // Test that the value of coin owned exists which has nothing
    @Test
    public void testEmptyValue() {
        Coin tester = new Coin();
        Double testValue = 0.0;
        tester.setValueOwned(50, 0.0);
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
    // *** END SCENARIO 1 UNIT TESTS ***


    // *** Scenario 2 Unit Tests ***

    @Test
    public void testSetValue() {
        Coin tester = new Coin(); // blank coin with no values yet
        Double testValue = 25.0;
        tester.setValueOwned(50, 0.5);
        Double secondTestValue = tester.getValueOwned();
        assertEquals(testValue, secondTestValue);
    }

    // Test manipulation of wallet value
    @Test
    public void testGetWalletValue() {
        Portfolio myWallet = new Portfolio();
        Double initialValue = myWallet.getTotalValue();
        assertEquals((Double) 0.0, initialValue); // brand new portfolio, should not have a value
        myWallet.setTotalValue(12000.00);
        Double updatedValue = myWallet.getTotalValue();
        assertEquals((Double) 12000.00, updatedValue);

    }

    @Test
    public void testValueCalculator() {
        Coin coin = new Coin();
        Double valueOne = coin.valueCalculator(10, 0.002);
        assertEquals(valueOne, (Double) 0.02);
        Double valueTwo = coin.valueCalculator(10000, 0);
        assertEquals(valueTwo, (Double) 0.0);
    }

//    @Test
//    public void coinAdded() {
//        Portfolio myWallet = new Portfolio(); // initialize coins
//        Intent i = new Intent(myWallet, PurchaseScreen.class);
//        PurchaseScreen purchaseScreen = new PurchaseScreen();
//        myWallet.populateTester(); // populate coins with base info to be compared to
//    }
//    @Test
//    public void testAddButton() {
//        Portfolio myWallet = mock(Portfolio.class);
//        assertNotNull(myWallet.findViewById(R.id.addButton));
//
//    }
    // *** END SCENARIO 2 UNIT TESTS ***

    // *** Scenario 3 Unit Tests ***

    // Test the ability of a coin's amount owned to be changed.
    // This is the base test that will be checked repeatedly when the add screen's functions are ran.
    /*@Test
    public void testCoinAddition() {
        Portfolio myWallet = new Portfolio();
        myWallet.populateTester();
        Coin one = myWallet.getCoin1();
        Double originalOwned = one.getAmountOwned();
        Double userInput = 10.4; // arbitrary number to imitate user inputting something on the add screen
        one.setAmountOwned(originalOwned + userInput);
        Double updatedOwned = one.getAmountOwned();
        assertNotEquals(originalOwned, updatedOwned);
    }

    // Testing the basic idea of spending some amount of coin where the amount added to the coin would be negative.
    @Test
    public void testNegative() {
        Portfolio myWallet = new Portfolio();
        myWallet.populateTester();
        Coin one = myWallet.getCoin1();
        Double originalOwned = one.getAmountOwned();
        Double userInput = -0.001;
        one.setAmountOwned(originalOwned + userInput);
        Double updatedOwned = one.getAmountOwned();
        assertEquals(updatedOwned, (Double) 0.004);

    }

    // Tests a simpler version of the updatePortfolio method that will be called when we go through the addition process
    // This test also applies to scenario 3 because the updatePortfolio method will be called from the PurchaseScreen activity when a coin is added
    @Test
    public void testUpdatePortfolio() {
        Portfolio myWallet = new Portfolio();
        myWallet.populateTester();
        Coin one = myWallet.getCoin1(); // get base value of coin stored in coin object
        Double baseValue = myWallet.getTotalValue(); // holds initial wallet value when it is created
        one.setAmountOwned(1.00); // hard code that increases how much coin 1 is owned
        one.setValueOwned(one.getCoinValue(), one.getAmountOwned()); // updates value owned since the variable does not change itself
        myWallet.updatePortfolioTester(); // updates the total value amount after a coin has changed
        Double updatedValue = myWallet.getTotalValue(); // checks that an increase in how much coin is owned leads to an increase in total wallet value
        assertNotEquals(baseValue, updatedValue);
    }*/

    // *** END SCENARIO 3 UNIT TESTS ***

}
