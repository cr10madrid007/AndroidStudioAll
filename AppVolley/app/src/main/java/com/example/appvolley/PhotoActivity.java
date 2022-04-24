package com.example.appvolley;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class PhotoActivity extends AppCompatActivity {

    ImageView imgView;
    Button btnUpload, btnGallery;
    Bitmap photo;
    static final int RESULT_GALLERY = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        imgView = findViewById(R.id.imgPhoto);
        btnUpload = findViewById(R.id.btnSubir);
        btnGallery = findViewById(R.id.btnGallery);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendImage();
            }
        });
        
        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GaleriaImagenes();
            }
        });
    }

    private void GaleriaImagenes() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RESULT_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri imageUri;
        if(requestCode == RESULT_OK && requestCode == RESULT_GALLERY){
            imageUri = data.getData();
            imgView.setImageURI(imageUri);

            try {
                photo = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

            }catch (Exception ex){
            }
        }
    }

    private void sendImage() {
        String url = RestApiMetodos.UploadFileApiEmple;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{

                }catch (Exception ex){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Respuesta", error.toString());
            }
        })
        {
            protected Map<String, String> getParams(){
               String image = getStringImage(photo);
               Map<String, String> parametros = new HashMap<>();
               parametros.put("IMAGEN", image);
               return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private String getStringImage(Bitmap photo) {
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG, 100, ba);
        byte[] imgByte = ba.toByteArray();
        String encode = Base64.encodeToString(imgByte, Base64.DEFAULT);
        return encode;
    }
}