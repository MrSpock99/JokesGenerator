package itis.ru.jokesgenerator

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
class MockActivityTest {
    @Rule
    @JvmField
    val rule = ActivityTestRule(MockActivity::class.java)

    @Test
    fun showActivityTitleSuccess() {
        Espresso.onView(withId(R.id.tv_mock_title))
            .check(matches(withText(R.string.title_mock_activity)))
    }
}
