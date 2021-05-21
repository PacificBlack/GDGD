package com.pacificblack.ganardinero.clases.Padre;

import java.io.Serializable;

public class Padre implements Serializable {
    protected String titulo, des1, des2, des3, des4, des5, fecha,enlace;
    protected int cantidad;
    protected String [] imagenes;

    public Padre(String titulo, String des1, String des2, String des3, String des4, String des5, String fecha, String enlace, int cantidad, String[] imagenes) {
        this.titulo = titulo;
        this.des1 = des1;
        this.des2 = des2;
        this.des3 = des3;
        this.des4 = des4;
        this.des5 = des5;
        this.fecha = fecha;
        this.enlace = enlace;
        this.cantidad = cantidad;
        this.imagenes = imagenes;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDes1() {
        return des1;
    }

    public void setDes1(String des1) {
        this.des1 = des1;
    }

    public String getDes2() {
        return des2;
    }

    public void setDes2(String des2) {
        this.des2 = des2;
    }

    public String getDes3() {
        return des3;
    }

    public void setDes3(String des3) {
        this.des3 = des3;
    }

    public String getDes4() {
        return des4;
    }

    public void setDes4(String des4) {
        this.des4 = des4;
    }

    public String getDes5() {
        return des5;
    }

    public void setDes5(String des5) {
        this.des5 = des5;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String[] getImagenes() {
        return imagenes;
    }

    public void setImagenes(String[] imagenes) {
        this.imagenes = imagenes;
    }
}
