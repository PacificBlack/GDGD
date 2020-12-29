package com.pacificblack.ganardinero.clases.critomining;

import java.io.Serializable;

public class Mineria implements Serializable {
    String titulo, des1, des2, des3, des4, des5, fecha;
    String imagen1, imagen2, imagen3, imagen4, imagen5;

    public Mineria() {
    }

    public Mineria(String titulo, String des1, String des2, String des3, String des4, String des5, String fecha, String imagen1, String imagen2, String imagen3, String imagen4, String imagen5) {
        this.titulo = titulo;
        this.des1 = des1;
        this.des2 = des2;
        this.des3 = des3;
        this.des4 = des4;
        this.des5 = des5;
        this.fecha = fecha;
        this.imagen1 = imagen1;
        this.imagen2 = imagen2;
        this.imagen3 = imagen3;
        this.imagen4 = imagen4;
        this.imagen5 = imagen5;
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

    public String getImagen1() {
        return imagen1;
    }

    public void setImagen1(String imagen1) {
        this.imagen1 = imagen1;
    }

    public String getImagen2() {
        return imagen2;
    }

    public void setImagen2(String imagen2) {
        this.imagen2 = imagen2;
    }

    public String getImagen3() {
        return imagen3;
    }

    public void setImagen3(String imagen3) {
        this.imagen3 = imagen3;
    }

    public String getImagen4() {
        return imagen4;
    }

    public void setImagen4(String imagen4) {
        this.imagen4 = imagen4;
    }

    public String getImagen5() {
        return imagen5;
    }

    public void setImagen5(String imagen5) {
        this.imagen5 = imagen5;
    }
}
