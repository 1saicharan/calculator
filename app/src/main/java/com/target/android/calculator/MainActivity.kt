package com.target.android.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private var flag = false
    private lateinit var resultTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultTextView = findViewById<TextView>(R.id.result)
        val off = findViewById<Button>(R.id.off)
        val c = findViewById<Button>(R.id.c)
        val equal = findViewById<Button>(R.id.equal)
        off.setOnClickListener {
            resultTextView.text = ""
        }
        c.setOnClickListener {
            resultTextView.text = ""
        }
        equal.setOnClickListener{
            calculate()
            flag = true
        }
        val buttonMap = mapOf(
            R.id.zero to "0",
            R.id.one to "1",
            R.id.two to "2",
            R.id.three to "3",
            R.id.four to "4",
            R.id.five to "5",
            R.id.six to "6",
            R.id.seven to "7",
            R.id.eight to "8",
            R.id.nine to "9",
            R.id.dot to ".",
            R.id.percent to "%",
            R.id.divide to "÷",
            R.id.multiply to "×",
            R.id.minus to "-",
            R.id.plus to "+"
        )

        // Attach click listeners to each button
        for ((id, value) in buttonMap) {
            findViewById<Button>(id).setOnClickListener {
                appendToTextView(value, resultTextView)
            }
        }

    }

    private fun appendToTextView(value: String, textView: TextView?) {
        if(flag || resultTextView.text.toString() =="Error")resultTextView.text = ""
        flag = false
        val currentText = textView?.text.toString()
        // Prevent multiple dots in the text
        if (value.last() in arrayOf('.','%','÷','×','-','+') && currentText.last() in arrayOf('.','%','÷','×','-','+')
        ) {
            return
        }
        // If the current text is '0', replace it with the first digit
        if (currentText == "0" && value != ".") {
            textView?.text = value
        } else {
            textView?.text = currentText + value
        }
    }
    private fun calculate() {
        val expression = resultTextView.text.toString().replace("×", "*").replace("÷", "/")

        try {
            // You can use a library to evaluate the expression
            val result = ExpressionBuilder(expression).build().evaluate()
            resultTextView.text = result.toString()
        } catch (e: Exception) {
            resultTextView.text = "Error"
        }
    }
    }


