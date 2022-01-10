package com.example.daftarbelanja

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ActivityTambah : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah)

        val txtNama = findViewById<EditText>(R.id.txt_nama)
        val txtJumlah = findViewById<EditText>(R.id.txt_jmlh)
        val btnSimpan = findViewById<Button>(R.id.btn_simpan)

        btnSimpan.setOnClickListener{
            val dbSaya = MyDBHelper(this)

            dbSaya.tambahDaftar(
                txtNama.text.toString().trim(),
                Integer.valueOf(txtJumlah.text.toString().trim())
            )
        }
    }
}