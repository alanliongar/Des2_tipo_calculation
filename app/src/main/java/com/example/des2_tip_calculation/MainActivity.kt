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
            val valor_conta = "%.1f".format(valorr / pessoass)
            val valor_gorjeta = "%.1f".format((valorr / pessoass) * percentuall)
            val valor_total = "%.1f".format((valorr / pessoass) + (valorr / pessoass) * percentuall)

            resultado.text = "$valor_conta || $valor_gorjeta || $valor_total"
        } else {
            resultado.text = ""
        }
    }
}