package com.example.appvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvEmple;
    List<Empleado> empList;
    ArrayList<String> arrayEmple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvEmple = findViewById(R.id.lvEmple);
        empList = new ArrayList<>();
        arrayEmple = new ArrayList<>();
        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CrearActivity.class);
                startActivity(intent);
            }
        });

        sendRequest();
    }



    private void sendRequest() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = RestApiMetodos.GetApiEmple;

        //Realizamos la peticion a la URL del endPoint
        StringRequest strinRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray EmpleArray = obj.getJSONArray("empleado");

                    for(int i=0; i < EmpleArray.length(); i++){
                        //aqui se puede guardar uno por uno
                        JSONObject empleObject = EmpleArray.getJSONObject(i);
                        Empleado emple = new Empleado(empleObject.getString("id"),
                                empleObject.getString("nombre"),
                                empleObject.getString("apellidos"),
                                empleObject.getString("edad"));

                        empList.add(emple);
                        arrayEmple.add(emple.getNombre() + " - " + emple.apellidos);
                    }

                    ArrayAdapter adp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, arrayEmple);
                    lvEmple.setAdapter(adp);

                }catch (JSONException ex){

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error", error.toString());

            }
        });
        queue.add(strinRequest);
    }
}