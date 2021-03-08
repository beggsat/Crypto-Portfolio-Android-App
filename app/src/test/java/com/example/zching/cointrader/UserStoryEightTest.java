package com.example.zching.cointrader;

import org.junit.Test;

import static org.junit.Assert.*;

/*
User Story 8:
As a developer,
I want to be able to support multiple currencies
so that we can support users from other countries

 */

/*
Purpose: To test if the function that is being used to convert the USD dollars to
         the given currency works. The unit tests make sure the functions used to
         convert in the app are correct, so that the currency displayed is also correct.
 */




public class UserStoryEightTest {

    /*
Scenario 3:
    Given the user wants to switch currencies
    When they click the Yen (JPY) button
    Then they should be able to see the wallet amount in Yen

   Note: 1 USD = 112.5 Yen according to the formula for calculations.
 */


    @Test
    public void yenFormulaTest1() {
        double input = 20.0;
        double output;
        double expected = 2250.0;
        double delta = .1;

        output = Portfolio.yenFormula(input);

        assertEquals(expected, output, delta);
    }

    // special case if input is 0.0
    @Test
    public void yenFormulaTest2() {
        double input = 0.0;
        double output;
        double expected = 0.0;
        double delta = .1;

        output = Portfolio.yenFormula(input);

        assertEquals(expected, output, delta);
    }

// testing with negative currency
    @Test
    public void yenFormulaTest3() {
        double input = -75.22525;
        double output;
        double expected = -8462.840625;
        double delta = .1;

        output = Portfolio.yenFormula(input);

        assertEquals(expected, output, delta);
    }

    /*
    -----------------------------------------------------------------------------
     */


    /*
Scenario 1:
  Given the user wants to switch currencies
  When they click the Pounds (GDP) button
  Then they should be able to see the wallet amount in pounds

  Note: 1 USD = .76 pounds according to the formula for calculations.
*/

    @Test
    public void poundFormula1() {

        double input = 45.0;
        double output;
        double expected = 34.2;
        double delta = .1;

        output = Portfolio.poundFormula(input);

        assertEquals(expected, output, delta);
    }

    @Test
    public void poundFormula2() {

        double input = 0.0;
        double output;
        double expected = 0.0;
        double delta = .1;

        output = Portfolio.poundFormula(input);

        assertEquals(expected, output, delta);
    }

    @Test
    public void poundFormula3() {

        double input = -75.22525;
        double output;
        double expected = -57.17119;
        double delta = .1;

        output = Portfolio.poundFormula(input);

        assertEquals(expected, output, delta);
    }

    /*
    -----------------------------------------------------------------------------
     */


    /*
Scenario 2:
  Given the user wants to switch currencies
  When they click the Euros (EUR) button
  Then they should be able to see the wallet amount in Euros

 Note: 1 USD = .87 pounds according to the formula for calculations.
*/


    @Test
    public void euroFormula() {

        double input = 20.0;
        double output;
        double expected = 17.4;
        double delta = .1;

        output = Portfolio.euroFormula(input);

        assertEquals(expected, output, delta);
    }

    @Test
    public void euroFormulaTest2() {

        double input = 0.0;
        double output;
        double expected = 0.0;
        double delta = .1;

        output = Portfolio.euroFormula(input);

        assertEquals(expected, output, delta);
    }

    @Test
    public void euroFormulaTest3() {

        double input = -75.22525;
        double output;
        double expected = -65.4459675;
        double delta = .1;

        output = Portfolio.euroFormula(input);

        assertEquals(expected, output, delta);
    }
}