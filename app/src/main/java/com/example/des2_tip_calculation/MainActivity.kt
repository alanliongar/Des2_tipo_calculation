package com.example.des2_tip_calculation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var percentual: TextInputEditText
    private lateinit var valor: TextInputEditText
    private lateinit var resultado: TextView
    private lateinit var qtd_p: TextView
    private lateinit var btnP: Button
    private lateinit var btnM: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        percentual = findViewById(R.id.percent_E)
        valor = findViewById(R.id.valor_E)
        resultado = findViewById(R.id.resultado) //valor final do edittext
        btnP = findViewById(R.id.button_P)
        btnM = findViewById(R.id.button_M)
        qtd_p = findViewById(R.id.tv_qtd)
        var qtdPessoas: Int = 1
        qtd_p.text = qtdPessoas.toString()


        val textwater = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculateAndDisplayResult(qtdPessoas) //Essa função serve pra LITERALMENTE calcular e mostrar o result
            }
        }
        percentual.addTextChangedListener(textwater)
        valor.addTextChangedListener(textwater)//Quando o texto é digitado esse método é chamado.
        btnM.setOnClickListener {
            if (qtdPessoas > 1) {
                qtdPessoas--
                qtd_p.text = qtdPessoas.toString()
                calculateAndDisplayResult(qtdPessoas)
            } else {
                //deixar o botão BRANCO
            }
        }
        btnP.setOnClickListener {
            qtdPessoas++
            qtd_p.text = qtdPessoas.toString()
            calculateAndDisplayResult(qtdPessoas)
        }
    }

    private fun calculateAndDisplayResult(qtd: Int) {
        var percentuall = percentual.text.toString().toFloatOrNull()
        val valorr = valor.text.toString().toFloatOrNull()
        val pessoass = qtd.toFloat()

        if (percentuall != null && valorr != null && pessoass != null) {
            percentuall = percentuall / 100

            var valor_conta: String = ""
            var valor_gorjeta: String = ""
            var valor_total: String = ""
            if (valorr / pessoass < 10) {
                valor_conta = "0" + "%.2f".format(valorr / pessoass)
            } else {
                valor_conta = "" + "%.2f".format(valorr / pessoass)
            }
            if ((valorr / pessoass) * percentuall < 10) {
                valor_gorjeta = "0" + "%.2f".format((valorr / pessoass) * percentuall)
            } else {
                valor_gorjeta = "" + "%.2f".format((valorr / pessoass) * percentuall)
            }
            if ((valorr / pessoass) + (valorr / pessoass) * percentuall < 10) {
                valor_total =
                    "0" + "%.2f".format((valorr / pessoass) + (valorr / pessoass) * percentuall)
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