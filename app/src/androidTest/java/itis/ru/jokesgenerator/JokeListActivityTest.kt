package itis.ru.jokesgenerator

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
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
