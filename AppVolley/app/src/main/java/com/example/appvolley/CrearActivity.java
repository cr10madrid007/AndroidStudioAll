package com.example.appvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.VoiceInteractor;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class CrearActivity extends AppCompatActivity {

    EditText txbNombre, txbApellido, txbEdad;
    Button btnSave;
    String POSTurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);

        txbNombre = findViewById(R.id.txbNombre);
        txbApellido = findViewById(R.id.txbApellido);
        txbEdad = findViewById(R.id.txbEdad);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createEmployee();
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });
    }

    private void createEmployee() {
        POSTurl = RestApiMetodos.PostApiEmple;
        HashMap<String, String> parametros = new HashMap<>();
        parametros.put(RestApiMetodos.fieldNombre, txbNombre.getText().toString());
        parametros.put(RestApiMetodos.fieldApellido, txbApellido.getText().toString());
        parametros.put(RestApiMetodos.fieldEdad, txbEdad.getText().toString());

        JsonObjectRequest jsonOjectRequest = new JsonObjectRequest(Request.Method.POST, POSTurl, new JSONObject(parametros), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray status = new JSONArray(response);
                    Toast.makeText(getApplicationContext(), "Respuesta: "+response.toString(), Toast.LENGTH_SHORT).show();
                    ClearScreen();
                    txbNombre.requestFocus();
                }catch (Exception ex){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonOjectRequest);
    }

    private void ClearScreen() {
        txbNombre.setText("");
        txbApellido.setText("");
        txbEdad.setText("");
    }
}