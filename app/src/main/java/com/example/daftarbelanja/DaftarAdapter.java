package com.example.daftarbelanja;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DaftarAdapter extends RecyclerView.Adapter<DaftarAdapter.ViewHolder> {

    private Context context;
    private ArrayList daftar_id, daftar_nama, daftar_jumlah;

    DaftarAdapter(
            Context context,
            ArrayList daftar_id,
            ArrayList daftar_nama,
            ArrayList daftar_jumlah
    ){
        this.context =context;
        this.daftar_id =daftar_id;
        this.daftar_nama = daftar_nama;
        this.daftar_jumlah = daftar_jumlah;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater iKita = LayoutInflater.from(context);
        View vKita = iKita.inflate(R.layout.row, parent,false);

        return new ViewHolder(vKita);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txt_id.setText(String.valueOf(daftar_id.get(position)));
        holder.txt_nama.setText(String.valueOf(daftar_nama.get(position)));
        holder.txt_jumlah.setText(String.valueOf(daftar_jumlah.get(position)));
        holder.layoutUtama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent iKita = new Intent(context, ActivityUbah.class);
                iKita.putExtra("id", String.valueOf(daftar_id.get(position)));
                iKita.putExtra("nama", String.valueOf(daftar_nama.get(position)));
                iKita.putExtra("jumlah", String.valueOf(daftar_jumlah.get(position)));

                context.startActivity(iKita);
            }
        });
    }

    @Override
    public int getItemCount() {
        return daftar_id.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_id, txt_nama, txt_jumlah;
        LinearLayout layoutUtama;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_id = itemView.findViewById(R.id.txt_daftarID);
            txt_nama = itemView.findViewById(R.id.txt_daftarNama);
            txt_jumlah = itemView.findViewById(R.id.txt_daftarJumlah);
            layoutUtama = itemView.findViewById(R.id.layout_utama);

        }
    }
}
