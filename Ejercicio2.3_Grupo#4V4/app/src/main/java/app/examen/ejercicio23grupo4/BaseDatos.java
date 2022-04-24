package app.examen.ejercicio23grupo4;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {
    public BaseDatos(@Nullable Context context) {
        super(context, Registro.DB_NAME, null, Registro.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //Crea la tabla de la base de datos
        db.execSQL(Registro.CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        // actualizar la base de datos (si hay alguna estructura, cambie la versión de db)

        //descartar la tabla anterior si existe
        db.execSQL("DROP TABLE IF EXISTS "+ Registro.TABLE_NAME);
        //crear tabla de nuevo
        onCreate(db);
    }

    //Inserta datos a la base de datos
    public long insertRecord(String name, String image,String addedTime, String updatedTime ){

        //get databse grabable porque queremos escribir datos

        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues values = new ContentValues();


        // la identificación se insertará automáticamente cuando configuremos AUTOINCREMENTO en la consulta

        // inserta datos
        values.put(Registro.C_NAME, name);
        values.put(Registro.C_IMAGE, image);
        values.put(Registro.C_ADDED_TIMESTAMP, addedTime);
        values.put(Registro.C_UPDATED_TIMESTAMP, updatedTime);

        // insertar fila, devolverá la identificación del registro guardado
        long id = db.insert(Registro.TABLE_NAME, null, values);


        // cerrar db Connection
        db.close();


        // devuelve la identificación del registro insertado
        return id;

    }

    //Obtener todos datos
    public ArrayList<ModelRecord> getAllRecords(String orderBy){
        // la orden de consulta permitirá ordenar los datos más nuevo / más antiguo primero, nombre ascendente / descendente
        // devolverá la lista o registros ya que hemos utilizado return tipo ArrayList <ModelRecord>

        ArrayList<ModelRecord> recordsList = new ArrayList<>();
        // consulta para seleccionar registros
        String selectQuery = " SELECT * FROM " + Registro.TABLE_NAME + " ORDER BY " + orderBy;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // recorrer todos los registros y agregarlos a la lista
        if ( cursor.moveToFirst()){

            do {
                @SuppressLint("Range") ModelRecord modelRecord = new ModelRecord(
                        ""+cursor.getInt(cursor.getColumnIndex(Registro.C_ID)),
                        ""+cursor.getString(cursor.getColumnIndex(Registro.C_NAME)),
                        ""+cursor.getString(cursor.getColumnIndex(Registro.C_IMAGE)),
                        ""+cursor.getString(cursor.getColumnIndex(Registro.C_ADDED_TIMESTAMP)),
                        ""+cursor.getString(cursor.getColumnIndex(Registro.C_UPDATED_TIMESTAMP)));

                // Añadir registro a la list
                recordsList.add(modelRecord);
            }while (cursor.moveToNext());
        }

        //cierre de conexión db

        db.close();

        //retorna la lista
        return recordsList;
    }
    //Obtener el numero de registros
    public int getRecordsCount(){
        String countQuery = " SELECT * FROM " + Registro.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        return count;
    }


}
