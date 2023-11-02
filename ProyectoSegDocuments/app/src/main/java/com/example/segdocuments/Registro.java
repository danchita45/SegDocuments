package com.example.segdocuments;

import java.util.Date;

public class Registro {
    private String Description;
    private int IdRegistro;
    private Date Fecha;
    private int IdPersona;

    // Getter y Setter para Description
    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    // Getter y Setter para IdRegistro
    public int getIdRegistro() {
        return IdRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.IdRegistro = idRegistro;
    }

    // Getter y Setter para Fecha
    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        this.Fecha = fecha;
    }

    // Getter y Setter para IdPersona
    public int getIdPersona() {
        return IdPersona;
    }

    public void setIdPersona(int idPersona) {
        this.IdPersona = idPersona;
    }


}
