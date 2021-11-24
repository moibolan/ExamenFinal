package com.progra.productos.entities;

public class Consulta {

    private String operacion;
    private int cantidad;

    public Consulta(String operacion, int cantidad) {
        this.operacion = operacion;
        this.cantidad = cantidad;
    }

    public Consulta() {
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
