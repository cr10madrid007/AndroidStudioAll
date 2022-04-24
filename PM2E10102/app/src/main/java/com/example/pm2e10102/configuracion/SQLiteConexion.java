package com.example.pm2e10102.configuracion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class SQLiteConexion extends SQLiteOpenHelper {

    // Cinstruccion de clase creacion de la base de datos en sqlite
    public SQLiteConexion(Context context, String dbname, SQLiteDatabase.CursorFactory factory, int version){
        super(context, dbname, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creacion de la primera Tabla
        db.execSQL(Transacciones.CreateTablePersonas);
        db.execSQL(Transacciones.CreateTablePais);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Eliminacion de las tablas al momento de eliminar la info de la bd -- bd limpia
        db.execSQL(Transacciones.DROPTablePersonas);
        db.execSQL(Transacciones.DROPTablePais);
        onCreate(db);
    }
}