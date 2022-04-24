package com.example.pm2e10102;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pm2e10102.configuracion.SQLiteConexion;
import com.example.pm2e10102.configuracion.Transacciones;
import com.example.pm2e10102.tablas.Personas;
import com.example.pm2e10102.tablas.pais;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SQLiteConexion conexion;
    Spinner combopersonas,pais;
    EditText nombres, nota, telefono;

    ArrayList<String> listapersonas;
    ArrayList<pais> lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        combopersonas=(Spinner) findViewById(R.id.combopersonas);
        nombres = (EditText) findViewById(R.id.txtnombres);
        telefono = (EditText) findViewById(R.id.txttelefono);
        nota = (EditText) findViewById(R.id.txtnota);
        pais=(Spinner) findViewById(R.id.combopersonas);

        obtenerListaPersonas();

        ArrayAdapter<CharSequence> adp = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listapersonas);
        combopersonas.setAdapter(adp);


        Button btnAdd =(Button) findViewById(R.id.btnAdd);
        Button btnContacto =(Button) findViewById(R.id.btnAddContacto);
        Button btnVer =(Button) findViewById(R.id.btnVerContacto);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityPais.class);
                startActivity(intent);
            }
        });

        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityContactos.class);
                startActivity(intent);
            }
        });

        btnContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarContacto();
            }
        });
    }

    private void guardarContacto() {

        String c1=nombres.getText().toString();
        String c2=telefono.getText().toString();
        String c3= nota.getText().toString();
        String c4= pais.getSelectedItem().toString();
        if(pais.equals("---")) {
            alerta1();
        }else if( c1.isEmpty() && c2.isEmpty() && c3.isEmpty() ){
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_LONG).show();
            alerta();

        }else {

            SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
            SQLiteDatabase db = conexion.getWritableDatabase();


            ContentValues valores = new ContentValues();
            valores.put(Transacciones.nombres, nombres.getText().toString());
            valores.put(Transacciones.telefono, telefono.getText().toString());
            valores.put(Transacciones.nota, nota.getText().toString());
            valores.put(Transacciones.origen, pais.getSelectedItem().toString());

            //Toast.makeText(getApplicationContext(), "Registro Ingresado: Codigo: "+ combopersonas.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
            Long resultado = db.insert(Transacciones.tablaPersonas, Transacciones.id, valores);

            Toast.makeText(getApplicationContext(), "Registro Ingresado: Codigo: " + resultado.toString(), Toast.LENGTH_LONG).show();
            db.close();
            LimpiarPantalla();
        }
    }

    private void LimpiarPantalla(){
        nombres.setText("");
        telefono.setText("");
        nota.setText("");

    }

    private void obtenerListaPersonas(){
        SQLiteDatabase db = conexion.getReadableDatabase();
        pais list_personas = null;
        lista= new ArrayList<pais>();

        // cursor de bd, nos apoya a recorrer la informacion

        Cursor cursor= db.rawQuery("SELECT * FROM "+Transacciones.tablaPais, null);

        // recorremos la informacion del cursor

        while (cursor.moveToNext()){
            list_personas= new pais();
            list_personas.setId(cursor.getInt(0));
            list_personas.setPais(cursor.getString(1));


            lista.add(list_personas);
        }
        cursor.close();

        fillCombo();

    }
    private void fillCombo() {
        listapersonas = new ArrayList<String>();
        for(int i=0; i< lista.size(); i++){
            listapersonas.add(lista.get(i).getPais() );
        }
    }

    private void alerta(){
       AlertDialog alert = new AlertDialog.Builder(MainActivity.this).create();
        alert.setTitle("Ooops");
        alert.setMessage("LLene todos los campos");
        alert.setButton(AlertDialog.BUTTON_NEUTRAL,"Ok", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface d, int which){
                d.dismiss();
            }
        });

        alert.show();
    }

    private void alerta1(){
        AlertDialog alert = new AlertDialog.Builder(MainActivity.this).create();
        alert.setTitle("Ooops");
        alert.setMessage("Escoja un pais");
        alert.setButton(AlertDialog.BUTTON_NEUTRAL,"Ok", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface d, int which){
                d.dismiss();
            }
        });

        alert.show();
    }




}