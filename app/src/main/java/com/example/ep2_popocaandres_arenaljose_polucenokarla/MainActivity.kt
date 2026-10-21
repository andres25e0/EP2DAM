package com.example.ep2_popocaandres_arenaljose_polucenokarla

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

// Pantalla de inicio
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnEmpezar = findViewById<Button>(R.id.btnEmpezar)

        // Abre la pantalla de inicio de sesion
        btnEmpezar.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
