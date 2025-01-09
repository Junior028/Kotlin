package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Examen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_examen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var Nombre: EditText =findViewById(R.id.editTextNombre)
        var  Hobbies : EditText=findViewById(R.id.editTextHobbies)
        var Deportes : CheckBox=findViewById(R.id.checkBoxExamen)
        var Lectura : CheckBox=findViewById(R.id.checkBoxExamen2)
        var Cine : CheckBox=findViewById(R.id.checkBoxExamen3)
        var Otros : CheckBox=findViewById(R.id.checkBoxExamen4)
        var Enviar : Button=findViewById(R.id.button2)
        var Limpiar : Button=findViewById(R.id.button3)
        var Contenedor : TextView=findViewById(R.id.textViewExamen3)

        var Texto: String=""//Texto para luego insertarlo al textview
        var TextoHobbies: String=""//Cogemos el texto de los hobbbies
        var contador:Int=0//Un contador para los hobbies
        Enviar.setOnClickListener{
            Texto+="Eres ${Nombre.text.toString()} "//Metemos el nombre
            if(Deportes.isChecked){//Si esta checkeado aumentamos contador y añadimos texto
                contador++;
                TextoHobbies+="deportes,"
            }
            if(Lectura.isChecked){
                contador++;
                TextoHobbies+="lectura,"
            }
            if(Cine.isChecked){
                contador++;
                TextoHobbies+="Cine,"
            }
            if(Otros.isChecked){
                contador++;
                TextoHobbies+=Hobbies.text.toString()
            }
            if(contador==0){//Es cuando no tienes ninguna aficion
                Texto+=getString(R.string.Sin_aficiones)//Se ha usado String.xml
            }
            else if(contador!=0){//Se activa si tocas una checkbox por lo que tienes una aficion
                Texto+=getString(R.string.Con_aficiones)//Literacion
                Texto+=" $contador aficiones:$TextoHobbies ."
            }

            Contenedor.text=Texto//Vaciamos el textview ,contador y textos auxiliares
            Texto=""
            TextoHobbies=""
            contador=0

        }
        Limpiar.setOnClickListener{//Limpiamos en pantalla
            Nombre.text.clear()
            Hobbies.text.clear()
            Deportes.isChecked=false
            Lectura.isChecked=false
            Cine.isChecked=false
            Otros.isChecked=false
            Contenedor.text=""

        }

    }
}