package com.presupuestos2.model;

import java.io.Serializable;
import java.util.Arrays;

public class Budget implements Serializable {

    private String cliente, fecha, total;
    private String[] trabajos, detalles;

    public Budget(String cliente, String fecha, String total, String[] trabajos, String[] detalles) {
        this.cliente = cliente;
        this.fecha = fecha;
        this.total = total;
        this.trabajos = trabajos;
        this.detalles = detalles;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String[] getTrabajos() {
        return trabajos;
    }

    public void setTrabajos(String[] trabajos) {
        this.trabajos = trabajos;
    }

    public String[] getDetalles() {
        return detalles;
    }

    public void setDetalles(String[] detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "cliente='" + cliente + '\'' +
                ", fecha='" + fecha + '\'' +
                ", total='" + total + '\'' +
                ", trabajos=" + Arrays.toString(trabajos) +
                ", detalles=" + Arrays.toString(detalles) +
                '}';
    }
}
