package com.pacificblack.ganardinero.actividades.critomining;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.pacificblack.ganardinero.R;
import com.pacificblack.ganardinero.clases.critomining.Mineria;
import com.pacificblack.ganardinero.metodos.Abrir;
import com.squareup.picasso.Picasso;

import static com.pacificblack.ganardinero.constatnes.Constantes.Imagen;
import static com.pacificblack.ganardinero.constatnes.Constantes.Vacio;
import static com.pacificblack.ganardinero.metodos.Claves.IdClaveMineria;

public class MineriaDetalle extends AppCompatActivity {

    TextView titulo, des1, des2, des3, des4, des5;
    ImageView imagen1, imagen2, imagen3, imagen4, imagen5;
    AdView ban1, ban2, ban3, ban4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_mineria);

        titulo = findViewById(R.id.titulo_detalle_mineria);
        des1 = findViewById(R.id.descripcion1_detalle_mineria);
        des2 = findViewById(R.id.descripcion2_detalle_mineria);
        des3 = findViewById(R.id.descripcion3_detalle_mineria);
        des4 = findViewById(R.id.descripcion4_detalle_mineria);
        des5 = findViewById(R.id.descripcion5_detalle_mineria);
        imagen1 = findViewById(R.id.imagen1_detalle_mineria);
        imagen2 = findViewById(R.id.imagen2_detalle_mineria);
        imagen3 = findViewById(R.id.imagen3_detalle_mineria);
        imagen4 = findViewById(R.id.imagen4_detalle_mineria);
        imagen5 = findViewById(R.id.imagen5_detalle_mineria);

        AdRequest adRequest = new AdRequest.Builder().build();
        ban1 = findViewById(R.id.banner1_mineria);
        ban2 = findViewById(R.id.banner2_mineria);
        ban3 = findViewById(R.id.banner3_mineria);
        ban4 = findViewById(R.id.banner4_mineria);

        ban1.loadAd(adRequest);
        ban2.loadAd(adRequest);
        ban3.loadAd(adRequest);
        ban4.loadAd(adRequest);

        Bundle objetomineria = getIntent().getExtras();

        Mineria mineria = null;

        if (objetomineria != null) {

            mineria = (Mineria) objetomineria.getSerializable(IdClaveMineria);

            titulo.setText(mineria.getTitulo());
            des1.setText(mineria.getDes1());
            Picasso.get().load(mineria.getImagen1()).placeholder(R.drawable.sample).error(R.drawable.sample).into(imagen1);
            final String imagen1_link = mineria.getImagen1();
            imagen1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Abrir.class);
                    Bundle envioimg = new Bundle();
                    envioimg.putString(Imagen, imagen1_link);
                    intent.putExtras(envioimg);
                    startActivity(intent);
                }
            });

            if (mineria.getDes2().equals(Vacio)) {
                des2.setVisibility(View.GONE);
            } else {
                des2.setText(mineria.getDes2());
            }
            if (mineria.getDes3().equals(Vacio)) {
                des3.setVisibility(View.GONE);
            } else {
                des3.setText(mineria.getDes3());
            }
            if (mineria.getDes4().equals(Vacio)) {
                des4.setVisibility(View.GONE);
            } else {
                des4.setText(mineria.getDes4());
            }
            if (mineria.getDes5().equals(Vacio)) {
                des5.setVisibility(View.GONE);
            } else {
                des5.setText(mineria.getDes5());
            }

            if (mineria.getImagen2().equals(Vacio)) {
                imagen2.setVisibility(View.GONE);
            } else {
                Picasso.get().load(mineria.getImagen2()).placeholder(R.drawable.sample).error(R.drawable.sample).into(imagen2);
                final String imagen2_link = mineria.getImagen2();
                imagen2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), Abrir.class);
                        Bundle envioimg = new Bundle();
                        envioimg.putString(Imagen, imagen2_link);
                        intent.putExtras(envioimg);
                        startActivity(intent);
                    }
                });
            }
            if (mineria.getImagen3().equals(Vacio)) {
                imagen3.setVisibility(View.GONE);
            } else {
                Picasso.get().load(mineria.getImagen3()).placeholder(R.drawable.sample).error(R.drawable.sample).into(imagen3);
                final String imagen3_link = mineria.getImagen3();
                imagen3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), Abrir.class);
                        Bundle envioimg = new Bundle();
                        envioimg.putString(Imagen, imagen3_link);
                        intent.putExtras(envioimg);
                        startActivity(intent);
                    }
                });
            }
            if (mineria.getImagen4().equals(Vacio)) {
                imagen4.setVisibility(View.GONE);
            } else {
                Picasso.get().load(mineria.getImagen4()).placeholder(R.drawable.sample).error(R.drawable.sample).into(imagen4);
                final String imagen4_link = mineria.getImagen4();
                imagen4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), Abrir.class);
                        Bundle envioimg = new Bundle();
                        envioimg.putString(Imagen, imagen4_link);
                        intent.putExtras(envioimg);
                        startActivity(intent);
                    }
                });
            }
            if (mineria.getImagen5().equals(Vacio)) {
                imagen5.setVisibility(View.GONE);
            } else {
                Picasso.get().load(mineria.getImagen5()).placeholder(R.drawable.sample).error(R.drawable.sample).into(imagen5);
                final String imagen5_link = mineria.getImagen5();
                imagen5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), Abrir.class);
                        Bundle envioimg = new Bundle();
                        envioimg.putString(Imagen, imagen5_link);
                        intent.putExtras(envioimg);
                        startActivity(intent);
                    }
                });
            }

        }

    }
}