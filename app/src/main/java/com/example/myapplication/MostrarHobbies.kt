package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MostrarHobbies : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mostrar_hobbies)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var Atras : Button=findViewById(R.id.buttonAtras)
        val tvResult: TextView = findViewById(R.id.textMostrarHobbies)
        // Nos da valor de la clave EXTRA_NAME si no es vacío o vacío en caso contrario
        val name: String = intent.extras?.getString("EXTRA_NAME").orEmpty()
        tvResult.setText(name) // Usar setText para asignar el texto

        Atras.setOnClickListener{
            finish()
        }
    }
}