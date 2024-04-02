package com.example.des2_tip_calculation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var percentual: TextInputEditText
    private lateinit var valor: TextInputEditText
    private lateinit var pessoas: TextInputEditText
    private lateinit var resultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        percentual = findViewById(R.id.percent_E)
        valor = findViewById(R.id.valor_E)
        pessoas = findViewById(R.id.pessoas_E)
        resultado = findViewById(R.id.resultado) //valor final do edittext

        val textwater = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculateAndDisplayResult()
            }
        }
        percentual.addTextChangedListener(textwater)
        valor.addTextChangedListener(textwater)
        pessoas.addTextChangedListener(textwater)
    }

    private fun calculateAndDisplayResult() {
        var percentuall = percentual.text.toString().toFloatOrNull()
        val valorr = valor.text.toString().toFloatOrNull()
        val pessoass = pessoas.text.toString().toFloatOrNull()

        if (percentuall != null && valorr != null && pessoass != null) {
            percentuall = percentuall / 100

            var valor_conta: String = ""
            var valor_gorjeta: String = ""
            var valor_total: String = ""
            if (valorr / pessoass < 10) {
                valor_conta = "0"+"%.2f".format(valorr / pessoass)
            } else {
                valor_conta = ""+"%.2f".format(valorr / pessoass)
            }
            if ((valorr / pessoass) * percentuall < 10) {
                valor_gorjeta = "0"+"%.2f".format((valorr / pessoass) * percentuall)
            } else {
                valor_gorjeta = ""+"%.2f".format((valorr / pessoass) * percentuall)
            }
            if ((valorr / pessoass) + (valorr / pessoass) * percentuall < 10) {
                valor_total = "0"+"%.2f".format((valorr / pessoass) + (valorr / pessoass) * percentuall)
            } else {
                valor_total = "%.2f".format((valorr / pessoass) + (valorr / pessoass) * percentuall)
            }
            resultado.text =
                "R$ $valor_conta\n\nR$ $valor_gorjeta\n\nR$ $valor_total"
        } else {
            resultado.text = ""
        }
    }
}