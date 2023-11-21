package com.example.segdocuments.ui;

import android.net.Uri;

import java.util.Date;

public class Entrada {
    private int idEntrada;
    private int numeroCasa;
    private String calle;
    private String placa;
    private String status;
    private Uri imagen;
    private String imagenUrl;
    private String FechaEntrada;
    private String HoraEntrada;
    private String FechaSalida;
    private String HoraSalida;


    // Constructor
    public Entrada(int idEntrada, int numeroCasa, String calle, String placa, String status, String s,String FechaSalida, String FechaEntrada) {
        this.idEntrada = idEntrada;
        this.numeroCasa = numeroCasa;
        this.calle = calle;
        this.placa = placa;
        this.status = status;
        this.imagen = imagen;
        this.FechaEntrada = FechaEntrada;
        this.FechaSalida = FechaSalida;
    }

    public Entrada() {

    }

    // Métodos getters y setters

    public int getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(int idEntrada) {
        this.idEntrada = idEntrada;
    }

    public int getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(int numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




    public String getFechaEntrada() {
        return FechaEntrada;
    }
    public String getFechaSalida() {
        return FechaSalida;
    }

    public void setFechaEntrada(String Fecha) {
        this.FechaEntrada = Fecha;
    }
    public void setFechaSalida(String FechaSalida) {
        this.FechaSalida = FechaSalida;
    }
    public Uri getImagen() {
        return imagen;
    }

    public void setImagen(Uri imagen) {
        this.imagen = imagen;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }
}
