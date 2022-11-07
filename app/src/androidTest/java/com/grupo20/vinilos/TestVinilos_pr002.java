package com.grupo20.vinilos;


import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestVinilos_pr002 {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest2() throws InterruptedException {
        Thread.sleep(3000);
        ViewInteraction artistsLisBtn = onView(allOf(withId(R.id.navigation_artists),isDisplayed()));
        artistsLisBtn.perform(click());
        Thread.sleep(3000);
        ViewInteraction artistsText = onView((allOf(withText("Rubén Blades Bellido de Luna"), isDisplayed())));
        artistsText.check(matches(isDisplayed()));
    }
}