package com.example.retrofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.retrofitapp.Interfaces.ProductosApi;
import com.example.retrofitapp.Interfaces.UsuarioApi;
import com.example.retrofitapp.Models.Usuarios;
import com.example.retrofitapp.Models.productos;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
 ListView listapersonas;
ArrayList<String> titulos = new ArrayList();
ArrayAdapter arrayAdapter;
String cod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        obtenerlistapersonas();


        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,titulos);
        listapersonas = (ListView) findViewById(R.id.listuser);
        listapersonas.setAdapter(arrayAdapter);

    }

    private void obtenerlistapersonas() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://economico-app.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

      /*  UsuarioApi usuariosApi = retrofit.create(UsuarioApi.class);
        Call<List<Usuarios>> calllista = usuariosApi.getUsuarios();*/

        ProductosApi productosApi = retrofit.create(ProductosApi.class);
        Call<List<productos>> calllista = productosApi.getproductos();

        calllista.enqueue(new Callback<List<productos>>() {
            @Override
            public void onResponse(Call<List<productos>> call, Response<List<productos>> response) {
                for (productos productos: response.body())
                {
                   Log.i(productos.getNombreProducto(),"onResponse");

                        titulos.add(
                                productos.getIdProducto()+ " | " +
                                productos.getNombreProducto());




                    arrayAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<List<productos>> call, Throwable t)
            {
                t.getMessage();
            }
        });


       /* calllista.enqueue(new Callback<List<Usuarios>>() {
            @Override
            public void onResponse(Call<List<Usuarios>> call, Response<List<Usuarios>> response)

            {
                for (Usuarios usuarios: response.body())
                {
                    Log.i(usuarios.getTitle(),"onResponse");
                    titulos.add(usuarios.getTitle());

                    arrayAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<List<Usuarios>> call, Throwable t)

            {
            t.getMessage();
            }
        });*/




    }
}