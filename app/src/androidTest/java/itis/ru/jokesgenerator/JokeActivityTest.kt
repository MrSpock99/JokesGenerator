package itis.ru.jokesgenerator

import android.content.Intent
import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import itis.ru.jokesgenerator.activity.JokeActivity
import itis.ru.jokesgenerator.activity.JokeListActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class JokeActivityTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(JokeActivity::class.java, false, false)

    @Test
    fun showJokeTextSuccess() {
        val intent = Intent()
        intent.putExtra(JokeListActivity.EXTRA_JOKE, "Joke")
        rule.launchActivity(intent)
        Espresso.onView(withId(R.id.tv_joke))
            .check(matches(withText("Joke")))
    }
}
