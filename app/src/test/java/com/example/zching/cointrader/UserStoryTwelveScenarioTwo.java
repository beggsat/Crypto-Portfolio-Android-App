package com.example.zching.cointrader;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserStoryTwelveScenarioTwo {

    @Test
    public void populateCoinsTest1()
    {
        float[] yDataTest = new float[2];
        yDataTest[0] = .50f;
        yDataTest[1] = 2.55f;

        float[] yData = new float[2];
        double btcComp = .50;
        double ethComp = 2.55;

        yData[0] = (float)btcComp;
        yData[1] = (float)ethComp;

        float delta = .1f;

        assertArrayEquals(yDataTest, yData, delta);

    }


    @Test
    public void populateCoinsTest2()
    {
        float[] yDataTest = new float[2];
        yDataTest[0] = 1.70f;
        yDataTest[1] = 53.8f;

        float[] yData = new float[2];
        double btcComp = 1.70;
        double ethComp = 53.8;

        yData[0] = (float)btcComp;
        yData[1] = (float)ethComp;

        float delta = .1f;

        assertArrayEquals(yDataTest, yData, delta);

    }

    @Test
    public void populateCoinsTest3()
    {
        float[] yDataTest = new float[2];
        yDataTest[0] = 0.0f;
        yDataTest[1] = 0.0f;

        float[] yData = new float[2];
        double btcComp = 0.0;
        double ethComp = 0.0;

        yData[0] = (float)btcComp;
        yData[1] = (float)ethComp;

        float delta = .1f;

        assertArrayEquals(yDataTest, yData, delta);

    }
}
