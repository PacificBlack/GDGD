package com.pacificblack.ganardinero.actividades.desarrollo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.pacificblack.ganardinero.R;
import com.pacificblack.ganardinero.clases.desarrollo.Desarrollo;
import com.pacificblack.ganardinero.metodos.Abrir;
import com.squareup.picasso.Picasso;

import static com.pacificblack.ganardinero.constatnes.Constantes.Imagen;
import static com.pacificblack.ganardinero.constatnes.Constantes.Vacio;
import static com.pacificblack.ganardinero.metodos.Claves.IdClaveDesarrollo;

public class DesarrolloDetalle extends AppCompatActivity {
    TextView titulo, des1, des2, des3, des4, des5;
    ImageView imagen1, imagen2, imagen3, imagen4, imagen5;
    AdView ban1, ban2, ban3, ban4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_desarrollo);

        titulo = findViewById(R.id.titulo_detalle_desarrollo);
        des1 = findViewById(R.id.descripcion1_detalle_desarrollo);
        des2 = findViewById(R.id.descripcion2_detalle_desarrollo);
        des3 = findViewById(R.id.descripcion3_detalle_desarrollo);
        des4 = findViewById(R.id.descripcion4_detalle_desarrollo);
        des5 = findViewById(R.id.descripcion5_detalle_desarrollo);
        imagen1 = findViewById(R.id.imagen1_detalle_desarrollo);
        imagen2 = findViewById(R.id.imagen2_detalle_desarrollo);
        imagen3 = findViewById(R.id.imagen3_detalle_desarrollo);
        imagen4 = findViewById(R.id.imagen4_detalle_desarrollo);
        imagen5 = findViewById(R.id.imagen5_detalle_desarrollo);

        AdRequest adRequest = new AdRequest.Builder().build();
        ban1 = findViewById(R.id.banner1_desarrollo);
        ban2 = findViewById(R.id.banner2_desarrollo);
        ban3 = findViewById(R.id.banner3_desarrollo);
        ban4 = findViewById(R.id.banner4_desarrollo);

        ban1.loadAd(adRequest);
        ban2.loadAd(adRequest);
        ban3.loadAd(adRequest);
        ban4.loadAd(adRequest);

        Bundle objetodesarrollo = getIntent().getExtras();

        Desarrollo desarrollo = null;

        if (objetodesarrollo != null) {

            desarrollo = (Desarrollo) objetodesarrollo.getSerializable(IdClaveDesarrollo);

            titulo.setText(desarrollo.getTitulo());
            des1.setText(desarrollo.getDes1());
            Picasso.get().load(desarrollo.getImagen1()).placeholder(R.drawable.sample).error(R.drawable.sample).into(imagen1);
            final String imagen1_link = desarrollo.getImagen1();
            imagen1.setOnClickListener(v -> {
                Intent intent = new Intent(getApplicationContext(), Abrir.class);
                Bundle envioimg = new Bundle();
                envioimg.putString(Imagen, imagen1_link);
                intent.putExtras(envioimg);
                startActivity(intent);
            });

            if (desarrollo.getDes2().equals(Vacio)) {
                des2.setVisibility(View.GONE);
            } else {
                des2.setText(desarrollo.getDes2());
            }
            if (desarrollo.getDes3().equals(Vacio)) {
                des3.setVisibility(View.GONE);
            } else {
                des3.setText(desarrollo.getDes3());
            }
            if (desarrollo.getDes4().equals(Vacio)) {
                des4.setVisibility(View.GONE);
            } else {
                des4.setText(desarrollo.getDes4());
            }
            if (desarrollo.getDes5().equals(Vacio)) {
                des5.setVisibility(View.GONE);
            } else {
                des5.setText(desarrollo.getDes5());
            }

            if (desarrollo.getImagen2().equals(Vacio)) {
                imagen2.setVisibility(View.GONE);
            } else {
                Picasso.get().load(desarrollo.getImagen2()).placeholder(R.drawable.sample).error(R.drawable.sample).into(imagen2);
                final String imagen2_link = desarrollo.getImagen2();
                imagen2.setOnClickListener(v -> {
                    Intent intent = new Intent(getApplicationContext(), Abrir.class);
                    Bundle envioimg = new Bundle();
                    envioimg.putString(Imagen, imagen2_link);
                    intent.putExtras(envioimg);
                    startActivity(intent);
                });
            }
            if (desarrollo.getImagen3().equals(Vacio)) {
                imagen3.setVisibility(View.GONE);
            } else {
                Picasso.get().load(desarrollo.getImagen3()).placeholder(R.drawable.sample).error(R.drawable.sample).into(imagen3);
                final String imagen3_link = desarrollo.getImagen3();
                imagen3.setOnClickListener(v -> {
                    Intent intent = new Intent(getApplicationContext(), Abrir.class);
                    Bundle envioimg = new Bundle();
                    envioimg.putString(Imagen, imagen3_link);
                    intent.putExtras(envioimg);
                    startActivity(intent);
                });
            }
            if (desarrollo.getImagen4().equals(Vacio)) {
                imagen4.setVisibility(View.GONE);
            } else {
                Picasso.get().load(desarrollo.getImagen4()).placeholder(R.drawable.sample).error(R.drawable.sample).into(imagen4);
                final String imagen4_link = desarrollo.getImagen4();
                imagen4.setOnClickListener(v -> {
                    Intent intent = new Intent(getApplicationContext(), Abrir.class);
                    Bundle envioimg = new Bundle();
                    envioimg.putString(Imagen, imagen4_link);
                    intent.putExtras(envioimg);
                    startActivity(intent);
                });
            }
            if (desarrollo.getImagen5().equals(Vacio)) {
                imagen5.setVisibility(View.GONE);
            } else {
                Picasso.get().load(desarrollo.getImagen5()).placeholder(R.drawable.sample).error(R.drawable.sample).into(imagen5);
                final String imagen5_link = desarrollo.getImagen5();
                imagen5.setOnClickListener(v -> {
                    Intent intent = new Intent(getApplicationContext(), Abrir.class);
                    Bundle envioimg = new Bundle();
                    envioimg.putString(Imagen, imagen5_link);
                    intent.putExtras(envioimg);
                    startActivity(intent);
                });
            }

        }
    }
}