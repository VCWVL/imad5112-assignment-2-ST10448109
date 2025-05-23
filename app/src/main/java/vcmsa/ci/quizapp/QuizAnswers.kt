package vcmsa.ci.quizapp

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizAnswers : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz_answers)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Declared UI elements
        val textViewScore = findViewById<TextView>(R.id.textViewscore)
        val buttonCompleted = findViewById<Button>(R.id.buttonQuizCompleted)
        val buttonReview = findViewById<Button>(R.id.buttonReview)

        // Get score
        val score = intent.getIntExtra("score", 0)

        // Get quiz data
        val questions = intent.getStringArrayExtra("questions")
        val answers = intent.getBooleanArrayExtra("answers")

        // Display score
        textViewScore.text = "Your score is $score"

        // Feedback message
        val feedback = if (score >= 3) "Great job!" else "Keep practising!"
        Toast.makeText(this, feedback, Toast.LENGTH_LONG).show()

        // Completed button logic
        buttonCompleted.setOnClickListener {
            Toast.makeText(this, "Quiz Completed!", Toast.LENGTH_SHORT).show()
            finish()
        }

        // Review button logic (updated with larger text)
        buttonReview.setOnClickListener {
            if (questions != null && answers != null) {
                val reviewMessage = StringBuilder()
                for (i in questions.indices) {
                    reviewMessage.append("Q${i + 1}: ${questions[i]}\nAnswer: ${if (answers[i]) "True" else "False"}\n\n")
                }

                val dialogView = layoutInflater.inflate(R.layout.review_button_text_size, null)
                val textViewReview = dialogView.findViewById<TextView>(R.id.textViewButtonReview)
                textViewReview.text = reviewMessage.toString()

                AlertDialog.Builder(this)
                    .setTitle("Review Answers")
                    .setView(dialogView)
                    .setPositiveButton("Close", null)
                    .show()
            } else {
                Toast.makeText(this, "No review data available.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

