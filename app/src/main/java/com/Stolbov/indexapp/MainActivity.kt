package com.Stolbov.indexapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private lateinit var buttonResult: Button
    private lateinit var editHeight: EditText
    private lateinit var editWeight: EditText
    private lateinit var textResult: TextView

    private val resultText = "Индекс массы тела:  "
    private val arrayWithAnnotation = arrayOf("Выраженный дефицит массы тела",
        "Недостаточная масса тела",
        "Норма",
        "Избыточная масса тела(предшествует ожирению)",
        "Ожирение 1-й степени",
        "Ожирение 2-й степени",
        "Ожирение 3-й степени")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonResult = findViewById(R.id.button_result)
        editHeight = findViewById(R.id.edit_height)
        editWeight = findViewById(R.id.edit_weight)
        textResult = findViewById(R.id.text_view_result)

        buttonResult.setOnClickListener {

            if (editHeight.text.toString() == "" || editWeight.text.toString() == ""){
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (editHeight.text.toString().toInt() <= 0 || editWeight.text.toString().toInt() <= 0) {
                Toast.makeText(this, "Некорректное значение", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val ibm: Double = editWeight.text.toString().toDouble() / (editHeight.text.toString().toDouble().pow(2) / 10000)

            if (ibm <= 16)
                textResult.text = resultText + String.format("%.2f", ibm) + "\n" + arrayWithAnnotation[0]
            else if (ibm > 16 && ibm <= 18.5)
                textResult.text = resultText + String.format("%.2f", ibm) + "\n" + arrayWithAnnotation[1]
            else if (ibm > 18.5 && ibm <= 25)
                textResult.text = resultText + String.format("%.2f", ibm) + "\n" + arrayWithAnnotation[2]
            else if (ibm > 25 && ibm <= 30)
                textResult.text = resultText + String.format("%.2f", ibm) + "\n" + arrayWithAnnotation[3]
            else if (ibm > 30 && ibm <= 35)
                textResult.text = resultText + String.format("%.2f", ibm) + "\n" + arrayWithAnnotation[4]
            else if (ibm > 35 && ibm <= 40)
                textResult.text = resultText + String.format("%.2f", ibm) + "\n" + arrayWithAnnotation[5]
            else
                textResult.text = resultText + String.format("%.2f", ibm) + "\n" + arrayWithAnnotation[6]

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.run {
            putString("KEY", textResult.text.toString())
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        textResult.text = savedInstanceState.getString("KEY")
    }
}