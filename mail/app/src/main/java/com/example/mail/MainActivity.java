package com.example.mail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
EditText txtDireccion, txtTema, txtCuerpo;
Button btnEnviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtDireccion=findViewById(R.id.txtDireccion);
        txtCuerpo=findViewById(R.id.txtCuerpo);
        txtTema=findViewById(R.id.txtTema);

        btnEnviar=(Button) findViewById(R.id.btnEnviar);


        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               enviar();
            }
        });
    }

    public void enviar(){
        String mail= txtDireccion.getText().toString().trim();
        String message= txtCuerpo.getText().toString();
        String subject= txtTema.getText().toString().trim();

        //Send Mail
        JavaMailAPI javaMailAPI = new JavaMailAPI(this,mail,subject,message);
        javaMailAPI.execute();
    }

}

/*
 Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{txtDireccion.getText().toString()});
        intent.putExtra(Intent.EXTRA_SUBJECT,txtTema.getText().toString());
        intent.putExtra(Intent.EXTRA_TEXT,txtCuerpo.getText().toString());
        startActivity(intent.createChooser(intent, "Enviar mail"));
*/