package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_formulario)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var Nombre: EditText =findViewById(R.id.nombre)
        var  Edad : EditText=findViewById(R.id.edad)
        var Femenino : RadioButton=findViewById(R.id.radioButton)
        var Masculino : RadioButton=findViewById(R.id.radioButton2)
        var desplegable: Spinner =findViewById(R.id.spinner)
        var box1 : CheckBox=findViewById(R.id.checkBox)
        var box2 : CheckBox=findViewById(R.id.checkBox2)
        var box3 : CheckBox=findViewById(R.id.checkBox3)
        var btn1 : Button=findViewById(R.id.btn)
        var btn2 : Button=findViewById(R.id.btn2)
        var Contenedor : TextView=findViewById(R.id.contenedor)

        var Texto: String=""

        val profesiones = arrayOf("Abogado", "Profesor", "Carpintero", "Electricista")

        desplegable.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, profesiones)

        btn1.setOnClickListener{
            Texto+=Nombre.text.toString()+"\n"
            Texto+=Edad.text.toString()+"\n"

            if(Femenino.isChecked){
                Texto+="Sexo : Femenino \n"
            }
            if(Masculino.isChecked){
                Texto+="Sexo: Masculino \n"
            }

            val textoSeleccionado = "Profesion: "+desplegable.selectedItem.toString()
            Texto+=textoSeleccionado+"\n"

            if(box1.isChecked){
                Texto+="Hobbie : "+box1.text+"\n"
            }
            if(box2.isChecked){
                Texto+="Hobbie : "+box2.text+"\n"
            }
            if(box3.isChecked){
                Texto+="Hobbie : "+box3.text+"\n"
            }
            Contenedor.text=Texto
            if (Texto.isNotEmpty()) {
                // Crea intent para lanzar ResultActivity
                val intent = Intent(this, MostrarHobbies::class.java)
                // Paso de información name
                intent.putExtra("EXTRA_NAME",Texto)
                // Lanza la segunda actividad
                startActivity(intent)
            }

            Texto=""

        }
        btn2.setOnClickListener{
            Nombre.text.clear()
            Edad.text.clear()
            Femenino.isChecked=false
            Masculino.isChecked=false
            box1.isChecked=false
            box2.isChecked=false
            box3.isChecked=false
            Contenedor.text=""
            desplegable.setSelection(0)

        }
    }
}