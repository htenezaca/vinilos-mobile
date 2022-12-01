package com.example.vinilos_mobile.album

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.vinilos_mobile.R
import com.example.vinilos_mobile.view.WelcomeActivity
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class AlbumTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(WelcomeActivity::class.java)

    @Test
    fun listAlbums() {

        val guestButton = onView(withId(R.id.userIsGuestButton))
        guestButton.perform(click())

        Thread.sleep(1500)
        val albumRecyclerView = onView(withId(R.id.album_recycler_view))
        albumRecyclerView.check(matches(hasDescendant(withId(R.id.album_list_album_name))))
    }

    @Test
    fun detailAlbum() {

        val guestButton = onView(withId(R.id.userIsGuestButton))
        guestButton.perform(click())

        Thread.sleep(1500)
        val albumRecyclerView = onView(withId(R.id.album_recycler_view))
        albumRecyclerView.check(matches(hasDescendant(withId(R.id.album_list_album_name))))
        // There are multiple items with the same, id pick them all, and click the first one
        onView(allOf(withId(R.id.album_list_album_name), withText("Buscando América"))).perform(
            click()
        )
        // Check for the title
        onView(withText("Buscando América")).check(matches(isDisplayed()))
        // Check for the description
        onView(withId(R.id.album_description_content)).check(matches(isDisplayed()))
        onView(withId(R.id.album_year)).check(matches(isDisplayed()))
        onView(withId(R.id.album_genre)).check(matches(isDisplayed()))
    }

    @Test
    fun addTrack() {

        val guestButton = onView(withId(R.id.userIsGuestButton))
        guestButton.perform(click())

        Thread.sleep(1500)
        val albumRecyclerView = onView(withId(R.id.album_recycler_view))
        albumRecyclerView.check(matches(hasDescendant(withId(R.id.album_list_album_name))))
        // There are multiple items with the same, id pick them all, and click the first one
        onView(allOf(withId(R.id.album_list_album_name), withText("Nevermind"))).perform(
            click()
        )
        // Check for the title
        onView(withText("Nevermind")).check(matches(isDisplayed()))
        onView(withId(R.id.fab_add_track)).perform(click())
        Thread.sleep(1000)
        val randomString = (1..10).map { kotlin.random.Random.nextInt(0, 10) }.joinToString("")
        onView(withId(R.id.track_add_name)).perform(typeText(randomString))
        onView(withId(R.id.track_add_duration_minute)).perform(typeText("1"))
        onView(withId(R.id.track_add_duration_second)).perform(typeText("30"))
        onView(withId(R.id.track_add_accept)).perform(click())
    }
}
