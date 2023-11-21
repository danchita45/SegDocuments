package com.example.segdocuments;

public class TableItem {
    private String NumCasa;
    private String Calle;
    private String Placas;
    private String Hora;
    private String Fecha;
    // ...

    public TableItem(String NumCasa, String Calle, String Placas, String Hora, String Fecha /*, ...*/) {
        this.NumCasa = NumCasa;
        this.Calle = Calle;
        this.Placas = Placas;
        this.Hora = Hora;
        this.Fecha = Fecha;
        // ...
    }
    public TableItem() {
        // Necesario para Firebase (puede estar vacío)
    }

    public String getNumCasa() {
        return NumCasa;
    }

    public void setNumCasa(String NumCasa) {
        this.NumCasa = NumCasa;
    }

    public String getCalle() {
        return Calle;
    }

    public void setCalle(String Calle) {
        this.Calle = Calle;
    }

    public String getPlacas() {
        return Placas;
    }

    public void setPlacas(String Placas) {
        this.Placas = Placas;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }
}
