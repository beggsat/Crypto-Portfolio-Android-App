package com.example.zching.cointrader;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.filters.LargeTest;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
/*
User Story 3:
As a user,
I want a wallet created for me with multiple coins
so that I can track my earnings or losses.


Scenario 3:
Given I am in the wallet screen
When I add more of a coin to my wallet
Then the coin's value is updated on the base portfolio screen.
 */

/*
Purpose: To test if the function that is being used to convert the USD dollars to
         the given currency works. The unit tests make sure the functions used to
         convert in the app are correct, so that the currency displayed is also correct.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class UserStoryThreeScenarioThree {

    @Rule
    public ActivityTestRule<HomeScreen> mActivityTestRule = new ActivityTestRule<>(HomeScreen.class);

    @Test
    public void userstorythreescenarioThree() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.homePortfolioButton), withText("Portfolio"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.addButton2), withText("Add"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                15),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.amountPurchased),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("20"), closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.submit), withText("Submit"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.portfolioHomeButton), withText("Home"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                9),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.bitcoinButton), withText("Bitcoin"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                15),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.bitcoinPriceText), withText("PRICE: $6257"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        textView.check(matches(withText("PRICE: $6257")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
