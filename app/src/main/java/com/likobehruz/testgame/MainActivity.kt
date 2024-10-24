package com.likobehruz.testgame

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var txt_1: TextView
    private lateinit var rad_group: RadioGroup
    private lateinit var btn_next: Button
    private lateinit var rad_a: RadioButton
    private lateinit var rad_b: RadioButton
    private lateinit var rad_c: RadioButton
    private lateinit var rad_d: RadioButton

    private var javob = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txt_1 = findViewById(R.id.txt_1)
        rad_group = findViewById(R.id.rad_group)
        btn_next = findViewById(R.id.btn_next)
        rad_a = findViewById(R.id.rad_a)
        rad_b = findViewById(R.id.rad_b)
        rad_c = findViewById(R.id.rad_c)
        rad_d = findViewById(R.id.rad_d)

        randomizeQuestion()

        btn_next.setOnClickListener {
            val selectedId = rad_group.checkedRadioButtonId
            val selectedRadioButton = findViewById<RadioButton>(selectedId)

            if (selectedRadioButton != null) {
                if (selectedRadioButton.text.toString().toInt() == javob) {
                    Toast.makeText(this, "✅ Correct!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "❌ Incorrect! The correct answer is $javob", Toast.LENGTH_SHORT).show()
                }
                randomizeQuestion()
            } else {
                Toast.makeText(this, "Please select an answer.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun randomizeQuestion() {
        val number1 = Random.nextInt(20)
        val number2 = Random.nextInt(1, 20)  // Avoid zero for division
        val operation = Random.nextInt(4)

        when (operation) {
            0 -> {
                txt_1.text = "$number1 + $number2 ="
                javob = number1 + number2
            }
            1 -> {
                txt_1.text = "$number1 - $number2 ="
                javob = number1 - number2
            }
            2 -> {
                txt_1.text = "$number1 * $number2 ="
                javob = number1 * number2
            }
            3 -> {
                txt_1.text = "$number1 / $number2 ="
                javob = number1 / number2
            }
        }
        updateRadioButtons()
    }

    private fun updateRadioButtons() {
        val incorrectAnswers = mutableSetOf<Int>()
        while (incorrectAnswers.size < 3) {
            val wrongAnswer = Random.nextInt(0, 40) // Ensure it's not the correct answer
            if (wrongAnswer != javob) {
                incorrectAnswers.add(wrongAnswer)
            }
        }

        val answers = incorrectAnswers.toList() + javob
        val shuffledAnswers = answers.shuffled()

        rad_a.text = shuffledAnswers[0].toString()
        rad_b.text = shuffledAnswers[1].toString()
        rad_c.text = shuffledAnswers[2].toString()
        rad_d.text = shuffledAnswers[3].toString()
    }
}
