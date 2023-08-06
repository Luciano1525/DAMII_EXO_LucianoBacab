package com.example.damii_exo_lucianobacab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnInventarios, btnVentas, btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Boton para ejecutar inventarios
        btnInventarios = (Button) findViewById(R.id.btnInventarios);
        btnInventarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Inventarios", Toast.LENGTH_SHORT).show();
                Log.i("INFO:", "Class Inventarios");
                Intent intent = new Intent(MainActivity.this, Inventarios.class);
                startActivity(intent);

            }
        });

        //Boton para ejecutar Ventas
        btnVentas = (Button) findViewById(R.id.btnVentas);
        btnVentas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Ventas", Toast.LENGTH_SHORT).show();
                Log.i("INFO:", "Class Ventas");
                Intent intent = new Intent(MainActivity.this, Ventas.class);
                startActivity(intent);

            }
        });

        //Finalizar App
        btnSalir = (Button) findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Aplicacion Finalizada!", Toast.LENGTH_SHORT).show();
                Log.i("INFO:", "App Finish");
                finishAffinity();

            }
        });
    }
}