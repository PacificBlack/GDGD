package com.pacificblack.ganardinero.ui.creandovideos.clase;

import com.pacificblack.ganardinero.clases.Padre.Padre;

import java.io.Serializable;

public class Videos extends Padre {

    public Videos(String titulo, String des1, String des2, String des3, String des4, String des5, String fecha, String enlace, int cantidad, String[] imagenes) {
        super(titulo, des1, des2, des3, des4, des5, fecha, enlace, cantidad, imagenes);
    }
}
