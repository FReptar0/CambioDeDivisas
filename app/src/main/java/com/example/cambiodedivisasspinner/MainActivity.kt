package com.example.cambiodedivisasspinner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.cambiodedivisasspinner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sp1 = binding.sp1
        val sp2 = binding.sp2
        val btnEnviar = binding.btnEnviar
        var resultado = binding.resultado
        var ediText = binding.ediTxt
        val lista = resources.getStringArray(R.array.divisas)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista)

        var posicionSP1 = ""
        var posicionSP2 = ""

        var total = 0.0

        sp1.adapter = adapter
        sp2.adapter = adapter

        sp1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                posicionSP1 = lista[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(
                    this@MainActivity,
                    "No se seleccionaron las divisas de origen",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
        sp2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                posicionSP2 = lista[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(
                    this@MainActivity,
                    "No se seleccionaron las divisas de destino",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        btnEnviar.setOnClickListener {
            if (ediText.text.isNotEmpty()) {
                when (posicionSP1) {
                    "MXN" -> {
                        when (posicionSP2) {
                            "MXN" -> {
                                resultado.text = ediText.text
                            }
                            "USD" -> {
                                total = ediText.text.toString().toFloat() * 0.050
                                resultado.text = total.toString()
                            }
                            "EUR" -> {
                                total = ediText.text.toString().toFloat() * 0.051
                                resultado.text = total.toString()
                            }
                        }
                    }
                    "USD" -> {
                        when (posicionSP2) {
                            "MXN" -> {
                                total = ediText.text.toString().toFloat() * 20.02
                                resultado.text = total.toString()
                            }
                            "USD" -> {
                                resultado.text = ediText.text
                            }
                            "EUR" -> {
                                total = ediText.text.toString().toFloat() * 1.03
                                resultado.text = total.toString()
                            }
                        }
                    }
                    "EUR" -> {
                        when (posicionSP2) {
                            "MXN" -> {
                                total = ediText.text.toString().toFloat() * 19.50
                                resultado.text = total.toString()
                            }
                            "USD" -> {
                                total = ediText.text.toString().toFloat() * 0.97
                                resultado.text = total.toString()
                            }
                            "EUR" -> {
                                resultado.text = ediText.text
                            }
                        }
                    }
                }
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "No puede dejar el input vacio",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}