package com.example.zching.cointrader;

import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/*
    User Story 11:
    As a user
    I want to be able to change the value of a coin in Settings
    So that the entire app is updated with the new coin's value.
 */
public class UserStoryElevenTests {

    /*
        SCENARIO 1:
        Given I am on the home screen
        When I go through the process of updating a coin's value in settings
        Then the home screen should show the coin's updated values.
     */
    // Changing value of one coin


    // Basic logic that is called to change the coin's value
    @Test
    public void testOneUpdate() {
        HomeScreen home = mock(HomeScreen.class);
        when(home.getCoin1TextValue()).thenReturn("100"); // sets base from the mock
        Coin coin = new Coin(); // get the coin
        coin.setCoinValue(1000);
        assertNotEquals(home.getCoin1TextValue(), Integer.toString(coin.getCoinValue()));

    }

    @Test
    public void testMultipleUpdates() {
        HomeScreen home = mock(HomeScreen.class);
        when(home.getCoin1TextValue()).thenReturn("100"); // sets base returns for both coins
        when(home.getCoin2TextValue()).thenReturn("20");
        Coin coin1 = new Coin();
        coin1.setCoinValue(100);
        Coin coin2 = new Coin();
        coin2.setCoinValue(20); // base information for the test
        assertEquals(home.getCoin1TextValue(), Integer.toString(coin1.getCoinValue()));
        assertEquals(home.getCoin2TextValue(), Integer.toString(coin2.getCoinValue()));
    }

    @Test
    public void testNewValue() {
        boolean tester = false;
        HomeScreen home = new HomeScreen();
        Coin place = new Coin();
        place.setCoinValue(100);
        home.updateCoinTester(place, 1000);
        assertNotEquals(place.getCoinValue(), 100);
    }
    /*
        SCENARIO 2:
        Given I have my portfolio
        When I update a coin's value through settings
        Then the value of the coin I own in my portfolio should update.
     */

    /*
        SCENARIO 3:
        Given I have my portfolio
        When I update a coin's value through settings
        Then my total wallet value should update to include the changes.
     */

}
