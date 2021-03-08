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
    Given I am on the main screen
    When clicking on a coin button on the page
    Then the app should show me the graph for that coin

 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class UserStoryFourScenarioTwoTest {

    @Rule
    public ActivityTestRule<HomeScreen> mActivityTestRule = new ActivityTestRule<>(HomeScreen.class);

    @Test
    public void userStoryFourScenario2Test() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.litecoinButton), withText("LiteCoin"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                16),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction imageView = onView(
                allOf(withId(R.id.imageView3),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

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
