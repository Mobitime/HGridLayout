package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private lateinit var editTextInput: EditText
    private lateinit var textViewOutput: TextView
    private var expression: String = ""
    private lateinit var toolbarN: Toolbar

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        toolbarN = findViewById(R.id.toolbarN)
        setSupportActionBar(toolbarN)
        title = "Калькулятор"



        editTextInput = findViewById(R.id.editTextInput)
        textViewOutput = findViewById(R.id.textViewOutput)


        val buttonIds = listOf(
            R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8,
            R.id.button9, R.id.button0, R.id.buttonPlus, R.id.buttonMinus,
            R.id.buttonDivide, R.id.buttonMultiply, R.id.buttonEqual, R.id.buttonReset
        )

        for (buttonId in buttonIds) {
            val button: Button = findViewById(buttonId)
            button.setOnClickListener {
                when (buttonId) {
                    R.id.buttonEqual -> calculateResult()
                    R.id.buttonReset -> resetFields()
                    else -> appendToExpression(button.text.toString())
                }
            }
        }
    }

    private fun appendToExpression(value: String) {
        expression += value
        editTextInput.setText(expression)
    }

    private fun calculateResult() {
        try {
            val result = evaluateExpression(expression)
            textViewOutput.text = result.toString()
        } catch (e: Exception) {
            textViewOutput.text = "Ошибка"
        }
    }

    private fun resetFields() {
        expression = ""
        editTextInput.setText("")
        textViewOutput.text = "Результат"
    }

    private fun evaluateExpression(expr: String): Double {
        val expression = expr.replace(" ", "")
        return when {
            expression.contains("+") -> {
                val parts = expression.split("+")
                parts[0].toDouble() + parts[1].toDouble()
            }
            expression.contains("-") -> {
                val parts = expression.split("-")
                parts[0].toDouble() - parts[1].toDouble()
            }
            expression.contains("*") -> {
                val parts = expression.split("*")
                parts[0].toDouble() * parts[1].toDouble()
            }
            expression.contains("/") -> {
                val parts = expression.split("/")
                parts[0].toDouble() / parts[1].toDouble()
            }
            else -> 0.0
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_exit -> {
                Toast.makeText(this, "Выход", Toast.LENGTH_SHORT).show()
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
