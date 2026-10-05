package edu.temple.namelist

import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NameDeletionTest {
    private fun waitForUi() = InstrumentationRegistry.getInstrumentation().waitForIdleSync()

    @Test
    fun deletingFirstNameUpdatesDisplayAndAllowsSelectingAnotherName() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            waitForUi()
            scenario.onActivity {
                assertEquals("Kevin Shaply", it.findViewById<TextView>(R.id.textView).text.toString())
                it.findViewById<Button>(R.id.deleteButton).performClick()
            }
            waitForUi()
            scenario.onActivity {
                assertEquals("Stacey Lou", it.findViewById<TextView>(R.id.textView).text.toString())
                val spinner = it.findViewById<Spinner>(R.id.spinner)
                assertEquals(4, spinner.count)
                assertEquals("Stacey Lou", spinner.selectedItem)
                spinner.setSelection(3)
            }
            waitForUi()
            scenario.onActivity {
                assertEquals("Michelle Studdard", it.findViewById<TextView>(R.id.textView).text.toString())
            }
        }
    }

    @Test
    fun deletingLastNameSelectsPreviousName() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            waitForUi()
            scenario.onActivity { it.findViewById<Spinner>(R.id.spinner).setSelection(4) }
            waitForUi()
            scenario.onActivity { it.findViewById<Button>(R.id.deleteButton).performClick() }
            waitForUi()
            scenario.onActivity {
                assertEquals("Michael Studdard", it.findViewById<Spinner>(R.id.spinner).selectedItem)
                assertEquals("Michael Studdard", it.findViewById<TextView>(R.id.textView).text.toString())
            }
        }
    }

    @Test
    fun deletingAllNamesClearsDisplayAndDisablesDelete() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            waitForUi()
            repeat(5) {
                scenario.onActivity { it.findViewById<Button>(R.id.deleteButton).performClick() }
                waitForUi()
            }
            scenario.onActivity {
                val button = it.findViewById<Button>(R.id.deleteButton)
                assertFalse(button.isEnabled)
                // Exercise a further click even with an empty list.
                button.performClick()
                assertEquals(0, it.findViewById<Spinner>(R.id.spinner).count)
                assertEquals("", it.findViewById<TextView>(R.id.textView).text.toString())
            }
        }
    }
}
