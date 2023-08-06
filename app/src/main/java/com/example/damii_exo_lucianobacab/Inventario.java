package com.example.damii_exo_lucianobacab;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Inventario extends SQLiteOpenHelper {

    public Inventario(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase OrdinarioBD) {
        OrdinarioBD.execSQL("create table productos(id int primary key, nombre text, precio text, cantidad text, imagen blob)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int il) {

    }

}
