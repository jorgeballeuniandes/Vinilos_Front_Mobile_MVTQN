package com.grupo20.vinilos;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView;
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
public class TestVinilos_pr006 {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() throws InterruptedException {
        Thread.sleep(3000);
        ViewInteraction createAlbumButton = onView(allOf(withId(R.id.navigation_create_album),isDisplayed()));
        createAlbumButton.perform(click());
        Thread.sleep(3000);
        ViewInteraction albumNameText = onView((allOf(withId(R.id.txt_name_album), isDisplayed())));
        albumNameText.perform(click());
        albumNameText.perform( typeTextIntoFocusedView("OK Computer"));
        Thread.sleep(3000);
        ViewInteraction albumDescriptionText = onView((allOf(withId(R.id.txt_name_album), isDisplayed())));
        albumDescriptionText.perform(click());
        albumDescriptionText.perform( typeTextIntoFocusedView("OK Computer is the third studio album by the English rock band Radiohead, released in Japan on 21 May 1997 and in the UK on 16 June 1997. Radiohead self-produced the album with Nigel Godrich, an arrangement they have used for their subsequent albums. Radiohead recorded most of OK Computer in their rehearsal space in Oxfordshire and the historic mansion of St Catherine's Court in Bath in 1996 and early 1997."));
    }
}