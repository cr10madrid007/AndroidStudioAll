package com.example.pm2e10102;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pm2e10102.configuracion.SQLiteConexion;
import com.example.pm2e10102.configuracion.Transacciones;
import com.example.pm2e10102.tablas.Personas;
import com.example.pm2e10102.tablas.pais;

import java.util.ArrayList;

public class ActivityContactos extends AppCompatActivity {
    SQLiteConexion conexion;
    Spinner combopersonas,combopais;
    EditText nombres, nota, pais,telefono, id;

    ArrayList<Personas> lista;
    ArrayList<String> ArregloPersonas;

    ArrayList<String> listapais;
    ArrayList<pais> listap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);



        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        id=(EditText) findViewById(R.id.txtID);
        combopersonas=(Spinner) findViewById(R.id.combopersonas);
        nombres = (EditText) findViewById(R.id.txtnombres);
        telefono = (EditText) findViewById(R.id.txttelefono);
        nota = (EditText) findViewById(R.id.txtnota);
        pais = (EditText) findViewById(R.id.txtpais);
       // combopais=(Spinner) findViewById(R.id.combopais);

        obtenerListaPersonas();
        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, ArregloPersonas);
        combopersonas.setAdapter(adp);


        combopersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Buscar();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void obtenerListaPersonas(){
        SQLiteDatabase db = conexion.getReadableDatabase();
        Personas list_personas = null;
        lista= new ArrayList<Personas>();

        // cursor de bd, nos apoya a recorrer la informacion

        Cursor cursor= db.rawQuery("SELECT * FROM "+Transacciones.tablaPersonas, null);

        // recorremos la informacion del cursor

        while (cursor.moveToNext()){
            list_personas= new Personas();
            list_personas.setId(cursor.getInt(0));
            list_personas.setNombres(cursor.getString(1));
            list_personas.setTelefono(cursor.getInt(2));
            list_personas.setOrigen(cursor.getString(3));
            list_personas.setNota(cursor.getString(4));


            lista.add(list_personas);
        }
        cursor.close();

        fillList();

    }

    private void fillList(){
        ArregloPersonas = new ArrayList<String>();
        for (int i = 0; i < lista.size(); i++){
            ArregloPersonas.add(lista.get(i).getId() + " | "
                    +lista.get(i).getNombres()+ " | "
                    +lista.get(i).getTelefono()+ " | "
                    +lista.get(i).getOrigen());
        }
    }


    private void Buscar() {

        SQLiteDatabase db = conexion.getWritableDatabase();
        /* Parametros de configuracion de la sentencia SELECT */
        String [] params = {id.getText().toString()};
        // parametro de la busqueda
        String [] fields = {Transacciones.nombres,
                Transacciones.telefono,
                Transacciones.nota,
                Transacciones.pais };
        String wherecond = Transacciones.id + "=?";

        try{
            Cursor cdata = db.query(Transacciones.tablaPersonas, fields, wherecond, params, null,null, null );
            cdata.moveToFirst();
            nombres.setText(cdata.getString(0));
            telefono.setText(cdata.getString(1));
            nota.setText(cdata.getString(2));
            pais.setText(cdata.getString(3));
            Toast.makeText(getApplicationContext(), "Consultado con exito",Toast.LENGTH_LONG).show();
        }
        catch (Exception ex) {
            ClearScreen();
            Toast.makeText(getApplicationContext(), "Elemento no encontrado", Toast.LENGTH_LONG).show();
        }


    }
    private void ClearScreen() {
        nombres.setText("");
        telefono.setText("");
        nota.setText("");
        pais.setText("");
    }
}