package vcmsa.ci.quizapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizAnswers : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz_answers)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }//end of viewCompat

        //code
         val textViewScore = findViewById<TextView>(R.id.textViewscore)
        val buttonCompleted = findViewById<Button>(R.id.buttonQuizCompleted)



         //coding the declared elements
        //score and username
        val score = intent.getIntExtra("score", 0)
        val username = intent.getStringExtra("username")

        //display the users quiz score and thir name
        textViewScore.text= ("$username your score is $score")


        //  Button click handler
        buttonCompleted.setOnClickListener {
            Toast.makeText(this, "Quiz Completed!", Toast.LENGTH_SHORT).show()
            finish() //
        }
    }
}
