package com.example.pm2e10102;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pm2e10102.configuracion.SQLiteConexion;
import com.example.pm2e10102.configuracion.Transacciones;

public class ActivityPais extends AppCompatActivity {
    EditText pais;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pais);



        pais =(EditText) findViewById(R.id.txtPais);
        Button btnAgregar = (Button) findViewById(R.id.btnGuardarPais);


        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgregarPersonas();
            }
        });

    }

    private void AgregarPersonas(){
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null,1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.pais , pais.getText().toString());


        Long resultado = db.insert(Transacciones.tablaPais,Transacciones.id, valores);

        Toast.makeText(getApplicationContext(), "Registro Ingresado: Codigo: "+ resultado.toString(),Toast.LENGTH_LONG).show();
        db.close();
        LimpiarPantalla();

    }


    private void LimpiarPantalla(){
        pais.setText("");

    }
}