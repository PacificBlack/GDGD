package com.pacificblack.ganardinero.ui.publicidad.actividad;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.button.MaterialButton;
import com.pacificblack.ganardinero.R;
import com.pacificblack.ganardinero.Slider.SliderAdapter;
import com.pacificblack.ganardinero.metodos.Abrir;
import com.pacificblack.ganardinero.ui.publicidad.clase.Publicidad;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import static com.pacificblack.ganardinero.constatnes.Constantes.Imagen;
import static com.pacificblack.ganardinero.constatnes.Constantes.Vacio;
import static com.pacificblack.ganardinero.metodos.Claves.IdClavePublicidad;

public class PublicidadDetalle extends AppCompatActivity {
    TextView titulo, des1, des2, des3, des4, des5;
    SliderView imagenes;
    AdView ban1, ban2, ban3, ban4;
    MaterialButton ir;

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
        imagenes = findViewById(R.id.imagenes_detalle_publicidad);
        ir = findViewById(R.id.ir_publicidad);


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

        Publicidad publicidad;

        if (objetopublicidad != null) {

            publicidad = (Publicidad) objetopublicidad.getSerializable(IdClavePublicidad);

            titulo.setText(publicidad.getTitulo());
            des1.setText(publicidad.getDes1());

            SliderAdapter adapter = new SliderAdapter(getApplicationContext(),publicidad.getImagenes());
            imagenes.setSliderAdapter(adapter);
            imagenes.setIndicatorAnimation(IndicatorAnimationType.WORM);
            imagenes.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
            imagenes.startAutoCycle();

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
            ir.setOnClickListener(v -> {
                Uri uri = Uri.parse(publicidad.getEnlace());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            });
        }

    }
}