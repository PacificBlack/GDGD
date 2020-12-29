package com.pacificblack.ganardinero.actividades.workhome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.pacificblack.ganardinero.R;
import com.pacificblack.ganardinero.clases.workhome.WorkHome;
import com.pacificblack.ganardinero.metodos.Abrir;
import com.squareup.picasso.Picasso;

import static com.pacificblack.ganardinero.constatnes.Constantes.Imagen;
import static com.pacificblack.ganardinero.constatnes.Constantes.Vacio;
import static com.pacificblack.ganardinero.metodos.Claves.IdClaveWorkHome;

public class WorkHomeDetalle extends AppCompatActivity {
    TextView titulo, des1, des2, des3, des4, des5;
    ImageView imagen1, imagen2, imagen3, imagen4, imagen5;
    AdView ban1, ban2, ban3, ban4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_workhome);

        titulo = findViewById(R.id.titulo_detalle_workhome);
        des1 = findViewById(R.id.descripcion1_detalle_workhome);
        des2 = findViewById(R.id.descripcion2_detalle_workhome);
        des3 = findViewById(R.id.descripcion3_detalle_workhome);
        des4 = findViewById(R.id.descripcion4_detalle_workhome);
        des5 = findViewById(R.id.descripcion5_detalle_workhome);
        imagen1 = findViewById(R.id.imagen1_detalle_workhome);
        imagen2 = findViewById(R.id.imagen2_detalle_workhome);
        imagen3 = findViewById(R.id.imagen3_detalle_workhome);
        imagen4 = findViewById(R.id.imagen4_detalle_workhome);
        imagen5 = findViewById(R.id.imagen5_detalle_workhome);

        AdRequest adRequest = new AdRequest.Builder().build();
        ban1 = findViewById(R.id.banner1_workhome);
        ban2 = findViewById(R.id.banner2_workhome);
        ban3 = findViewById(R.id.banner3_workhome);
        ban4 = findViewById(R.id.banner4_workhome);

        ban1.loadAd(adRequest);
        ban2.loadAd(adRequest);
        ban3.loadAd(adRequest);
        ban4.loadAd(adRequest);

        Bundle objetoworkhome = getIntent().getExtras();

        WorkHome workhome = null;

        if (objetoworkhome != null) {

            workhome = (WorkHome) objetoworkhome.getSerializable(IdClaveWorkHome);

            titulo.setText(workhome.getTitulo());
            des1.setText(workhome.getDes1());
            Picasso.get().load(workhome.getImagen1()).placeholder(R.drawable.sample).error(R.drawable.sample).into(imagen1);
            final String imagen1_link = workhome.getImagen1();
            imagen1.setOnClickListener(v -> {
                Intent intent = new Intent(getApplicationContext(), Abrir.class);
                Bundle envioimg = new Bundle();
                envioimg.putString(Imagen, imagen1_link);
                intent.putExtras(envioimg);
                startActivity(intent);
            });

            if (workhome.getDes2().equals(Vacio)) {
                des2.setVisibility(View.GONE);
            } else {
                des2.setText(workhome.getDes2());
            }
            if (workhome.getDes3().equals(Vacio)) {
                des3.setVisibility(View.GONE);
            } else {
                des3.setText(workhome.getDes3());
            }
            if (workhome.getDes4().equals(Vacio)) {
                des4.setVisibility(View.GONE);
            } else {
                des4.setText(workhome.getDes4());
            }
            if (workhome.getDes5().equals(Vacio)) {
                des5.setVisibility(View.GONE);
            } else {
                des5.setText(workhome.getDes5());
            }

            if (workhome.getImagen2().equals(Vacio)) {
                imagen2.setVisibility(View.GONE);
            } else {
                Picasso.get().load(workhome.getImagen2()).placeholder(R.drawable.sample).error(R.drawable.sample).into(imagen2);
                final String imagen2_link = workhome.getImagen2();
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
            if (workhome.getImagen3().equals(Vacio)) {
                imagen3.setVisibility(View.GONE);
            } else {
                Picasso.get().load(workhome.getImagen3()).placeholder(R.drawable.sample).error(R.drawable.sample).into(imagen3);
                final String imagen3_link = workhome.getImagen3();
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
            if (workhome.getImagen4().equals(Vacio)) {
                imagen4.setVisibility(View.GONE);
            } else {
                Picasso.get().load(workhome.getImagen4()).placeholder(R.drawable.sample).error(R.drawable.sample).into(imagen4);
                final String imagen4_link = workhome.getImagen4();
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
            if (workhome.getImagen5().equals(Vacio)) {
                imagen5.setVisibility(View.GONE);
            } else {
                Picasso.get().load(workhome.getImagen5()).placeholder(R.drawable.sample).error(R.drawable.sample).into(imagen5);
                final String imagen5_link = workhome.getImagen5();
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