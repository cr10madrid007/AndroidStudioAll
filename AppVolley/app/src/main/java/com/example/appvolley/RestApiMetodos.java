package com.example.appvolley;

public class RestApiMetodos {

    private static final String ipAddress = "192.168.0.4";
    public static final String GETendPoint = "listaempleados.php";
    public static final String POSTendPoint = "crear.php";
    public static final String POSphoto= "UploadFile.php";

    public static final String GetApiEmple = "http://" + ipAddress + "/CRUD-PHP/" + GETendPoint;
    public static final String PostApiEmple = "http://" + ipAddress + "/CRUD-PHP/" + POSTendPoint;
    public  static final String UploadFileApiEmple = "http://" + ipAddress + "/CRUD-PHP/" + POSphoto;

    public static final String fieldNombre = "nombre";
    public static final String fieldApellido = "apellidos";
    public static final String fieldEdad = "edad";
}
