package com.example.economicoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.*;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    ListView listemple;
    List<Empleado> EmpleadoList;
    ArrayList<String> arrayemple;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listemple = (ListView) findViewById(R.id.listemple);
        EmpleadoList = new ArrayList<>();
        arrayemple = new ArrayList<String>();

        SendRequest();


    }

    private void SendRequest() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://economico-app.herokuapp.com/empleados";

        //Realizamos peticion a la url del endpoint

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray Emplearray = obj.getJSONArray("empleado");
                      for(int i=0;i<Emplearray.length();i++){

                          //aqui puede guardar uno por uno

                          JSONObject empleObjet = Emplearray.getJSONObject(i);
                          Empleado emple = new Empleado(empleObjet.getString("nombre"),
                                  empleObjet.getString("apellidos"),
                                  empleObjet.getString("fechaNacimiento"),
                                  empleObjet.getString("correo"),
                                  empleObjet.getString("contraseña"),
                                  empleObjet.getString("telefono"),
                                  empleObjet.getString("img"));



                          EmpleadoList.add(emple);
                          arrayemple.add(emple.getNombre()+"-"+emple.getApellidos()+"-"+emple.getFechaNacimiento()+"-"+emple.getCorreo()+""+emple.getContraseña()+"-"+emple.getTelefono()+"-"+emple.getImg());




                      }
                    ArrayAdapter adp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, arrayemple);
                      listemple.setAdapter(adp);
                }

                catch (JSONException ex) {

                    ex.printStackTrace();
                }

            }
        }, new Response.ErrorListener(){
            public void  onErrorResponse(VolleyError error)
            {

            }

        });
    }


}