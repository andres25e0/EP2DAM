package com.example.ep2_popocaandres_arenaljose_polucenokarla

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// Pantalla del perfil del usuario
class PerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val txtSalir = findViewById<TextView>(R.id.txtSalir)
        val txtApodo = findViewById<TextView>(R.id.txtApodo)
        val imgFoto = findViewById<ImageView>(R.id.imgFoto)
        val txtUsuario = findViewById<TextView>(R.id.txtUsuario)
        val txtNivel = findViewById<TextView>(R.id.txtNivel)
        val txtCorreo = findViewById<TextView>(R.id.txtCorreo)
        val btnEstudiar = findViewById<Button>(R.id.btnEstudiar)

        // Usuario que llega desde el login
        val usuario = intent.getStringExtra("usuario").toString()
        txtUsuario.text = usuario

        // Se buscan sus datos en la base de datos
        val db = BaseDatos(this)
        val cursor = db.buscarUsuario(usuario)

        if (cursor.moveToFirst()) {
            txtApodo.text = cursor.getString(0)
            txtNivel.text = "Nivel académico: " + cursor.getString(1)
            txtCorreo.text = "Correo: " + cursor.getString(2)

            val foto = cursor.getInt(3)
            if (foto == 1) {
                imgFoto.setImageResource(R.drawable.avatar1)
            } else if (foto == 2) {
                imgFoto.setImageResource(R.drawable.avatar2)
            } else {
                imgFoto.setImageResource(R.drawable.avatar3)
            }
        }
        cursor.close()

        txtSalir.setOnClickListener {
            finish()
        }

        btnEstudiar.setOnClickListener {
            Toast.makeText(this, "A estudiar, " + txtApodo.text, Toast.LENGTH_SHORT).show()
        }
    }
}
