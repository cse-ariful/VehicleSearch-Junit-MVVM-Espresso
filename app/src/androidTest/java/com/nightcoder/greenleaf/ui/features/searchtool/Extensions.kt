package com.nightcoder.greenleaf.ui.features.searchtool

import androidx.test.espresso.PerformException
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import junit.framework.AssertionFailedError
import java.lang.Thread.sleep
import java.util.concurrent.TimeoutException


fun ViewInteraction.waitForViewToVisible(maxWaitingTimeMs: Long) {
    val endTime = System.currentTimeMillis() + maxWaitingTimeMs
    do{
        try {
            check(matches(isDisplayed()))
            sleep(100)
            return
        } catch (ignored: AssertionFailedError) { }
    }while (System.currentTimeMillis() <= endTime)

    throw PerformException.Builder()
        .withCause(TimeoutException("Waited $maxWaitingTimeMs milliseconds but view didn't showed up"))
        .build()
}
