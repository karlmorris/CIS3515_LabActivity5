package edu.temple.namelist

import android.widget.Spinner
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.not
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NameDeletionTest {
    @Test
    fun adapterCountTracksItsCollectionAfterDeletion() {
        val names = mutableListOf("A", "B", "C", "D", "E")
        val adapter = CustomAdapter(names, InstrumentationRegistry.getInstrumentation().targetContext)
        assertEquals(5, adapter.count)
        names.removeAt(0)
        assertEquals(4, adapter.count)
        names.clear()
        assertEquals(0, adapter.count)
    }

    @Test
    fun deletingTheSelectedNameUpdatesTheLargeDisplayAndAllowsNewSelection() {
        ActivityScenario.launch(MainActivity::class.java).use {
            onView(withId(R.id.deleteButton)).perform(click())
            onView(withId(R.id.textView)).check(matches(withText("Stacey Lou")))
            onView(withId(R.id.spinner)).perform(click())
            onData(equalTo("Michelle Studdard")).perform(click())
            onView(withId(R.id.textView)).check(matches(withText("Michelle Studdard")))
            onView(withId(R.id.deleteButton)).perform(click())
            onView(withId(R.id.textView)).check(matches(withText("Michael Studdard")))
        }
    }

    @Test
    fun deletingAllNamesClearsTheDisplayAndPreventsFurtherDeletion() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            repeat(5) { onView(withId(R.id.deleteButton)).perform(click()) }
            onView(withId(R.id.spinner)).check(matches(not(isEnabled())))
            onView(withId(R.id.deleteButton)).check(matches(not(isEnabled())))
            onView(withId(R.id.textView)).check(matches(withText("No names remaining")))
            scenario.onActivity { activity ->
                // A queued click must also be harmless after the last removal.
                activity.findViewById<android.view.View>(R.id.deleteButton).performClick()
                assertEquals(0, activity.findViewById<Spinner>(R.id.spinner).count)
            }
        }
    }

    @Test
    fun deletedNamesStayDeletedAfterActivityRecreation() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            onView(withId(R.id.deleteButton)).perform(click())
            onView(withId(R.id.spinner)).perform(click())
            onData(equalTo("Michelle Studdard")).perform(click())
            scenario.recreate()
            onView(withId(R.id.spinner)).check { view, _ ->
                val spinner = view as Spinner
                assertEquals(4, spinner.count)
                assertFalse((0 until spinner.count).any { spinner.getItemAtPosition(it) == "Kevin Shaply" })
            }
            onView(withId(R.id.textView)).check(matches(withText("Michelle Studdard")))
        }
    }
}
