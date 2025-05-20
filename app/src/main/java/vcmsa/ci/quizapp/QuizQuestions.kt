package vcmsa.ci.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import vcmsa.ci.quizapp.R

class QuizQuestions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz_questions)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }//end of viewCompat

        //Code

        //This allows for the userInterface to operate with the code
        val textQuestion = findViewById<TextView>(R.id.TextViewQuestion)
        val ButtonNext = findViewById<Button>(R.id.buttonNextQuestion)
        val RadiobuttonAnswersOne = findViewById<RadioGroup>(R.id.RadioButtonAnswers)

        //Arrays of Questions and Answers
        val scienceQuestions = arrayOf(
            "What is the chemical synbol for water?",

            "Which planet is known as the Red Planet?",

            "What force keeps planets in orbit around the sun?",

            "What gas do plants absorb fron the atmosphere?",

            "What is the boling point of water at sea level?"
        )

        val answerChoices = arrayOf(
            arrayOf("A: H20", "B: CO2", "C: 02"),

            arrayOf("A: Venus", "B: Mars", "C: Jupiter"),

            arrayOf("A: Hagnetisn", "B: Gravity", "C: Nuclear Force"),

            arrayOf("A: Oxygen", "B: Carbon Dioxide", "C: Nitrogen"),

            arrayOf("А: 160°C", "В: 0°C", "C: 50C")
        )
        var userAnswers = arrayOfNulls<String>(5)
        val correctAnswers = arrayOf(
            "A:H20",
            "B:Mars",
            "B:Gravity",
            "B:Carbon Dioxide",
            "A:100°C"
        )

        var counter = 0

        ButtonNext.setOnClickListener {

            val chosenAnswer = RadiobuttonAnswersOne.checkedRadioButtonId
            if (chosenAnswer == -1) {
                Toast.makeText(this, "Please choose an Answer", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val chosenButton = findViewById<RadioButton>(chosenAnswer)
            userAnswers[counter] = chosenButton.text.toString()

            counter++

            if (counter < 5) {
                textQuestion.text = scienceQuestions[counter]

                for (i in 0 until RadiobuttonAnswersOne.childCount) {
                    val radioButton = RadiobuttonAnswersOne.getChildAt(i) as RadioButton
                    radioButton.text = answerChoices[counter][i]
                }

                RadiobuttonAnswersOne.clearCheck()
            } else {
                val intent = Intent(this, QuizAnswers::class.java)
                var score = 0
                for (i in 0 until userAnswers.size) {
                    if (userAnswers[i] == correctAnswers[i]) {
                        score++
                    }
                }
                intent.putExtra("score", score)
                startActivity(intent)
                finish()
            }

    }//next button
        }//end of onCreate

    }//end of quiz questions Activity

