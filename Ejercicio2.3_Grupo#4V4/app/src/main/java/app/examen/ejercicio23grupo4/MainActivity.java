package app.examen.ejercicio23grupo4;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    //Views
    private FloatingActionButton addRecordBtn;

    //RecyclerView
    private RecyclerView recordsRv;

    //DB Helper
    private BaseDatos dbHelper;

    //Action Bar
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addfoto=(Button) findViewById(R.id.btnAddFoto);

        recordsRv = findViewById(R.id.recordsRv);
        //Inicializamos db helper Clase
        dbHelper = new BaseDatos(this);

        //Inicializacion ActionBar
        actionBar = getSupportActionBar();
        actionBar.setTitle("Registros");

        // Cargando Registros
        loadRecords();

        addfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityGuardarFoto.class);
                startActivity(intent);
            }
        });
    }
    private void loadRecords(){
        AdapterRecord adapterRecord = new AdapterRecord(MainActivity.this,
                dbHelper.getAllRecords(Registro.C_ADDED_TIMESTAMP + " DESC"));

        recordsRv.setAdapter(adapterRecord);

        //Establecer el numero de Registros
        actionBar.setSubtitle("Total: "+dbHelper.getRecordsCount());
    }
}