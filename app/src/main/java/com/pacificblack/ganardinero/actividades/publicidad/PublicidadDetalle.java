package com.pacificblack.ganardinero.actividades.publicidad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.pacificblack.ganardinero.R;
import com.pacificblack.ganardinero.clases.publicidad.Publicidad;
import com.pacificblack.ganardinero.metodos.Abrir;
import com.squareup.picasso.Picasso;

import static com.pacificblack.ganardinero.constatnes.Constantes.Imagen;
import static com.pacificblack.ganardinero.constatnes.Constantes.Vacio;
import static com.pacificblack.ganardinero.metodos.Claves.IdClavePublicidad;

public class PublicidadDetalle extends AppCompatActivity {
    TextView titulo, des1, des2, des3, des4, des5;
    ImageView imagen1, imagen2, imagen3, imagen4, imagen5;
    AdView ban1, ban2, ban3, ban4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_publicidad);

        titulo = findViewById(R.id.titulo_detalle_publicidad);
        des1 = findViewById(R.id.descripcion1_detalle_publicidad);
        des2 = findViewById(R.id.descripcion2_detalle_publicidad);
        des3 = findViewById(R.id.descripcion3_detalle_publicidad);
        des4 = findViewById(R.id.descripcion4_detalle_publicidad);
        des5 = findViewById(R.id.descripcion5_detalle_publicidad);
        imagen1 = findViewById(R.id.imagen1_detalle_publicidad);
        imagen2 = findViewById(R.id.imagen2_detalle_publicidad);
        imagen3 = findViewById(R.id.imagen3_detalle_publicidad);
        imagen4 = findViewById(R.id.imagen4_detalle_publicidad);
        imagen5 = findViewById(R.id.imagen5_detalle_publicidad);

        AdRequest adRequest = new AdRequest.Builder().build();
        ban1 = findViewById(R.id.banner1_publicidad);
        ban2 = findViewById(R.id.banner2_publicidad);
        ban3 = findViewById(R.id.banner3_publicidad);
        ban4 = findViewById(R.id.banner4_publicidad);

        ban1.loadAd(adRequest);
        ban2.loadAd(adRequest);
        ban3.loadAd(adRequest);
        ban4.loadAd(adRequest);

        Bundle objetopublicidad = getIntent().getExtras();

        Publicidad publicidad = null;

        if (objetopublicidad != null) {

            publicidad = (Publicidad) objetopublicidad.getSerializable(IdClavePublicidad);

            titulo.setText(publicidad.getTitulo());
            des1.setText(publicidad.getDes1());
            Picasso.get().load(publicidad.getImagen1()).placeholder(R.drawable.sample).error(R.drawable.sample).into(imagen1);
            final String imagen1_link = publicidad.getImagen1();
            imagen1.setOnClickListener(v -> {
                Intent intent = new Intent(getApplicationContext(), Abrir.class);
                Bundle envioimg = new Bundle();
                envioimg.putString(Imagen, imagen1_link);
                intent.putExtras(envioimg);
                startActivity(intent);
            });

            if (publicidad.getDes2().equals(Vacio)) {
                des2.setVisibility(View.GONE);
            } else {
                des2.setText(publicidad.getDes2());
            }
            if (publicidad.getDes3().equals(Vacio)) {
                des3.setVisibility(View.GONE);
            } else {
                des3.setText(publicidad.getDes3());
            }
            if (publicidad.getDes4().equals(Vacio)) {
                des4.setVisibility(View.GONE);
            } else {
                des4.setText(publicidad.getDes4());
            }
            if (publicidad.getDes5().equals(Vacio)) {
                des5.setVisibility(View.GONE);
            } else {
                des5.setText(publicidad.getDes5());
            }

            if (publicidad.getImagen2().equals(Vacio)) {
                imagen2.setVisibility(View.GONE);
            } else {
                Picasso.get().load(publicidad.getImagen2()).placeholder(R.drawable.sample).error(R.drawable.sample).into(imagen2);
                final String imagen2_link = publicidad.getImagen2();
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
            if (publicidad.getImagen3().equals(Vacio)) {
                imagen3.setVisibility(View.GONE);
            } else {
                Picasso.get().load(publicidad.getImagen3()).placeholder(R.drawable.sample).error(R.drawable.sample).into(imagen3);
                final String imagen3_link = publicidad.getImagen3();
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
            if (publicidad.getImagen4().equals(Vacio)) {
                imagen4.setVisibility(View.GONE);
            } else {
                Picasso.get().load(publicidad.getImagen4()).placeholder(R.drawable.sample).error(R.drawable.sample).into(imagen4);
                final String imagen4_link = publicidad.getImagen4();
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
            if (publicidad.getImagen5().equals(Vacio)) {
                imagen5.setVisibility(View.GONE);
            } else {
                Picasso.get().load(publicidad.getImagen5()).placeholder(R.drawable.sample).error(R.drawable.sample).into(imagen5);
                final String imagen5_link = publicidad.getImagen5();
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