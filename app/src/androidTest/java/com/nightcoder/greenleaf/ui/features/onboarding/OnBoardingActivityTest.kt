package com.nightcoder.greenleaf.ui.features.onboarding

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nightcoder.greenleaf.R
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OnBoardingActivityTest {
    private val sut = ActivityScenario.launch(OnBoardingActivity::class.java)

    @Before
    fun setup() {

    }

    @Test
    fun test_OnBoardingActivityDisplaying_Views() {

        onView(withId(R.id.toolbar_title)).check(matches(isDisplayed()))

        onView(withText(R.string.onboarding_header_text)).check(matches(isDisplayed()))

        onView(withText(R.string.try_new_feature_hint)).check(matches(isDisplayed()))

        onView(withId(R.id.nextBtn)).check(matches(isDisplayed()))
    }

    @Test
    fun test_OnBoardingActivity_nextButtonAction() {

        val nextBtn = onView(withId(R.id.nextBtn))
        nextBtn.check(matches(isClickable()))

        nextBtn.perform(click())

        onView(withText(R.string.search_tool)).check(matches(isDisplayed()))
    }

}