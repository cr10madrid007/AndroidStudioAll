package com.example.retrofitapp.Interfaces;


import com.example.retrofitapp.Models.productos;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductosApi {

    String Ruta0="/producto";

   // String Ruta1="/posts/{valor}";

    @GET(Ruta0)
    Call<List<productos>> getproductos();


    @POST("/producto")
    Call<List<productos>> getproductospost();

   /* @GET(Ruta1)
    Call<Usuarios> getUsuario(@Path("valor")String valor);*/
}
