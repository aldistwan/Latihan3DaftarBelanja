package com.example.daftarbelanja;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "Daftarbelanja.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "belanja";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAMA = "daftar_nama";
    private static final String COLUMN_JUMLAH = "daftar_jumlah";


    public MyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String querySQL = "CREATE TABLE " + TABLE_NAME +
                            " (" +
                                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                COLUMN_NAMA + " TEXT, " +
                                COLUMN_JUMLAH + " INTEGER " +
                            " );";
        db.execSQL(querySQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void tambahDaftar(String nama, int jumlah){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAMA, nama);
        cv.put(COLUMN_JUMLAH, jumlah);

        long result = db.insert(TABLE_NAME, null, cv);

        if(result == -1){
            Toast.makeText(context, "Gagal Menambahkan!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Berhasil Ditambahkan!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor bacaData(){
        String querySQL = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor data =  null;
        if(db != null){
            data = db.rawQuery(querySQL, null);
        }

        return data;
    }

    void ubahDaftar(String baris_id, String nama, String jumlah){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put(COLUMN_NAMA, nama);
        data.put(COLUMN_JUMLAH, jumlah);

        long result = db.update(TABLE_NAME, data, "_id=?", new String[]{baris_id});

        if(result == -1){
            Toast.makeText(context, "Ada Gangguan!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Berhasil Diupdate!", Toast.LENGTH_SHORT).show();
        }
    }

    void hapusDaftar(String row_id){
        SQLiteDatabase db = this.getReadableDatabase();

        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});

        if(result == -1){
            Toast.makeText(context, "Gagal Dihapus!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Berhasil Dihapus!", Toast.LENGTH_SHORT).show();
        }
    }

}
