package com.example.damii_exo_lucianobacab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Compras extends AppCompatActivity {
    private TableLayout tbCompras;
    private Button btnSalir4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);

        tbCompras = (TableLayout) findViewById(R.id.tbCompras);

        //Creacion de objeto de enlace a las base de datos
        Compra oper = new Compra(this, "operacion", null, 1);
        SQLiteDatabase OrdinarioBD1 = oper.getWritableDatabase();
        Cursor fila = OrdinarioBD1.rawQuery("select * from venta", null);

        // Recorrer el cursor y crear las filas y columnas en el TableLayout
        if (fila.moveToFirst()) {
            do {
                int id = fila.getInt(fila.getColumnIndex("id"));
                String marca = fila.getString(fila.getColumnIndex("marca"));
                String cantidad = fila.getString(fila.getColumnIndex("cantidad"));
                String precio = fila.getString(fila.getColumnIndex("precio"));
                String importe = fila.getString(fila.getColumnIndex("importe"));
                String espacio = "   ";

                // Crear una nueva fila
                TableRow row = new TableRow(this);

                // Crear textviews para cada columna y agregar los datos
                TextView ID = new TextView(this);
                ID.setText(String.valueOf(id + espacio));
                ID.setTextColor(Color.BLACK);
                row.addView(ID);

                TextView Marca = new TextView(this);
                Marca.setText(marca + espacio);
                Marca.setTextColor(Color.BLACK);
                row.addView(Marca);

                TextView Cantidad = new TextView(this);
                Cantidad.setText(cantidad + espacio);
                Cantidad.setTextColor(Color.BLACK);
                row.addView(Cantidad);

                TextView Precio = new TextView(this);
                Precio.setText(precio + espacio);
                Precio.setTextColor(Color.BLACK);
                row.addView(Precio);

                TextView Importe = new TextView(this);
                Importe.setText(importe + espacio);
                Importe.setTextColor(Color.BLACK);
                row.addView(Importe);

                // Agregar la fila al TableLayout
                tbCompras.addView(row);

            } while (fila.moveToNext());
        }

        // Cerrar el cursor y la base de datos
        fila.close();
        OrdinarioBD1.close();


        //Boton para regresar al menu principal
        btnSalir4 = (Button) findViewById(R.id.btnSalir4);
        btnSalir4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Menu Principal", Toast.LENGTH_SHORT).show();
                Log.i("INFO:", "VolverVentas");
                Intent intent = new Intent(Compras.this, Ventas.class);
                startActivity(intent);

            }
        });
    }
}