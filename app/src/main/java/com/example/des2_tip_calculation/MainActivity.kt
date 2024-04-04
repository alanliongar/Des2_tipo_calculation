package com.example.des2_tip_calculation

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var valor: TextInputEditText
    private lateinit var resultado: TextView
    private lateinit var qtd_p: TextView
    private lateinit var btnP: Button
    private lateinit var btnM: Button
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button
    private lateinit var btn5: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        valor = findViewById(R.id.valor_E)
        resultado = findViewById(R.id.resultado) //valor final do edittext
        btnP = findViewById(R.id.button_P)
        btnM = findViewById(R.id.button_M)
        qtd_p = findViewById(R.id.tv_qtd)
        btn1 = findViewById(R.id.dez)
        btn2 = findViewById(R.id.quinze)
        btn3 = findViewById(R.id.vinte)
        btn4 = findViewById(R.id.vincinco)
        btn5 = findViewById(R.id.outros)

        val botoes = listOf(btn1,btn2,btn3,btn4,btn5)
        var perc: Float = 0.0f
        var qtdPessoas: Int = 1
        qtd_p.text = qtdPessoas.toString()


        val textwater = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculateAndDisplayResult(qtdPessoas, perc) //Essa função serve pra LITERALMENTE calcular e mostrar o result
            }
        }
        valor.addTextChangedListener(textwater)//Quando o texto é digitado esse método é chamado.
        btnM.setOnClickListener {
            if (qtdPessoas > 1) {
                qtdPessoas--
                qtd_p.text = qtdPessoas.toString()
                calculateAndDisplayResult(qtdPessoas, perc)
            } else {
                //deixar o botão BRANCO
            }
        }
        btnP.setOnClickListener {
            qtdPessoas++
            qtd_p.text = qtdPessoas.toString()
            calculateAndDisplayResult(qtdPessoas,perc)
        }
        btn1.setOnClickListener {
            perc = 0.10f
            pintar(btn1,botoes)
            calculateAndDisplayResult(qtdPessoas,perc)
        }
        btn2.setOnClickListener {
            perc = 0.15f
            pintar(btn2,botoes)
            calculateAndDisplayResult(qtdPessoas,perc)
        }
        btn3.setOnClickListener {
            perc = 0.20f
            pintar(btn3,botoes)
            calculateAndDisplayResult(qtdPessoas,perc)
        }
        btn4.setOnClickListener {
            perc = 0.25f
            pintar(btn4,botoes)
            calculateAndDisplayResult(qtdPessoas,perc)
        }
        btn5.setOnClickListener {
            perc = 0.30f
            pintar(btn5,botoes)
            calculateAndDisplayResult(qtdPessoas,perc)
        }
    }

    private fun calculateAndDisplayResult(qtd: Int, per: Float) {
        var percentuall = per
        val valorr = valor.text.toString().toFloatOrNull()
        val pessoass = qtd.toFloat()

        if (valorr != null) {
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
            resultado.text = "R$ 00,00\n\nR$ 00,00\n\nR$ 00,00"
        }
    }
}

private fun pintar(btnSel:Button, todos:List<Button>){

todos.forEach{botao ->
    if (botao == btnSel){
        botao.setBackgroundColor(Color.GREEN)
    }else{
        botao.setBackgroundColor(Color.MAGENTA)
    }
}




}