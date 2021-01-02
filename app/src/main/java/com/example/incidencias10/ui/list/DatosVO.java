package com.example.incidencias10.ui.list;

public class DatosVO {

    private String ID;
    private String title;
    private String incidencia;
    private String descripcion;
    private String estado;
    private String fecha;

    public DatosVO() {

    }

    public DatosVO(String ID, String title, String incidencia, String descripcion, String estado, String fecha) {
        this.ID = ID;
        this.title = title;
        this.incidencia = incidencia;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(String incidencia) {
        this.incidencia = incidencia;
    }
}
