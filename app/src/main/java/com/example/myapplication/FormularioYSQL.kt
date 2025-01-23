package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FormularioYSQL : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper

    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_formulario)

        // Inicializar la base de datos
        databaseHelper = DatabaseHelper(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nombre: EditText = findViewById(R.id.nombre)
        val edad: EditText = findViewById(R.id.edad)
        val femenino: RadioButton = findViewById(R.id.radioButton)
        val masculino: RadioButton = findViewById(R.id.radioButton2)
        val desplegable: Spinner = findViewById(R.id.spinner)
        val box1: CheckBox = findViewById(R.id.checkBox)
        val box2: CheckBox = findViewById(R.id.checkBox2)
        val box3: CheckBox = findViewById(R.id.checkBox3)
        val btnGuardar: Button = findViewById(R.id.btn)
        val btnLimpiar: Button = findViewById(R.id.btn2)
        val contenedor: TextView = findViewById(R.id.contenedor)

        val profesiones = arrayOf("Abogado", "Profesor", "Carpintero", "Electricista")

        desplegable.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, profesiones)

        btnGuardar.setOnClickListener {
            var texto = ""

            val nombrePersona = nombre.text.toString()
            val edadPersona = edad.text.toString().toIntOrNull() ?: 0
            val sexoPersona = if (femenino.isChecked) "Femenino" else if (masculino.isChecked) "Masculino" else "No especificado"
            val profesionPersona = desplegable.selectedItem.toString()

            // Recopilar los hobbies seleccionados
            val hobbiesList = mutableListOf<String>()
            if (box1.isChecked) hobbiesList.add(box1.text.toString())
            if (box2.isChecked) hobbiesList.add(box2.text.toString())
            if (box3.isChecked) hobbiesList.add(box3.text.toString())

            val hobbiesPersona = hobbiesList.joinToString(", ")

            // Guardar datos en la base de datos
            databaseHelper.insertPerson(
                nombre = nombrePersona,
                edad = edadPersona,
                sexo = sexoPersona,
                profesion = profesionPersona,
                hobbies = hobbiesPersona
            )

            // Mostrar los datos en el contenedor
            texto += "Nombre: $nombrePersona\n"
            texto += "Edad: $edadPersona\n"
            texto += "Sexo: $sexoPersona\n"
            texto += "Profesión: $profesionPersona\n"
            texto += "Hobbies: $hobbiesPersona\n"
            contenedor.text = texto

            // Limpiar los campos
            nombre.text.clear()
            edad.text.clear()
            femenino.isChecked = false
            masculino.isChecked = false
            box1.isChecked = false
            box2.isChecked = false
            box3.isChecked = false
            desplegable.setSelection(0)

            Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show()
        }

        btnLimpiar.setOnClickListener {
            nombre.text.clear()
            edad.text.clear()
            femenino.isChecked = false
            masculino.isChecked = false
            box1.isChecked = false
            box2.isChecked = false
            box3.isChecked = false
            desplegable.setSelection(0)
            contenedor.text = ""
        }
    }
}
