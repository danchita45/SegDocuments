package com.example.segdocuments;

public class Persona {
    private int edad;
    private String nombre;
    private String puesto;
    private String horaEntrada;
    private String horaSalida;
    private String contraseña;

    // Constructor
    public Persona() {
        this.edad = edad;
        this.nombre = nombre;
        this.puesto = puesto;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.contraseña = contraseña;
    }

    // Getters y Setters
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
