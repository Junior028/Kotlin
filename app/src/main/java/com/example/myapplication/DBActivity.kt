package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "PersonDatabase", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE Persons (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, edad INTEGER, sexo TEXT, profesion TEXT, hobbies TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS Persons")
        onCreate(db)
    }

    // Método para insertar una persona en la base de datos
    fun insertPerson(nombre: String, edad: Int, sexo: String, profesion: String, hobbies: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("nombre", nombre)
            put("edad", edad)
            put("sexo", sexo)
            put("profesion", profesion)
            put("hobbies", hobbies)
        }
        db.insert("Persons", null, values)
        db.close() // Cerrar la conexión después de escribir
    }

    // Método para obtener todas las personas de la base de datos
    fun getAllPersons(): List<Person> {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Persons", null)
        val personList = mutableListOf<Person>()

        if (cursor.moveToFirst()) {
            do {
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
                val edad = cursor.getInt(cursor.getColumnIndexOrThrow("edad"))
                val sexo = cursor.getString(cursor.getColumnIndexOrThrow("sexo"))
                val profesion = cursor.getString(cursor.getColumnIndexOrThrow("profesion"))
                val hobbies = cursor.getString(cursor.getColumnIndexOrThrow("hobbies"))
                personList.add(Person(nombre, edad, sexo, profesion, hobbies))
            } while (cursor.moveToNext())
        }
        cursor.close() // Cerrar el cursor
        db.close() // Cerrar la conexión después de leer
        return personList
    }
}