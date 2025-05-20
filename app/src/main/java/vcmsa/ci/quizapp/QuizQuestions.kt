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

        // username from mainActivity
        val username = intent.getStringExtra("username")

        //Code

        val textQuestion = findViewById<TextView>(R.id.TextViewQuestion)
        val ButtonNext = findViewById<Button>(R.id.buttonNextQuestion)
        val RadiobuttonAnswersOne = findViewById<RadioGroup>(R.id.RadioButtonAnswers)

        val ApartheidQuestions = arrayOf(
            "Nelson Mandela release Question: What was the date that Nelson Mandela was released from prison after 27 years of incarceration?\n"+"Can you spot the date:",
            "The first Democratic Elections Question:When did South Africa hold their first democratic elections which allowed citizens of all races to vote?\n" +"Which date made SA's history books:",
            "The Soweto Uprising Question: What was the main cause of the Soweto Uprising on the 16 June 1976?\n"+"It was:",
            "Desmond Tutu's Role Question: What was the role of the Archbishop Desmond Tutu play in post-apartheid South Africa?\n"+"The role of:",
            "The Pass Laws Question: What was the \"pass laws\" during apartheid in South Africa?\n"+"Choose from the following:"
        )

        val answerChoices = arrayOf(
            arrayOf("A: 27 April 1994", "B: 11 February 1990", "C: 16 June 1976", "D: 5 December 2013"),
            arrayOf("A: 16 June 1976", "B: 11 February 1990", "C: 27 April 1994", "D: 5 December 2018"),
            arrayOf("A:  A protest against pass laws", "B: The opposition to the use of Afrikaans as a medium of instruction in all schools", "C: A demand for Nelson Mandela's release", "D: The celebration of a national holiday"),
            arrayOf("A: The President of South Africa", "B: The head of the Truth and Reconciliation Commission", "C:  The Minister of Education", "D: The Chief Justice"),
            arrayOf("A: Laws that allowed all citizens to vote", "B:  Regulations that required non-white South Africans to carry documents which authorized their presence in restricted areas", "C: Rules that permitted interracial marriages", "D:Policies that promoted equal employment opportunities ")
        )
        var userAnswers = arrayOfNulls<String>(5)
        val correctAnswers = arrayOf(
            "B:11 February 1990",
            "C:27 April 1994",
            "B:The opposition to the use of Afrikaans as a medium of instruction in all schools",
            "B:The head of the Truth and Reconciliation Commission",
            "B:Regulations that required non-white South Africans to carry documents authorizing their presence in restricted areas"
        )

        var counter = 0

        textQuestion.text = ApartheidQuestions[counter]

        for (i in 0 until RadiobuttonAnswersOne.childCount) {
            val radioButton = RadiobuttonAnswersOne.getChildAt(i) as RadioButton
            radioButton.text = answerChoices[counter][i]
        }

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
                textQuestion.text = ApartheidQuestions[counter]

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


                //  send username to QuizAnswers
                intent.putExtra("username", username)
                startActivity(intent)
                finish()
            }

        }//next button
    }//end of onCreate

}//end of quiz questions Activity
