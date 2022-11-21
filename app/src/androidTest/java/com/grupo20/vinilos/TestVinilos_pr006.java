package com.grupo20.vinilos;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.RootMatchers;
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
        Thread.sleep(1000);
        ViewInteraction createAlbumButton = onView(allOf(withId(R.id.navigation_create_album),isDisplayed()));
        createAlbumButton.perform(click());
        Thread.sleep(1000);
        ViewInteraction albumNameText = onView((allOf(withId(R.id.txt_name_album), isDisplayed())));
        albumNameText.perform(swipeUp());
        albumNameText.perform(swipeUp());
        albumNameText.perform(click());
        albumNameText.perform( typeText("OK Computer"));
        Thread.sleep(1000);
        albumNameText.perform(swipeUp());
        albumNameText.perform(swipeUp());
        ViewInteraction albumUrlCoverText = onView((allOf(withId(R.id.txt_url_cover_album), isDisplayed())));
        albumUrlCoverText.perform(click());
        albumUrlCoverText.perform( typeText("https://en.wikipedia.org/wiki/OK_Computer#/media/File:Radioheadokcomputer.png"));
        Thread.sleep(1000);
        ViewInteraction genreDropDown = onView((allOf(withId(R.id.autoComplete_generos), isDisplayed())));
        genreDropDown.perform(click());
        onView(withText("Rock")).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        albumUrlCoverText.perform(swipeUp());
        albumUrlCoverText.perform(swipeUp());
        albumUrlCoverText.perform(swipeUp());
        ViewInteraction recordDropDown = onView((allOf(withId(R.id.autoComplete_records), isDisplayed())));
        recordDropDown.perform(click());
        recordDropDown.perform(click());
        onView(withText("EMI")).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        ViewInteraction albumDescriptionText = onView((allOf(withId(R.id.txt_description_album), isDisplayed())));
        albumDescriptionText.perform(click());
        albumDescriptionText.perform( typeText("OK Computer is the third studio album by the English rock band Radiohead, released in Japan on 21 May 1997 and in the UK on 16 June 1997. Radiohead self-produced the album with Nigel Godrich, an arrangement they have used for their subsequent albums. Radiohead recorded most of OK Computer in their rehearsal space in Oxfordshire and the historic mansion of St Catherine's Court in Bath in 1996 and early 1997."));
        albumDescriptionText.perform(swipeUp());
        Thread.sleep(500);
        albumDescriptionText.perform(swipeUp());
        albumDescriptionText.perform(click());Thread.sleep(3000);
        ViewInteraction albumReleaseDateText = onView((allOf(withId(R.id.txt_date_album), isDisplayed())));
        albumReleaseDateText.perform(click());
        albumReleaseDateText.perform( typeText("05/21/1997"));
        Thread.sleep(1000);
        ViewInteraction btnSubmit = onView((allOf(withId(R.id.btn_create_Album), isDisplayed())));
        btnSubmit.perform(click());
        Thread.sleep(3000);
        ViewInteraction albumsListMenuButton = onView(allOf(withId(R.id.navigation_albums),isDisplayed()));
        albumsListMenuButton.perform(click());
        Thread.sleep(3000);
        ViewInteraction albumItem = onView((allOf(withId(R.id.textView6), withText("OK Computer"), isDisplayed())));
        albumItem.check(matches(isDisplayed()));
    }
}