package app.examen.ejercicio23grupo4;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetalleRegistroActivity extends AppCompatActivity {


    //vistas
    private CircleImageView profileIv;
    private TextView bioTv, nameTv, phoneTv, emailTv, dobTv, addedTimeTv, updatedTimeTv;

    //ActionBar
    private ActionBar actionBar;

    //
    private String recordID;

    //BDHelper
    private BaseDatos dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_registro);
        //setting up actionbar with title and back button
        actionBar = getSupportActionBar();
        actionBar.setTitle("Detalle del Registro");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //obtener la identificación de registro del adaptador mediante la intención
        Intent intent = getIntent();
        recordID = intent.getStringExtra("RECORD_ID");

        //Inicializacion BD Helper Clase
        dbHelper = new BaseDatos(this);

        //Inicializamos las vistas
        profileIv = findViewById(R.id.profileIv);
        bioTv = findViewById(R.id.bioTv);
        nameTv = findViewById(R.id.nameTv);
        addedTimeTv = findViewById(R.id.addedTimeTv);
        updatedTimeTv = findViewById(R.id.updateTimeTv);

        showRecordDetails();
    }

    private void showRecordDetails() {
        //obtener detalles de registro
        //consulta para seleccionar el registro basado en la identificación del registro
        String selectQuery = " SELECT * FROM " + Registro.TABLE_NAME + " WHERE " + Registro.C_ID +" =\""+ recordID+"\"";

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // mantener comprobando toda la base de datos para ese registro
        if (cursor.moveToFirst()){
            do {

                //Obtenner datos
                @SuppressLint("Range") String id = ""+ cursor.getInt(cursor.getColumnIndex(Registro.C_ID));
                @SuppressLint("Range") String name = ""+ cursor.getString(cursor.getColumnIndex(Registro.C_NAME));
                @SuppressLint("Range") String image = ""+ cursor.getString(cursor.getColumnIndex(Registro.C_IMAGE));
                @SuppressLint("Range") String timestampAdded = ""+ cursor.getString(cursor.getColumnIndex(Registro.C_ADDED_TIMESTAMP));
                @SuppressLint("Range") String timestampUpdated = ""+ cursor.getString(cursor.getColumnIndex(Registro.C_UPDATED_TIMESTAMP));

                //Convertir marca de tiempo a dd/mm/yyyy hh:mm por ejemplo 10/04/2020 08:22 AM
                Calendar calendar1 = Calendar.getInstance(Locale.getDefault());
                calendar1.setTimeInMillis(Long.parseLong(timestampAdded));
                String timeAdded = ""+ DateFormat.format("dd/MM/yyyy hh:mm:aa", calendar1);

                Calendar calendar2 = Calendar.getInstance(Locale.getDefault());
                calendar2.setTimeInMillis(Long.parseLong(timestampUpdated));
                String timeupdated = ""+ DateFormat.format("dd/MM/yyyy hh:mm:aa", calendar2);

                //Establecer datos
                nameTv.setText(name);
                addedTimeTv.setText(timeAdded);
                updatedTimeTv.setText(timeupdated);

                // si el usuario no adjunta la imagen, imageUri será nulo, por lo tanto,
                // configure una imagen predeterminada en ese caso
                if (image.equals("null")){
                    // no hay imagen en el registro, establecer predeterminado
                    profileIv.setImageResource(R.drawable.ic_person_black);
                }
                else {
                    // tener imagen en el registro
                    profileIv.setImageURI(Uri.parse(image));
                }


            }while(cursor.moveToNext());
        }
        db.close();
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();//ir a la actividad anterior
        return super.onSupportNavigateUp();
    }
}
