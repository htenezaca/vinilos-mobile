package com.example.vinilos_mobile.album

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.vinilos_mobile.view.*
import com.example.vinilos_mobile.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Matcher

@RunWith(AndroidJUnit4::class)
@LargeTest
class AlbumTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(WelcomeActivity::class.java)

    @Test
    fun listAlbums() {

        val guestButton = onView(withId(R.id.userIsGuestButton))
        guestButton.perform(click())

        Thread.sleep(1000)
        val albumRecyclerView = onView(withId(R.id.album_recycler_view))
        albumRecyclerView.perform(click())
        albumRecyclerView.check(matches(hasDescendant(withId(R.id.album_list_album_name))))
    }

    @Test
    fun detailAlbum() {

        val guestButton = onView(withId(R.id.userIsGuestButton))
        guestButton.perform(click())

        Thread.sleep(1000)
        val albumRecyclerView = onView(withId(R.id.album_recycler_view))
        albumRecyclerView.perform(click())
        albumRecyclerView.check(matches(hasDescendant(withId(R.id.album_list_album_name))))
        // There are multiple items with the same, id pick them all, and click the first one
        onView(allOf(withId(R.id.album_list_album_name), withText("Buscando América"))).perform(
            click()
        );
        // Check for the title
        onView(withText("Buscando América")).check(matches(isDisplayed()))
        // Check for the description
        onView(withId(R.id.album_description_content)).check(matches(isDisplayed()))
        onView(withId(R.id.album_year)).check(matches(isDisplayed()))
        onView(withId(R.id.album_genre)).check(matches(isDisplayed()))
    }
}