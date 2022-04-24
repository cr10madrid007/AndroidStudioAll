package com.example.pm2e10102.configuracion;

public class Transacciones {
    // nombre de la bd
    public  static final String NameDatabase="PM2E1";

    // Tablas de la base de datos

    public static final String tablaPersonas = "personas";
    public static final String tablaPais = "tpais";

    public static final String id = "id";
    public static final String nombres = "nombres";
    public static final String telefono = "telefono";
    public static final String nota = "nota";
    public static final String pais = "pais";
    public static final String origen = "origen";



    // Transacciones DDL(Data Definition Lenguaje) Tabla personas

    public static final String CreateTablePersonas = "CREATE TABLE personas(id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "nombres TEXT,"+
            "telefono INTEGER,"+
            "origen TEXT,"+
            "nota TEXT)";

    public static final String CreateTablePais = "CREATE TABLE tpais (id INTEGER PRIMARY KEY AUTOINCREMENT, pais TEXT)";


    public static final String DROPTablePersonas= "DROP TABLE IF EXIST personas";
    public static final String DROPTablePais= "DROP TABLE IF EXIST tpais";
}
