import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.vinilos_mobile.view.*
import com.example.vinilos_mobile.R
import org.hamcrest.CoreMatchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class PerformerTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(WelcomeActivity::class.java)

    private fun gotoList() {
        val guestButton = onView(withId(R.id.userIsGuestButton))
        guestButton.perform(click())

        Thread.sleep(100)
        // Go to the collectors
        onView(withId(R.id.buttonIconArtists)).check(matches(isDisplayed()))
        onView(withId(R.id.buttonIconArtists)).perform(click())

        onView(withId(R.id.performer_recycler_view))
        onView(withId(R.id.list_artist_name)).check(matches(isDisplayed()))
        onView(withId(R.id.list_artist_name)).perform(click())
        Thread.sleep(100)
    }

    @Test
    fun listPerformers() {

        this.gotoList()
        onView(withId(R.id.performer_recycler_view))
        onView(withId(R.id.list_artist_name)).check(matches(isDisplayed()))
    }

    @Test
    fun performerDetailBand() {
        this.gotoList()
        onView(allOf(withId(R.id.performer_name), withText("Queen"))).check(matches(isCompletelyDisplayed()))
        onView(allOf(withId(R.id.performer_name), withText("Queen"))).perform(click())
        // Check the properties
        onView(withId(R.id.performer_detail_name)).check(matches(withText(containsString("Queen"))))
        onView(withId(R.id.performer_detail_description)).check(matches(withText(containsString("Queen"))))
        onView(withId(R.id.performer_detail_image)).check(matches(isDisplayed()))
    }

    @Test
    fun performerDetailMusician() {
        this.gotoList()
        onView(allOf(withId(R.id.performer_name), withText("Rubén Blades Bellido de Luna"))).check(matches(isCompletelyDisplayed()))
        onView(allOf(withId(R.id.performer_name), withText("Rubén Blades Bellido de Luna"))).perform(click())
        // Check the properties
        onView(withId(R.id.performer_detail_name)).check(matches(withText(containsString("Rubén Blades Bellido de Luna"))))
        onView(withId(R.id.performer_detail_description)).check(matches(withText(containsString("panameño"))))
        onView(withId(R.id.performer_detail_image)).check(matches(isDisplayed()))
    }

    @Test
    fun performerDetailNavigationToAlbum() {
        this.gotoList()
        onView(allOf(withId(R.id.performer_name), withText("Queen"))).check(matches(isCompletelyDisplayed()))
        onView(allOf(withId(R.id.performer_name), withText("Queen"))).perform(click())
        // Check we can navigate to the albums
        onView(withText("A Day at the Races")).perform(click())
        onView(allOf(withId(R.id.album_name), withText("A Day at the Races"))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.artist_name), withText("Queen"))).check(matches(isDisplayed()))
    }
}
