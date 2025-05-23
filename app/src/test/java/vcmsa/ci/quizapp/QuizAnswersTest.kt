package vcmsa.ci.quizapp

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class QuizAnswersTest {

    @Test
    fun testScoreDisplayAndButtons() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), QuizAnswers::class.java).apply {
            putExtra("score", 4)
            putExtra("questions", arrayOf("Q1", "Q2"))
            putExtra("answers", booleanArrayOf(true, false))
        }

        ActivityScenario.launch<QuizAnswers>(intent)

        // Assert text views and buttons are correctly displayed
        onView(withId(R.id.textViewscore)).check(matches(withText("Your score is 4")))
        onView(withId(R.id.buttonQuizCompleted)).check(matches(isDisplayed()))
        onView(withId(R.id.buttonReview)).check(matches(isDisplayed()))

        // Perform clicks to verify no crashes
        onView(withId(R.id.buttonReview)).perform(click())
        onView(withId(R.id.buttonQuizCompleted)).perform(click())
    }
}
