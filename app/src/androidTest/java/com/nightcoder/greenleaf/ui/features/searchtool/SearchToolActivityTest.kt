package com.nightcoder.greenleaf.ui.features.searchtool

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nightcoder.vehiclesearch.R
import com.nightcoder.vehiclesearch.ui.features.searchtool.SearchToolActivity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SearchToolActivityTest {


    @Before
    fun setup() {

        ActivityScenario.launch(SearchToolActivity::class.java)

    }

    @Test
    fun test_If_searchField_is_Displayed_and_Focusable_AfterClicked() {

        val searchField = onView(withId(R.id.searchField))

        searchField.check(matches(isDisplayed()))

        searchField.perform(click())

        searchField.check(matches(isFocusable()))

    }


    @Test
    fun test_typeAndSearchShowingSuccessOrError() {

        val searchField = onView(withId(R.id.searchField))

        searchField.perform(click())

        searchField.perform(typeText("xxyyzzz"))

        val searchBtn = onView(withId(R.id.searchBtn))
        searchBtn.check(matches(isDisplayed()))
        searchBtn.perform(click())

        val progressbar = onView(withId(R.id.progressbar))

        progressbar.waitForViewToVisible(5000)

        progressbar.check(matches(isDisplayed()))

        try {
            onView(withId(R.id.resultView)).waitForViewToVisible(5000)
        } catch (ex: Exception) {
            onView(withId(R.id.errorView)).check(matches(isDisplayed()))
        }

    }
}

