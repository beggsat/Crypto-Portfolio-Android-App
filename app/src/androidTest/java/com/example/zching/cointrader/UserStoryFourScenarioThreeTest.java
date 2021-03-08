package com.example.zching.cointrader;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
//import android.test.suitebuilder.annotation.LargeTest;
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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


/*
User Story 4:
As a developer,
I want to implement data for coins
so that the user can see a graph, name, price, and percent change data for the specific coin


Scenario 3:
    Given I open the main screen
    When I click on a coin
    Then I should see the name for the coin

 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class UserStoryFourScenarioThreeTest {

    @Rule
    public ActivityTestRule<HomeScreen> mActivityTestRule = new ActivityTestRule<>(HomeScreen.class);

    @Test
    public void userStoryFourScenarioThreeTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.ethButton), withText("Ethereum"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                17),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.ethpriceText), withText("NAME: ETHEREUM"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("NAME: ETHEREUM")));

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
