package com.example.daftarbelanja

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ActivityIsi : AppCompatActivity() {

    val daftar_id: ArrayList<String> = arrayListOf()
    val daftar_nama: ArrayList<String> = arrayListOf()
    val daftar_jumlah: ArrayList<String> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_isi)

        val btnAdd = findViewById<FloatingActionButton>(R.id.fab_add)
        btnAdd.setOnClickListener {
            val intentKita = Intent(this, ActivityTambah::class.java)
            startActivity(intentKita)
        }

        simpanDataArray()

        val rv_daftar = findViewById<RecyclerView>(R.id.rv_data)
        val daftarAdapter = DaftarAdapter(this, daftar_id, daftar_nama, daftar_jumlah)

        rv_daftar.adapter =daftarAdapter
        rv_daftar.layoutManager =LinearLayoutManager(this)
    }

    fun simpanDataArray(){
        val db = MyDBHelper(this)
        val data: Cursor = db.bacaData()

        if (data.count == 0){
            Toast.makeText(this, "Data Tidak Ada!", Toast.LENGTH_SHORT).show()
        } else{
            while (data.moveToNext()){
                daftar_id.add(data.getString(0))
                daftar_nama.add(data.getString(1))
                daftar_jumlah.add(data.getString(2))
            }
        }
    }
}