package com.example.ep2_popocaandres_arenaljose_polucenokarla

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// Pantalla para crear una cuenta
class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val txtVolver = findViewById<TextView>(R.id.txtVolver)
        val txtIrLogin = findViewById<TextView>(R.id.txtIrLogin)
        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        val etContrasena = findViewById<EditText>(R.id.etContrasena)
        val etNivel = findViewById<EditText>(R.id.etNivel)
        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val etApodo = findViewById<EditText>(R.id.etApodo)
        val imgFoto = findViewById<ImageView>(R.id.imgFoto)
        val imgFoto1 = findViewById<ImageView>(R.id.imgFoto1)
        val imgFoto2 = findViewById<ImageView>(R.id.imgFoto2)
        val imgFoto3 = findViewById<ImageView>(R.id.imgFoto3)
        val btnCrear = findViewById<Button>(R.id.btnCrear)

        val db = BaseDatos(this)

        // Numero de la foto que elige el usuario
        var foto = 1

        txtVolver.setOnClickListener {
            finish()
        }

        txtIrLogin.setOnClickListener {
            finish()
        }

        imgFoto1.setOnClickListener {
            foto = 1
            imgFoto.setImageResource(R.drawable.avatar1)
        }

        imgFoto2.setOnClickListener {
            foto = 2
            imgFoto.setImageResource(R.drawable.avatar2)
        }

        imgFoto3.setOnClickListener {
            foto = 3
            imgFoto.setImageResource(R.drawable.avatar3)
        }

        btnCrear.setOnClickListener {
            val usuario = etUsuario.text.toString()
            val contrasena = etContrasena.text.toString()
            val nivel = etNivel.text.toString()
            val correo = etCorreo.text.toString()
            val apodo = etApodo.text.toString()

            if (usuario == "" || contrasena == "" || nivel == "" || correo == "" || apodo == "") {
                Toast.makeText(this, "Llena todos los campos", Toast.LENGTH_SHORT).show()
            } else if (db.existeUsuario(usuario)) {
                Toast.makeText(this, "Ese usuario ya existe", Toast.LENGTH_SHORT).show()
            } else {
                val guardado = db.registrar(usuario, contrasena, nivel, correo, apodo, foto)

                if (guardado) {
                    Toast.makeText(this, "Cuenta creada", Toast.LENGTH_SHORT).show()
                    // Regresa a la pantalla de inicio de sesion
                    finish()
                } else {
                    Toast.makeText(this, "Error al crear la cuenta", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
