package com.example.daftarbelanja

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class ActivityUbah : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubah)

        val actionBar = supportActionBar
        if (intent.hasExtra("nama")){
            actionBar?.title = intent.getStringExtra("nama")
        }

        val btnUpdate = findViewById<Button>(R.id.btn_update)

        getIntentData()

        btnUpdate.setOnClickListener{
            val db = MyDBHelper(this)

            val idDaftar = intent.getStringExtra("id")
            val namaDaftar = findViewById<EditText>(R.id.txt_editnama).text.toString()
            val jumlahDaftar = findViewById<EditText>(R.id.txt_editjmlh).text.toString()

            db.ubahDaftar(idDaftar,namaDaftar,jumlahDaftar)

        }

        val btnHapus =  findViewById<Button>(R.id.btn_hapus)
        btnHapus.setOnClickListener{
            dialogKonf()
        }
    }

    fun getIntentData(){
        if (
            intent.hasExtra("id") && intent.hasExtra("nama") && intent.hasExtra("jumlah")
        ){
            val idDaftar = intent.getStringExtra("id")
            val namaDaftar = intent.getStringExtra("nama")
            val jumlahDaftar = intent.getStringExtra("jumlah")

            val txtNama = findViewById<EditText>(R.id.txt_editnama)
            val txtJumlah = findViewById<EditText>(R.id.txt_editjmlh)

            txtNama.setText(namaDaftar)
            txtJumlah.setText(jumlahDaftar)
        } else {
            Toast.makeText(this, "Tidak ada data!", Toast.LENGTH_SHORT).show()
        }
    }

    fun dialogKonf(){
        val idDaftar = intent.getStringExtra("id")
        val namaDaftar = intent.getStringExtra("nama")

        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Delete " + namaDaftar + " ?")
        alertDialog.setMessage("Apakah anda yakin menghapus " + namaDaftar + " ?")
        alertDialog.setPositiveButton("Iya ", DialogInterface.OnClickListener { dialogInterface, i ->
            val db = MyDBHelper(this)
            db.hapusDaftar(idDaftar)
            startActivity(Intent(this, ActivityIsi::class.java))
        })

        alertDialog.setNegativeButton("Tidak ", DialogInterface.OnClickListener { dialogInterface, i ->  })
        alertDialog.create().show()
    }
}