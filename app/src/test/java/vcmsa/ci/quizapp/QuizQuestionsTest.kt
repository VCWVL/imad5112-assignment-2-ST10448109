package vcmsa.ci.quizapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class QuizQuestionsTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(QuizQuestions::class.java)

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun completeQuizWithTrueNavigatesToQuizAnswers() {
        repeat(5) {
            onView(withId(R.id.buttonTrue)).perform(click())
            onView(withId(R.id.buttonNextQuestion)).perform(click())
        }
        Intents.intended(hasComponent(QuizAnswers::class.java.name))
    }

    @Test
    fun completeQuizWithFalseNavigatesToQuizAnswers() {
        repeat(5) {
            onView(withId(R.id.buttonFalse)).perform(click())
            onView(withId(R.id.buttonNextQuestion)).perform(click())
        }
        Intents.intended(hasComponent(QuizAnswers::class.java.name))
    }
}





