package com.example.damii_exo_lucianobacab;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Ventas extends AppCompatActivity {
    private ListView lvProductos;
    private List<DatosTabla> listaDeDatosTabla;
    private Button btnVentas1, btnSalir3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas);

        lvProductos = (ListView) findViewById(R.id.lvProductos);

        // Realiza una consulta a la base de datos y obtén los registros
        List<DatosTabla> listaDeDatosTabla = obtenerRegistrosDeTabla();

        // Crea el adaptador personalizado y establece el adaptador en el ListView
        TuAdaptador adaptador = new TuAdaptador(this, listaDeDatosTabla);
        lvProductos.setAdapter(adaptador);

        //Funcion de click del ListView
        lvProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(parent.getContext(), "Producto Seleccionado: "+parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                DatosTabla selectedItem = listaDeDatosTabla.get(position);

                String Nombre = selectedItem.getTvNombre();
                String Precio1 = selectedItem.getTvPreci();
                //Dividir el String y recuperar solo el precio
                String[] partes = Precio1.split(" ");
                String Signo = partes[0]; // Primera palabra
                String Precio = partes[1]; // Segunda palabra
                String Cantidad = selectedItem.getTvCant();
                int Imagen = selectedItem.getIvProduc();
                Toast.makeText(Ventas.this, "Marca: " + Nombre + "\nPrecio: " + Precio + "\nExistencias: " + Cantidad, Toast.LENGTH_SHORT).show();

                AlertDialog.Builder res = new AlertDialog.Builder(Ventas.this);
                res.setTitle("Vender");
                View select = getLayoutInflater().inflate(R.layout.compra, null);
                res.setView(select);
                res.setCancelable(false);

                res.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = res.create();
                dialog.show();

            }
        });

        //Boton para regresar al menu principal
        btnSalir3 = (Button) findViewById(R.id.btnSalir3);
        btnSalir3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Menu Principal", Toast.LENGTH_SHORT).show();
                Log.i("INFO:", "Volver");
                Intent intent = new Intent(Ventas.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }

    // Método para obtener los registros de la tabla de SQLite y crear la lista de objetos DatosTabla
    private List<DatosTabla> obtenerRegistrosDeTabla() {
        listaDeDatosTabla = new ArrayList<>();

        //Creacion de objeto de enlace a las base de datos
        Inventario oper1 = new Inventario(this, "operacion2", null, 1);
        SQLiteDatabase OrdinarioBD = oper1.getWritableDatabase();
        Cursor cursor = OrdinarioBD.rawQuery("select nombre, precio, cantidad from productos", null);
        if (cursor != null && cursor.moveToFirst()) {
            do {

                //byte[] imagenBlob = cursor.getBlob(cursor.getColumnIndex("imagen"));
                String nombre = cursor.getString(0);
                String precio1 = cursor.getString(1);
                String precio = "$ " + precio1;
                String cantidad = cursor.getString(2);

                if (nombre.equals("Xiaomi Black Shark 4")){
                    int imagen = R.drawable.blackshark;
                    DatosTabla datosTabla = new DatosTabla(imagen, nombre, precio, cantidad);
                    listaDeDatosTabla.add(datosTabla);

                } else if (nombre.equals("ZTE Nubia Red Magic 4")){
                    int imagen = R.drawable.nubiaredmagic;
                    DatosTabla datosTabla = new DatosTabla(imagen, nombre, precio, cantidad);
                    listaDeDatosTabla.add(datosTabla);

                } else if (nombre.equals("Xiaomi Poco F5")){
                    int imagen = R.drawable.xiaomipocof;
                    DatosTabla datosTabla = new DatosTabla(imagen, nombre, precio, cantidad);
                    listaDeDatosTabla.add(datosTabla);

                } else if (nombre.equals("Xiaomi 12x")){
                    int imagen = R.drawable.xiaomix;
                    DatosTabla datosTabla = new DatosTabla(imagen, nombre, precio, cantidad);
                    listaDeDatosTabla.add(datosTabla);

                } else if (nombre.equals("Xiaomi Poco M5s")){
                    int imagen = R.drawable.xiaomims;
                    DatosTabla datosTabla = new DatosTabla(imagen, nombre, precio, cantidad);
                    listaDeDatosTabla.add(datosTabla);

                } else if (nombre.equals("Xiaomi 11T Pro")){
                    int imagen = R.drawable.xiaomitpro;
                    DatosTabla datosTabla = new DatosTabla(imagen, nombre, precio, cantidad);
                    listaDeDatosTabla.add(datosTabla);

                } else if (nombre.equals("Xiaomi Poco X5 Pro")){
                    int imagen = R.drawable.xiaomipocoxpro;
                    DatosTabla datosTabla = new DatosTabla(imagen, nombre, precio, cantidad);
                    listaDeDatosTabla.add(datosTabla);

                }

            } while (cursor.moveToNext());
        }
        cursor.close();
        OrdinarioBD.close();

        // Retorna la lista de objetos DatosTabla
        return listaDeDatosTabla;
    }


}