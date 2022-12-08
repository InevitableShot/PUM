package pl.edu.uwr.pum

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.system.exitProcess

class Activity2 : AppCompatActivity() {
    // Connecting values with elements on screen
    private val result: TextView by lazy { findViewById(R.id.wynik) }
    private val buttonEnd: Button by lazy { findViewById(R.id.buttonEnd) }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        // Getting values from previous activity, so we can show results
        val score = intent.getIntExtra("score", 0)
        val correct = intent.getIntExtra("correct", 0)
        val cheats = intent.getIntExtra("cheat", 0)

        // Showing final results
        result.text = "Twój wynik: $score\nPoprawne odpowiedzi: $correct\nOszustwa: $cheats"

        // Button on click to quit application
        buttonEnd.setOnClickListener {
            finish()
            exitProcess(0)
        }
    }
}