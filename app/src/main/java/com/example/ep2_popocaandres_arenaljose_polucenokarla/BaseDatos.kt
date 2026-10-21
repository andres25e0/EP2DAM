package com.example.ep2_popocaandres_arenaljose_polucenokarla

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// Base de datos local de la app (SQLite)
class BaseDatos(context: Context) : SQLiteOpenHelper(context, "tustudio.db", null, 1) {

    // Se crea la tabla de usuarios
    override fun onCreate(db: SQLiteDatabase) {
        val tabla = "CREATE TABLE usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "usuario TEXT UNIQUE, " +
                "contrasena TEXT, " +
                "nivel TEXT, " +
                "correo TEXT, " +
                "apodo TEXT, " +
                "foto INTEGER)"
        db.execSQL(tabla)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS usuarios")
        onCreate(db)
    }

    // Guarda un usuario nuevo
    fun registrar(usuario: String, contrasena: String, nivel: String, correo: String, apodo: String, foto: Int): Boolean {
        val db = writableDatabase
        val datos = ContentValues()
        datos.put("usuario", usuario)
        datos.put("contrasena", contrasena)
        datos.put("nivel", nivel)
        datos.put("correo", correo)
        datos.put("apodo", apodo)
        datos.put("foto", foto)

        val resultado = db.insert("usuarios", null, datos)

        // Si es -1 hubo un error
        return resultado != -1L
    }

    // Revisa si el usuario ya existe
    fun existeUsuario(usuario: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM usuarios WHERE usuario = ?", arrayOf(usuario))
        val existe = cursor.count > 0
        cursor.close()
        return existe
    }

    // Revisa si el usuario y la contrasena son correctos
    fun iniciarSesion(usuario: String, contrasena: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM usuarios WHERE usuario = ? AND contrasena = ?",
            arrayOf(usuario, contrasena)
        )
        val correcto = cursor.count > 0
        cursor.close()
        return correcto
    }

    // Trae los datos del usuario para el perfil
    fun buscarUsuario(usuario: String): Cursor {
        val db = readableDatabase
        return db.rawQuery(
            "SELECT apodo, nivel, correo, foto FROM usuarios WHERE usuario = ?",
            arrayOf(usuario)
        )
    }
}
