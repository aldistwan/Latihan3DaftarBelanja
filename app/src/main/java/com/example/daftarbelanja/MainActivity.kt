package com.example.daftarbelanja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnisi  =  findViewById<Button>(R.id.btn_isi)

        btnisi.setOnClickListener{
            val intentKita = Intent(this, ActivityIsi::class.java)
            startActivity(intentKita)
        }


        val btnTentang = findViewById<Button>(R.id.btn_tentang)
        btnTentang.setOnClickListener{
            Toast.makeText(applicationContext, "Dibuat oleh Aldi Setiawan (8020180280)", Toast.LENGTH_SHORT).show()
        }
    }
}