package com.example.vinilos_mobile.album

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
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
        val randomString = System.currentTimeMillis().toString()
        onView(withId(R.id.track_add_name)).perform(typeText(randomString))
        onView(withId(R.id.track_add_duration_minute)).perform(typeText("1"))
        onView(withId(R.id.track_add_duration_second)).perform(typeText("30"))
        onView(withId(R.id.track_add_accept)).perform(click())
    }


    @Test
    fun createAlbum() {

        val guestButton = onView(withId(R.id.userIsGuestButton))
        guestButton.perform(click())

        Thread.sleep(1000)
        onView(withId(R.id.buttonnew)).perform(click())
        Thread.sleep(1000)
        // Album name from unix timestamp
        val albumName = System.currentTimeMillis().toString()
        val albumDescription = (1..10).map { kotlin.random.Random.nextInt(0, 10) }.joinToString("")
        onView(withId(R.id.album_name_edit)).perform(typeText(albumName))
        onView(withId(R.id.album_image_edit)).perform(typeText("https://cdns-images.dzcdn.net/images/cover/231c66c0ed260c708163eff3bb8458da/264x264.jpg"))
        onView(withId(R.id.album_date_edit)).perform(click())
        Thread.sleep(1000)
        onView(withText("OK")).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.album_description_edit)).perform(typeText(albumDescription))
        onView(isRoot()).perform(closeSoftKeyboard())
        onView(withId(R.id.genderDropdown)).perform(
            typeText(
                "Rock\t"
            )
        )
        onView(isRoot()).perform(closeSoftKeyboard())
        onView(withId(R.id.labelDropdown)).perform(
            typeText(
                "Sony Music\t"
            )
        )
        onView(isRoot()).perform(closeSoftKeyboard())
        Thread.sleep(1500)
        onView(withId(R.id.buttonpost)).perform(click())

        // Move to collectionist
        Thread.sleep(1500)
        onView(withId(R.id.buttonIconCollectors)).perform(click())
        onView(withId(R.id.buttonIconAlbums)).perform(click())

        // Find it in the album_recycler_view
        Thread.sleep(1500)
        // Swipe down
        onView(withId(R.id.album_recycler_view)).perform(swipeUp())
        Thread.sleep(1000)
        onView(withId(R.id.album_recycler_view)).check(matches(hasDescendant(withId(R.id.album_list_album_name))))
        onView(allOf(withId(R.id.album_list_album_name), withText(albumName))).check(matches(isDisplayed()))

    }
}
