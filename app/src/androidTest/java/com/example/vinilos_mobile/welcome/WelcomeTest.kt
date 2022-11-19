package com.example.vinilos_mobile.welcome

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.vinilos_mobile.view.WelcomeActivity
import com.example.vinilos_mobile.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class WelcomeTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(WelcomeActivity::class.java)

    @Test
    fun showMenu() {
        onView(withId(R.id.imageWelcomeView)).check(matches(isDisplayed()))
        onView(withId(R.id.userIsGuestButton)).check(matches(isDisplayed()))
        onView(withId(R.id.userIsCollectorButton)).check(matches(isDisplayed()))
    }

    @Test
    fun showCollects() {
        onView(withId(R.id.userIsCollectorButton)).perform(click())
        onView(withId(R.id.collectorsWelcomeDropdown)).check(matches(isDisplayed()))
    }
}
