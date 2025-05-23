package vcmsa.ci.quizapp.calculatescoretest

import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.junit.Assert.assertEquals


@RunWith(Enclosed::class)
class calculatescoreTest
{


    class calculatescoreTest {

        @Test
        fun calculateScore_returnsCorrectScore() {
            val correctAnswers = listOf("A", "B", "C")
            val userAnswers = listOf("A", "X", "C")
            val score = calculateScore(correctAnswers, userAnswers)
            assertEquals(2, score)
        }

        private fun calculateScore(correct: List<String>, user: List<String>): Int {
            return correct.zip(user).count { it.first == it.second }
        }
    }











}