package com.example.damii_exo_lucianobacab;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Compra extends SQLiteOpenHelper {
    public Compra(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase OrdinarioBD1) {
        OrdinarioBD1.execSQL("create table venta(id int primary key, marca text, cantidad text, precio text, importe text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int il) {

    }


}
