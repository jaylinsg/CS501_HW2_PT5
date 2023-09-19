package com.bignerdranch.android.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    private lateinit var operand1EditText: EditText
    private lateinit var operand2EditText: EditText
    private lateinit var operationSpinner: Spinner
    private lateinit var resultTextView: TextView
    private lateinit var calculateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        operand1EditText = findViewById(R.id.editTextOperand1)
        operand2EditText = findViewById(R.id.editTextOperand2)
        operationSpinner = findViewById(R.id.spinnerOperation)
        resultTextView = findViewById(R.id.textViewResult)
        calculateButton = findViewById(R.id.buttonCalculate)

        calculateButton.setOnClickListener {
            performArithmeticOperation()
        }
    }

    private fun performArithmeticOperation() {
        val operand1 = operand1EditText.text.toString().toDoubleOrNull()
        val operand2 = operand2EditText.text.toString().toDoubleOrNull()

        if (operand1 == null || operand2 == null) {
            resultTextView.text = getString(R.string.invalid_operand)
            return
        }

        val selectedOperation = operationSpinner.selectedItem.toString()
        val result = calculateResult(operand1, operand2, selectedOperation)

        if (result != null) {
            resultTextView.text = getString(R.string.result_format, result)
        } else {
            resultTextView.text = getString(R.string.invalid_operation)
        }
    }

    private fun calculateResult(operand1: Double, operand2: Double, operation: String): Double? {
        return when (operation) {
            getString(R.string.addition) -> operand1 + operand2
            getString(R.string.subtraction) -> operand1 - operand2
            getString(R.string.multiplication) -> operand1 * operand2
            getString(R.string.division) -> {
                if (operand2 == 0.0) {
                    Toast.makeText(this, R.string.divide_by_zero, Toast.LENGTH_SHORT).show()
                    null
                } else {
                    operand1 / operand2
                }
            }
            getString(R.string.modulus) -> {
                if (operand2 == 0.0) {
                    Toast.makeText(this, R.string.divide_by_zero, Toast.LENGTH_SHORT).show()
                    null
                } else {
                    operand1 % operand2
                }
            }
            else -> null
        }
    }
}
