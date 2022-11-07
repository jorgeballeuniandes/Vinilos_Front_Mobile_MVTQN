package com.grupo20.vinilos;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestVinilos_pr001 {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() throws InterruptedException {
        Thread.sleep(3000);
        ViewInteraction collectorsLisBtn = onView(allOf(withId(R.id.navigation_artists),isDisplayed()));
        collectorsLisBtn.perform(click());
        Thread.sleep(3000);
        ViewInteraction collectorsTitle = onView((allOf(withId(R.id.artists_view_title), withText("Artists List"), isDisplayed())));
        collectorsTitle.check(matches(isDisplayed()));
    }


}