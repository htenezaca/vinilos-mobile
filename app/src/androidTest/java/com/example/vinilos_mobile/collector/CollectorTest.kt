package com.example.vinilos_mobile.collector

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.vinilos_mobile.view.*
import com.example.vinilos_mobile.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class CollectorTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(WelcomeActivity::class.java)

    @Test
    fun listCollectors() {

        val guestButton = onView(withId(R.id.userIsGuestButton))
        guestButton.perform(click())

        Thread.sleep(1000)
        // Go to the collectors
        onView(withId(R.id.buttonIconCollectors)).check(matches(isDisplayed()))
        onView(withId(R.id.buttonIconCollectors)).perform(click())
        Thread.sleep(1000)

        onView(withId(R.id.collector_recycler_view))
        onView(withId(R.id.list_collector_name)).check(matches(isDisplayed()))
    }

    @Test
    fun collectorDetail() {

        val guestButton = onView(withId(R.id.userIsGuestButton))
        guestButton.perform(click())

        Thread.sleep(1000)
        // Go to the collectors
        onView(withId(R.id.buttonIconCollectors)).check(matches(isDisplayed()))
        onView(withId(R.id.buttonIconCollectors)).perform(click())
        Thread.sleep(1000)

        Thread.sleep(1000)
        // Go to the collectors
        val collectorRecycler = onView(withId(R.id.collector_recycler_view))
        collectorRecycler.perform(click())
        collectorRecycler.check(matches(hasDescendant(withId(R.id.cardView))))
        onView(allOf(withId(R.id.collectorName), withText("Manolo Bellon"))).perform(
            click()
        )


    }
}