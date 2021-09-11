package br.com.wolking.moneyinvestmvvm

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {
    private val mockNavController: NavController = mock(NavController::class.java)
    private val homeFragment = launchFragmentInContainer<HomeFragment>()

    init {
        homeFragment.onFragment { fragment ->
            mockNavController.setGraph(R.navigation.application)
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }
    }

    @get:Rule
    val mActivityRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, false, true)

    @Test
    fun whenActivityIsLaunched_shouldDisplayInitialState() {
        onView(withId(R.id.editTextValue)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextPercent)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextYears)).check(matches(isDisplayed()))
        onView(withId(R.id.buttonCalculate)).check(matches(isDisplayed()))
    }

    @Test
    fun whenHandleCalculateIsCalledResult() {
        onView(withId(R.id.editTextValue)).perform(typeText("100"), closeSoftKeyboard())
        onView(withId(R.id.editTextPercent)).perform(typeText("10"), closeSoftKeyboard())
        onView(withId(R.id.editTextYears)).perform(typeText("2"), closeSoftKeyboard())
        onView(withId(R.id.buttonCalculate)).perform(click())

        onView(withId(R.id.textViewResult)).check(matches(not(withText(""))))
        onView(withId(R.id.textViewResult)).check(matches(withText("Total: R$ 121.01")))
    }
}