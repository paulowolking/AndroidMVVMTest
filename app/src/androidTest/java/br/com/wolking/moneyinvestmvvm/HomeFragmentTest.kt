package br.com.wolking.moneyinvestmvvm

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.not
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {
    private var navController: TestNavHostController =
        TestNavHostController(ApplicationProvider.getApplicationContext())

    init {
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
    }

    @get:Rule
    val mActivityRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, false, true)

    @Test
    fun testNavigationToInHome() {
        val scenario = launchFragmentInContainer<InitFragment>()
        scenario.onFragment { fragment ->
            navController.setGraph(R.navigation.application)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        onView(withId(R.id.buttonStart)).perform(click())
        Assert.assertEquals(navController.currentDestination?.id, R.id.homeFragment)
    }

    @Test
    fun whenActivityIsLaunched_shouldDisplayInitialState() {
        launchFragmentInContainer<HomeFragment>()
        onView(withId(R.id.editTextValue)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextPercent)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextYears)).check(matches(isDisplayed()))
        onView(withId(R.id.buttonCalculate)).check(matches(isDisplayed()))
    }

    @Test
    fun whenHandleCalculateIsCalledResult() {
        launchFragmentInContainer<HomeFragment>()
        onView(withId(R.id.editTextValue)).perform(replaceText("100")).perform(closeSoftKeyboard())
        onView(withId(R.id.editTextPercent)).perform(replaceText("10")).perform(closeSoftKeyboard())
        onView(withId(R.id.editTextYears)).perform(replaceText("2")).perform(closeSoftKeyboard())
        onView(withId(R.id.buttonCalculate)).perform(click())
        onView(withId(R.id.textViewResult)).check(matches(not(withText(""))))
        onView(withId(R.id.textViewResult)).check(matches(withText("Total: R$ 121")))
    }

    @Test
    fun whenHandleCalculateToastShow() {
        whenHandleCalculateIsCalledResult()
        onView(withText(R.string.value_calculated)).inRoot(
            withDecorView(not(mActivityRule.activity.window.decorView))
        ).check(matches(isDisplayed()))
    }
}