package com.example.vinilos_mobile.album

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.vinilos_mobile.view.WelcomeActivity
import com.example.vinilos_mobile.R
import org.hamcrest.Matchers.*
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

        // alternativa 1
        // TODO: evitar sleep
        Thread.sleep(1000)

        val albumRecyclerView = onView(withId(R.id.album_recycler_view))
        albumRecyclerView.perform(click())
        albumRecyclerView.check(matches(hasDescendant(withId(R.id.album_name))))
        albumRecyclerView.check(matches(hasDescendant(withId(R.id.album_image_view))))

        // se comprueba lectura satisfactoria ya que al ejecutar la siguiente
        // linea, arroja error por 'matches multiple views in the hierarchy'
        //onView(withId(R.id.album_name)).check(matches(isDisplayed()))

        /*// alternativa 2
        // requiere: import androidx.test.espresso.contrib.RecyclerViewActions
        Thread.sleep(1000)

        val albumRecyclerView = onView(ViewMatchers.withId(R.id.album_recycler_view))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0)
            )

        albumRecyclerView.check(matches(hasDescendant(withId(R.id.album_name))))
        albumRecyclerView.check(matches(hasDescendant(withId(R.id.album_image_view))))*/

    }
}