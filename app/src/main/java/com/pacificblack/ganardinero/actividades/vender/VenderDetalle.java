package com.pacificblack.ganardinero.actividades.vender;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.pacificblack.ganardinero.R;
import com.pacificblack.ganardinero.clases.vendiendo.Vender;
import com.pacificblack.ganardinero.metodos.Abrir;
import com.squareup.picasso.Picasso;

import static com.pacificblack.ganardinero.constatnes.Constantes.Imagen;
import static com.pacificblack.ganardinero.constatnes.Constantes.Vacio;
import static com.pacificblack.ganardinero.metodos.Claves.IdClaveVender;

public class VenderDetalle extends AppCompatActivity {
    TextView titulo, des1, des2, des3, des4, des5;
    ImageView imagen1, imagen2, imagen3, imagen4, imagen5;
    AdView ban1, ban2, ban3, ban4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_vender);

        titulo = findViewById(R.id.titulo_detalle_vender);
        des1 = findViewById(R.id.descripcion1_detalle_vender);
        des2 = findViewById(R.id.descripcion2_detalle_vender);
        des3 = findViewById(R.id.descripcion3_detalle_vender);
        des4 = findViewById(R.id.descripcion4_detalle_vender);
        des5 = findViewById(R.id.descripcion5_detalle_vender);
        imagen1 = findViewById(R.id.imagen1_detalle_vender);
        imagen2 = findViewById(R.id.imagen2_detalle_vender);
        imagen3 = findViewById(R.id.imagen3_detalle_vender);
        imagen4 = findViewById(R.id.imagen4_detalle_vender);
        imagen5 = findViewById(R.id.imagen5_detalle_vender);

        AdRequest adRequest = new AdRequest.Builder().build();
        ban1 = findViewById(R.id.banner1_vender);
        ban2 = findViewById(R.id.banner2_vender);
        ban3 = findViewById(R.id.banner3_vender);
        ban4 = findViewById(R.id.banner4_vender);

        ban1.loadAd(adRequest);
        ban2.loadAd(adRequest);
        ban3.loadAd(adRequest);
        ban4.loadAd(adRequest);

        Bundle objetovender = getIntent().getExtras();

        Vender vender = null;

        if (objetovender != null) {

            vender = (Vender) objetovender.getSerializable(IdClaveVender);

            titulo.setText(vender.getTitulo());
            des1.setText(vender.getDes1());
            Picasso.get().load(vender.getImagen1()).placeholder(R.drawable.sample).error(R.drawable.sample).into(imagen1);
            final String imagen1_link = vender.getImagen1();
            imagen1.setOnClickListener(v -> {
                Intent intent = new Intent(getApplicationContext(), Abrir.class);
                Bundle envioimg = new Bundle();
                envioimg.putString(Imagen, imagen1_link);
                intent.putExtras(envioimg);
                startActivity(intent);
            });

            if (vender.getDes2().equals(Vacio)) {
                des2.setVisibility(View.GONE);
            } else {
                des2.setText(vender.getDes2());
            }
            if (vender.getDes3().equals(Vacio)) {
                des3.setVisibility(View.GONE);
            } else {
                des3.setText(vender.getDes3());
            }
            if (vender.getDes4().equals(Vacio)) {
                des4.setVisibility(View.GONE);
            } else {
                des4.setText(vender.getDes4());
            }
            if (vender.getDes5().equals(Vacio)) {
                des5.setVisibility(View.GONE);
            } else {
                des5.setText(vender.getDes5());
            }

            if (vender.getImagen2().equals(Vacio)) {
                imagen2.setVisibility(View.GONE);
            } else {
                Picasso.get().load(vender.getImagen2()).placeholder(R.drawable.sample).error(R.drawable.sample).into(imagen2);
                final String imagen2_link = vender.getImagen2();
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
            if (vender.getImagen3().equals(Vacio)) {
                imagen3.setVisibility(View.GONE);
            } else {
                Picasso.get().load(vender.getImagen3()).placeholder(R.drawable.sample).error(R.drawable.sample).into(imagen3);
                final String imagen3_link = vender.getImagen3();
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
            if (vender.getImagen4().equals(Vacio)) {
                imagen4.setVisibility(View.GONE);
            } else {
                Picasso.get().load(vender.getImagen4()).placeholder(R.drawable.sample).error(R.drawable.sample).into(imagen4);
                final String imagen4_link = vender.getImagen4();
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
            if (vender.getImagen5().equals(Vacio)) {
                imagen5.setVisibility(View.GONE);
            } else {
                Picasso.get().load(vender.getImagen5()).placeholder(R.drawable.sample).error(R.drawable.sample).into(imagen5);
                final String imagen5_link = vender.getImagen5();
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