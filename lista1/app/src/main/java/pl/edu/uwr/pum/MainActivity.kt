package pl.edu.uwr.pum

import android.content.Intent
import android.content.Intent.CATEGORY_BROWSABLE
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.system.exitProcess

// Class for questions
class Question(val question: String, val answer: Int)

// List of questions
val questions = listOf(
    Question("Czy istnieje mniejsza jednostka czasu od czasu Plancka?", 0),
    Question("Czy równania Navier-Stokesa mają rozwiązanie w 3D?", 0),
    Question("Czy gwiazdy mają inny kolor niż widzimy w teleskopach?", 1),
    Question("Czy galaktyki są kolorowe?", 0),
    Question("Czy wzór na wahadło jaki znamy jest w pewnym sensie uproszczeniem?", 1),
    Question("Czy Słońce ogrzewa Ziemię poprzez przewodzenie cieplne?", 0),
    Question("Czy spinacz staje się cięższy kiedy wygniemy go kilkukrotnie?", 1),
    Question("Czy jednostka lepkości to N*s/m^2", 1),
    Question("Czy stara zwykła żarówka była lepszym grzejnikiem niż żarówką?", 1),
    Question("Czy opór materiału może się zmieniać pod wpływem temperatury?", 1)
)

class MainActivity : AppCompatActivity() {

    // Declare objects on the screen so we can interact with them
    private val buttonTrue: Button by lazy { findViewById(R.id.prawda) }
    private val buttonFalse: Button by lazy { findViewById(R.id.falsz) }
    private val buttonCheat: Button by lazy { findViewById(R.id.oszustwo) }
    private val textQuestion: TextView by lazy { findViewById(R.id.pytania) }

    // Variables needed for score management
    private var score: Int = 0
    private var questionNumber: Int = 0
    private var cheatNumber: Int = 0
    private var correctAnswer: Int = 0

    // onclick functions for buttons
    private fun onClickButtonTrue() {
        if (questionNumber < questions.size) {
            if (questions[questionNumber].answer == 1) {
                score += 10
                correctAnswer++
            }
            if (questionNumber <= questions.size - 1) questionNumber++
        }
        if (questionNumber == questions.size) {
            val intent = Intent(this, Activity2::class.java)
            intent.putExtra("score", score)
            intent.putExtra("cheat", cheatNumber)
            intent.putExtra("correct", correctAnswer)
            startActivity(intent)
            finish()
            exitProcess(0)
        }
        textQuestion.text = questions[questionNumber].question
    }

    private fun onClickButtonFalse() {
        if (questionNumber < questions.size) {
            if (questions[questionNumber].answer == 0) {
                score += 10
                correctAnswer++
            }
            if (questionNumber <= questions.size - 1) questionNumber++
        }
        if (questionNumber == questions.size) {
            val intent = Intent(this, Activity2::class.java)
            intent.putExtra("score", score)
            intent.putExtra("cheat", cheatNumber)
            intent.putExtra("correct", correctAnswer)
            startActivity(intent)
            finish()
            exitProcess(0)
        }
        textQuestion.text = questions[questionNumber].question
    }

    private fun onClickButtonCheat() {
        val url =
            "http://google.pl//search?q=" + questions[questionNumber].question.replace(" ", "+")
        val intent =
            Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply { addCategory(CATEGORY_BROWSABLE) }
        if (questionNumber <= questions.size - 1) {
            questionNumber++
            cheatNumber++
            score -= 15
        }
        if (questionNumber == questions.size) {
            val intent = Intent(this, Activity2::class.java)
            intent.putExtra("score", score)
            intent.putExtra("cheat", cheatNumber)
            intent.putExtra("correct", correctAnswer)
            startActivity(intent)
            finish()
            exitProcess(0)
        }
        if (intent.resolveActivity(packageManager) != null) startActivity(intent)
        textQuestion.text = questions[questionNumber].question
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonTrue.setOnClickListener { onClickButtonTrue() }
        buttonFalse.setOnClickListener { onClickButtonFalse() }
        buttonCheat.setOnClickListener { onClickButtonCheat() }

        if (savedInstanceState != null) {
            questionNumber = savedInstanceState.getInt("question")
            score = savedInstanceState.getInt("score")
            correctAnswer = savedInstanceState.getInt("correct")
            cheatNumber = savedInstanceState.getInt("cheat")
            textQuestion.text = questions[questionNumber].question
        }
        textQuestion.text = questions[questionNumber].question
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("question", questionNumber)
        outState.putInt("score", score)
        outState.putInt("correct", correctAnswer)
        outState.putInt("cheat", cheatNumber)
    }

}