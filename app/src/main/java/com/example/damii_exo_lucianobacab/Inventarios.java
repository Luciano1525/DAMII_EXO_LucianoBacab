package com.example.damii_exo_lucianobacab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Inventarios extends AppCompatActivity {
    private Button btnInsertar, btnBuscar, btnModificar, btnEliminar, btnSalir2;
    private EditText etIdInv, etPrecio, etCantidad;
    private Spinner spnProducto;
    private ImageView ivProducto;
    private Bitmap Imag;
    private List<String> elementos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventarios);

        etIdInv = (EditText) findViewById(R.id.etIdInv);
        etPrecio = (EditText) findViewById(R.id.etPrecio);
        etCantidad = (EditText) findViewById(R.id.etCantidad);
        ivProducto = (ImageView) findViewById(R.id.ivProducto);
        spnProducto = (Spinner) findViewById(R.id.spnProducto);

        //Creacion de objeto de enlace a las base de datos
        Inventario oper1 = new Inventario(this, "operacion2", null, 1);
        //Arreglo que almacena los productos para luego ser consultados
        elementos = new ArrayList<>();
        elementos.add("Seleccionar Producto");
        elementos.add("Xiaomi Black Shark 4");
        elementos.add("ZTE Nubia Red Magic 4");
        elementos.add("Xiaomi Poco F5");
        elementos.add("Xiaomi 12x");
        elementos.add("Xiaomi Poco M5s");
        elementos.add("Xiaomi 11T Pro");
        elementos.add("Xiaomi Poco X5 Pro");

        //Adaptar el Spinner al Array de los productos y Asignarle su imagen
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.Productos, android.R.layout.simple_spinner_item);
        spnProducto.setAdapter(adapter);
        spnProducto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ivProducto.setVisibility(View.VISIBLE);

                if (position == 0) {
                    Toast.makeText(parent.getContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                    ivProducto.setImageResource(R.drawable.blanco);
                    Imag = BitmapFactory.decodeResource(getResources(), R.drawable.blanco);
                } else if (position == 1) {
                    ivProducto.setImageResource(R.drawable.blackshark);
                    Imag = BitmapFactory.decodeResource(getResources(), R.drawable.blackshark);

                } else if (position == 2) {
                    ivProducto.setImageResource(R.drawable.nubiaredmagic);
                    Imag = BitmapFactory.decodeResource(getResources(), R.drawable.nubiaredmagic);

                } else if (position == 3) {
                    ivProducto.setImageResource(R.drawable.xiaomipocof);
                    Imag = BitmapFactory.decodeResource(getResources(), R.drawable.xiaomipocof);

                } else if (position == 4) {
                    ivProducto.setImageResource(R.drawable.xiaomix);
                    Imag = BitmapFactory.decodeResource(getResources(), R.drawable.xiaomix);

                } else if (position == 5) {
                    ivProducto.setImageResource(R.drawable.xiaomims);
                    Imag = BitmapFactory.decodeResource(getResources(), R.drawable.xiaomims);

                } else if (position == 6) {
                    ivProducto.setImageResource(R.drawable.xiaomitpro);
                    Imag = BitmapFactory.decodeResource(getResources(), R.drawable.xiaomitpro);

                } else if (position == 7) {
                    ivProducto.setImageResource(R.drawable.xiaomipocoxpro);
                    Imag = BitmapFactory.decodeResource(getResources(), R.drawable.xiaomipocoxpro);

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Boton para registrar los Productos
        btnInsertar = (Button) findViewById(R.id.btnInsertar);
        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase OrdinarioBD = oper1.getWritableDatabase();

                String idInven = etIdInv.getText().toString();
                String producto = spnProducto.getSelectedItem().toString();
                String precio = etPrecio.getText().toString();
                String cantidad = etCantidad.getText().toString();
                byte[] imagen = getBytesFromBitmap(Imag);

                if(!idInven.isEmpty() && !precio.isEmpty() && !cantidad.isEmpty()) {
                    ContentValues registroI = new ContentValues();
                    registroI.put("id", idInven);
                    registroI.put("nombre", producto);
                    registroI.put("precio", precio);
                    registroI.put("cantidad", cantidad);
                    registroI.put("imagen", imagen);

                    OrdinarioBD.insert("productos", null, registroI);
                    OrdinarioBD.close();


                    etIdInv.setText("");
                    etPrecio.setText("");
                    etCantidad.setText("");

                    etIdInv.setHint("ID Inventario");
                    etPrecio.setHint("Precio");
                    etCantidad.setHint("Cantidad");
                    int posicion = 0;
                    spnProducto.setSelection(posicion);
                    ivProducto.setVisibility(View.INVISIBLE);

                    Toast.makeText(getApplicationContext(), "Producto Registrado Exitosamente!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "No puedes guardar datos vacios", Toast.LENGTH_SHORT).show();
                }


            }
        });

        //Boton para consultar los Productos
        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase OrdinarioBD = oper1.getWritableDatabase();
                String idInven = etIdInv.getText().toString();

                if(!idInven.isEmpty()) {
                    Cursor fila = OrdinarioBD.rawQuery("select nombre, precio, cantidad from productos where id = " + idInven, null);

                    if (fila.moveToFirst()){
                        String elemento = fila.getString(0);
                        int positionToSelect = elementos.indexOf(elemento);
                        spnProducto.setSelection(positionToSelect);
                        etPrecio.setText(fila.getString(1));
                        etCantidad.setText(fila.getString(2));
                        Toast.makeText(getApplicationContext(), "Producto Encontrado", Toast.LENGTH_SHORT).show();
                        OrdinarioBD.close();

                    } else {
                        Toast.makeText(getApplicationContext(), "El Producto no Existe", Toast.LENGTH_SHORT).show();
                        OrdinarioBD.close();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Introduzca el id del Producto", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //Boton para Modificar Productos
        btnModificar = (Button) findViewById(R.id.btnModificar);
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase OrdinarioBD = oper1.getWritableDatabase();

                String idInven = etIdInv.getText().toString();
                String producto = spnProducto.getSelectedItem().toString();
                String precio = etPrecio.getText().toString();
                String cantidad = etCantidad.getText().toString();
                byte[] imagen = getBytesFromBitmap(Imag);

                if(!idInven.isEmpty() && !precio.isEmpty() && !cantidad.isEmpty()) {
                    ContentValues mod = new ContentValues();
                    mod.put("id", idInven);
                    mod.put("nombre", producto);
                    mod.put("precio", precio);
                    mod.put("cantidad", cantidad);
                    mod.put("imagen", imagen);

                    int cantidadmod = OrdinarioBD.update("productos", mod, "id = " + idInven, null);
                    OrdinarioBD.close();

                    etIdInv.setText("");
                    etPrecio.setText("");
                    etCantidad.setText("");

                    etIdInv.setHint("ID Inventario");
                    etPrecio.setHint("Precio");
                    etCantidad.setHint("Cantidad");
                    int posicion = 0;
                    spnProducto.setSelection(posicion);
                    ivProducto.setVisibility(View.INVISIBLE);

                    if (cantidadmod == 1){
                        Toast.makeText(getApplicationContext(), "Producto Modificado Exitosamente", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Producto no Existe", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(getApplicationContext(), "Introduzca el id del Producto", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //Boton para Eliminar Reservaciones
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase OrdinarioBD = oper1.getWritableDatabase();
                String idInven = etIdInv.getText().toString();

                if(!idInven.isEmpty()) {
                    int cantidad = OrdinarioBD.delete("productos", "id = " + idInven, null);
                    OrdinarioBD.close();

                    etIdInv.setText("");
                    etPrecio.setText("");
                    etCantidad.setText("");

                    etIdInv.setHint("ID Inventario");
                    etPrecio.setHint("Precio");
                    etCantidad.setHint("Cantidad");
                    int posicion = 0;
                    spnProducto.setSelection(posicion);
                    ivProducto.setVisibility(View.INVISIBLE);

                    if (cantidad == 1){
                        Toast.makeText(getApplicationContext(), "Producto Eliminado Exitosamente", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Producto no Existe", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Introduzca el id del Producto", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //Boton para regresar al menu principal
        btnSalir2 = (Button) findViewById(R.id.btnSalir2);
        btnSalir2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Menu Principal", Toast.LENGTH_SHORT).show();
                Log.i("INFO:", "Volver");
                Intent intent = new Intent(Inventarios.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
    //Metodos
// Método para convertir un Bitmap en un array de bytes
    private byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

}