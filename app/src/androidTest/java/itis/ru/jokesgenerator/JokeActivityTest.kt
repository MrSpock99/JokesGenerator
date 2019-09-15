package itis.ru.jokesgenerator

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
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
