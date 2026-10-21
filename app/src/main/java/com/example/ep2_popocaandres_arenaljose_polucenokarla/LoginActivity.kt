package com.example.ep2_popocaandres_arenaljose_polucenokarla

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// Pantalla para iniciar sesion
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val txtVolver = findViewById<TextView>(R.id.txtVolver)
        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        val etContrasena = findViewById<EditText>(R.id.etContrasena)
        val btnIniciar = findViewById<Button>(R.id.btnIniciar)
        val txtCrearCuenta = findViewById<TextView>(R.id.txtCrearCuenta)

        val db = BaseDatos(this)

        txtVolver.setOnClickListener {
            finish()
        }

        // Abre la pantalla de registro
        txtCrearCuenta.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        btnIniciar.setOnClickListener {
            val usuario = etUsuario.text.toString()
            val contrasena = etContrasena.text.toString()

            if (usuario == "" || contrasena == "") {
                Toast.makeText(this, "Escribe tu usuario y contraseña", Toast.LENGTH_SHORT).show()
            } else if (db.iniciarSesion(usuario, contrasena)) {
                Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()

                // Abre el perfil y le manda el usuario
                val intent = Intent(this, PerfilActivity::class.java)
                intent.putExtra("usuario", usuario)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
