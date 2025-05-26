package vcmsa.ci.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizQuestions : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz_questions)

        // Handle edge insets (notch, system bars)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // UI Elements
        val textQuestion = findViewById<TextView>(R.id.TextViewQuestion)
        val buttonTrue = findViewById<Button>(R.id.buttonTrue)
        val buttonFalse = findViewById<Button>(R.id.buttonFalse)
        val buttonNext = findViewById<Button>(R.id.buttonNextQuestion)

        // Flashcard data
        val questions = arrayOf(
            "\n \n \n Nelson Mandela was released from prison in 1990.",
            "\n \n \n South Africa held its first democratic elections in 1985.",
            "\n \n \n The Soweto Uprising was in response to school policies.",
            "\n \n \n Desmond Tutu led the military during apartheid.",
            "\n \n \n Pass Laws restricted movement of non-white citizens."
        )

        val answers = booleanArrayOf(
            true,
            false,
            true,
            false,
            true
        )

        var questionCurrent = 0
        var score = 0
        var userAnswerGiven = false

        // Show first question
        textQuestion.text = questions[questionCurrent]

        // Handle True button click
        buttonTrue.setOnClickListener {
            if (userAnswerGiven) return@setOnClickListener // Prevent multiple clicks

            userAnswerGiven = true
            val correctAnswer = answers[questionCurrent]

            // Check user's answer and show feedback
            if (true == correctAnswer) {
                showCustomToast("Correct!")
                score++
            } else {
                showCustomToast("Incorrect")
            }
        }

        // Handle False button click
        buttonFalse.setOnClickListener {
            if (userAnswerGiven) return@setOnClickListener

            userAnswerGiven = true
            val correctAnswer = answers[questionCurrent]

            // Check user's answer and show feedback
            if (false == correctAnswer) {
                showCustomToast("Correct!")
                score++
            } else {
                showCustomToast("Incorrect")
            }
        }

        // Handle Next button click
        buttonNext.setOnClickListener {
            if (!userAnswerGiven) {
                showCustomToast("Please select True or False")
                return@setOnClickListener
            }

            questionCurrent++

            if (questionCurrent < questions.size) {
                // Show next question
                textQuestion.text = questions[questionCurrent]
                userAnswerGiven = false

                // Change button text to "Done" if next question is the last one
                if (questionCurrent == questions.size - 1) {
                    buttonNext.text = "Press to view your score"
                }
            } else {
                // Quiz finished, move to Score screen
                val intent = Intent(this, QuizAnswers::class.java)
                intent.putExtra("score", score)
                intent.putExtra("questions", questions)
                intent.putExtra("answers", answers)
                startActivity(intent)
                finish()
            }
        }
    } // end of onCreate

    // Custom Toast function for bigger, better feedback messages
    private fun showCustomToast(message: String) {
        val inflater = layoutInflater
        val layout = inflater.inflate(
            R.layout.custom_correct_button,
            findViewById(android.R.id.content),
            false
        )
        val textView = layout.findViewById<TextView>(R.id.textToast)
        textView.text = message

        with(Toast(applicationContext)) {
            duration = Toast.LENGTH_SHORT
            view = layout
            setGravity(
                android.view.Gravity.BOTTOM or android.view.Gravity.CENTER_HORIZONTAL,
                0,
                200
            )
            show()
        }
    }
} // end of QuizQuestions Activity
