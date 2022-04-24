package com.example.pm01pgr1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtN01, txtN02;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtN01=(EditText) findViewById(R.id.txtN01);
        txtN02=(EditText) findViewById(R.id.txtN02);

        Button btnPrueba = (Button) findViewById(R.id.btnPrueba);
        Button btnRestar = (Button) findViewById(R.id.btnRestar);
        Button btnDividir = (Button) findViewById(R.id.btnDiv);
        Button btnMul = (Button) findViewById(R.id.btnMul);
        /*Evento BTN*/

        btnPrueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"Evento Click", Toast.LENGTH_LONG).show();

                Double resultado;
                resultado = Double.parseDouble(txtN01.getText().toString()) + Double.parseDouble(txtN02.getText().toString());
                Intent intent = new Intent(getApplicationContext(), ActivityResultado.class);
                intent.putExtra("num1", resultado.toString());
                startActivity(intent);
                //Toast.makeText(getApplicationContext(),"Cambio", Toast.LENGTH_LONG).show();

/*
 int color;
                View contenedor = view.getRootView();

                color= Color.parseColor("#80CBC4");
                contenedor.setBackgroundColor(color);
                Double resultado;
                resultado = Double.parseDouble(txtN01.getText().toString()) + Double.parseDouble(txtN02.getText().toString());
                Toast.makeText(getApplicationContext(), resultado.toString(),Toast.LENGTH_LONG).show();
                */

            }
        });

        btnRestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double resultado;
                resultado = Double.parseDouble(txtN01.getText().toString()) - Double.parseDouble(txtN02.getText().toString());
                Intent intent = new Intent(getApplicationContext(), ActivityResultado.class);
                intent.putExtra("num1", resultado.toString());
                startActivity(intent);
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double resultado;
                resultado = Double.parseDouble(txtN01.getText().toString()) * Double.parseDouble(txtN02.getText().toString());
                Intent intent = new Intent(getApplicationContext(), ActivityResultado.class);
                intent.putExtra("num1", resultado.toString());
                startActivity(intent);
            }
        });

        btnDividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double resultado;
                resultado = Double.parseDouble(txtN01.getText().toString()) / Double.parseDouble(txtN02.getText().toString());
                Intent intent = new Intent(getApplicationContext(), ActivityResultado.class);
                intent.putExtra("num1", resultado.toString());
                startActivity(intent);
            }
        });
    }
}