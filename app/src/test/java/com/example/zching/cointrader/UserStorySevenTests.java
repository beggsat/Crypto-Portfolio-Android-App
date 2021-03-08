package com.example.zching.cointrader;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class UserStorySevenTests {

    /*User Story 7:
    As a user,
    I want to be able reset my wallet
    so that I can go back to the default state

    Scenario 1:
    Given I'm on the portfolio page and have coins in my wallet
    When I click on the reset button
    Then the wallet value and other coin values should be reset to zero.

    Scenario 2:
    Given I am on the home page
    When I go to the portfolio and click the reset button
    Then the amount owned column on the home page should be all zeroes.

    Scenario 3:
    Given I am on the portfolio page
    When I click the reset button
    Then the I should still be on the portfolio page and not the home page.*/

    //Scenario One
    @Test
    public void testResetAmountOwned()
    {
        Coin coin1 = new Coin();
        Coin coin2 = new Coin();

        coin1.setAmountOwned(0.0);
        coin2.setAmountOwned(0.0);

        assertEquals(coin1.amountOwned, 0.0);
    }

    //Scenario Two
    @Test
    public void testResetValueOwned()
    {
        Coin coin1 = new Coin();
        coin1.setValueOwned(0, 0.0);
        assertEquals(coin1.getValueOwned(), 0.0);
    }

    //Scenario Three
    @Test
    public void testResetTotalValue()
    {
        Coin coin1 = new Coin();
        Portfolio portfolio = new Portfolio();
        double totalValue = 0.0;
        assertEquals(portfolio.getTotalValue(), 0.0);
    }
}
