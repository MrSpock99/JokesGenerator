package itis.ru.jokesgenerator

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions.swipeDown
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import itis.ru.jokesgenerator.activity.JokeListActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class JokeListActivityTest {

    @Rule
    @JvmField
    val rule  = ActivityTestRule(JokeListActivity::class.java)

    @Test
    fun showInsultListSuccess(){
        Espresso.onView(withId(R.id.rv_jokes))
            .check(matches(isDisplayed()))
    }

    @Test
    fun showRefreshIsDisabled(){
        Espresso.onView(withId(R.id.swipeContainer))
            .check(matches(isDisplayed()))
    }

    @Test
    fun showRefreshWhenPulledDown(){
        Espresso.onView(withId(R.id.swipeContainer))
            .perform(swipeDown())
            .check(matches(isDisplayed()))
    }
}
