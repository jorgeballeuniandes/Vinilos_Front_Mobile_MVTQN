package com.grupo20.vinilos;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.action.ViewActions.typeText;
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
public class TestVinilos_pr012 {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() throws InterruptedException {
        Thread.sleep(3000);
        ViewInteraction albumButton = onView(allOf(withId(R.id.navigation_albums),isDisplayed()));
        albumButton.perform(click());
        Thread.sleep(3000);
        ViewInteraction albumItem = onView(allOf(withId(R.id.textView6), withText("OK Computer"),isDisplayed()));
        albumItem.perform(click());
        Thread.sleep(3000);
        ViewInteraction tracksButton = onView(allOf(withId(R.id.btn_album_tracks),isDisplayed()));
        tracksButton.perform(click());
        Thread.sleep(3000);
        ViewInteraction addTrackButton = onView(allOf(withId(R.id.btn_add_track),isDisplayed()));
        addTrackButton.perform(click());
        Thread.sleep(3000);
        ViewInteraction trackNameText = onView((allOf(withId(R.id.txt_name_track), isDisplayed())));
        trackNameText.perform(swipeUp());
        trackNameText.perform(click());
        trackNameText.perform( typeText("Test Track 1"));
        Thread.sleep(1000);
        ViewInteraction trackDurationText = onView((allOf(withId(R.id.txt_duration_track), isDisplayed())));
        trackDurationText.perform(swipeUp());
        Thread.sleep(1000);
        trackDurationText.perform(click());
        trackDurationText.perform( typeText("3:12"));
        Thread.sleep(1000);
        ViewInteraction btnCreateTrack = onView((allOf(withId(R.id.btn_create_track), isDisplayed())));
        btnCreateTrack.perform(click());
        Thread.sleep(3000);
        ViewInteraction trackItem = onView(allOf(withId(R.id.nameTrack), withText("Test Track 1"),isDisplayed()));
        trackItem.perform(click());
        Thread.sleep(1000);
    }


}