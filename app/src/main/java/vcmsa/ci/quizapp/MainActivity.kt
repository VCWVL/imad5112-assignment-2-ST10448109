package vcmsa.ci.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }//end of viewCompat

        //Code

        //This allows for the userInterface to operate with the code
         val editTextTextUsername = findViewById<EditText>(R.id.editTextTextUsername)
         val buttonStart = findViewById<Button>(R.id.buttonStart)

        //Gather and input Data from whats declared on the UI
        val username = editTextTextUsername.text.toString()
        buttonStart.setOnClickListener {
            val intent = Intent(this, QuizQuestions::class.java )
            startActivity(intent)

        }// end of button
    }//end of onCreate

}//end of mainActivity