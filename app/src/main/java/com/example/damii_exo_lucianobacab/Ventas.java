package com.example.damii_exo_lucianobacab;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Ventas extends AppCompatActivity {
    private ListView lvProductos;

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

    }

    // Método para obtener los registros de la tabla de SQLite y crear la lista de objetos DatosTabla
    private List<DatosTabla> obtenerRegistrosDeTabla() {
        List<DatosTabla> listaDeDatosTabla = new ArrayList<>();

        // Aquí debes realizar la consulta a la base de datos y obtener los datos de cada fila.
        // Por ejemplo:
        //Creacion de objeto de enlace a las base de datos
        Inventario oper1 = new Inventario(this, "operacion2", null, 1);
        SQLiteDatabase OrdinarioBD = oper1.getWritableDatabase();
        //SQLiteDatabase OrdinarioBD = dbHelper.getReadableDatabase();
        Cursor cursor = OrdinarioBD.rawQuery("select nombre, precio, cantidad from productos", null);
        if (cursor != null && cursor.moveToFirst()) {
            do {

                //byte[] imagenBlob = cursor.getBlob(cursor.getColumnIndex("imagen"));
                String nombre = cursor.getString(0);
                String precio = cursor.getString(1);
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


/*
    private List<DatosTabla> GetData1(){
        lst = new ArrayList<>();

        //Creacion de objeto de enlace a las base de datos
        Inventario oper1 = new Inventario(this, "operacion2", null, 1);
        SQLiteDatabase OrdinarioBD = oper1.getWritableDatabase();
        Cursor fila = OrdinarioBD.rawQuery("select imagen, nombre, precio, cantidad from productos", null);

        // Recorrer el cursor y crear las filas y columnas en el TableLayout
        if (fila.moveToFirst()) {
            do {
                int fila1 = -1;
                fila1 = fila1 + 1;
                byte[] imageData = fila.getBlob(0);
                int valorEntero = byteArrayToInt(imageData);
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
                String Nombre = fila.getString(1);
                String Precio = fila.getString(2);
                String Cantidad = fila.getString(3);

                lst.add(new DatosTabla(fila1, valorEntero, Nombre, Precio, Cantidad));

            } while (fila.moveToNext());
        }

        // Cerrar el cursor y la base de datos
        fila.close();
        OrdinarioBD.close();

        return lst;
    }
*/

    public static int byteArrayToInt(byte[] bytes) {
        if (bytes.length < 4) {
            throw new IllegalArgumentException("El arreglo de bytes debe tener al menos 4 bytes para convertirse a int.");
        }

        int value = 0;
        for (int i = 0; i < 4; i++) {
            value |= (bytes[i] & 0xFF) << (i * 8);
        }
        return value;
    }


}