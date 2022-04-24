package com.example.pm2e10102.tablas;

public class Personas {
    private Integer id, telefono;
    private String nombres, nota, origen;

    public Personas(Integer id, Integer telefono, String nombres, String nota, String origen) {
        this.id = id;
        this.telefono = telefono;
        this.nombres = nombres;
        this.nota = nota;
        this.origen = origen;
    }
    public Personas() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }
}
