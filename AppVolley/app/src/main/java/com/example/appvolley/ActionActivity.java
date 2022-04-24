package com.example.appvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnLista:
                Prc_View_List_Emple();
            break;

            case R.id.btnCrear:
                Prc_Create_Emple();
            break;

            case R.id.btnUpload:
                Prc_Upload_Image_Emple();
            break;
        }
    }

    private void Prc_Upload_Image_Emple() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private void Prc_Create_Emple() {
        Intent intent = new Intent(getApplicationContext(), CrearActivity.class);
        startActivity(intent);
    }

    private void Prc_View_List_Emple() {
        //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        //startActivity(intent);
    }
}