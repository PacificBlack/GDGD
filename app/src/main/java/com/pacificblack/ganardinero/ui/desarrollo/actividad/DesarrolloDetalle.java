package com.pacificblack.ganardinero.ui.desarrollo.actividad;

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
import com.pacificblack.ganardinero.ui.desarrollo.clase.Desarrollo;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import static com.pacificblack.ganardinero.constatnes.Constantes.Vacio;
import static com.pacificblack.ganardinero.metodos.Claves.IdClaveDesarrollo;

public class DesarrolloDetalle extends AppCompatActivity {
    TextView titulo, des1, des2, des3, des4, des5;
    SliderView imagenes;
    AdView ban1, ban2, ban3, ban4;
    MaterialButton ir;

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
        imagenes = findViewById(R.id.imagenes_detalle_desarrollo);
        ir = findViewById(R.id.ir_desarrollo);


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

        Desarrollo desarrollo;

        if (objetodesarrollo != null) {

            desarrollo = (Desarrollo) objetodesarrollo.getSerializable(IdClaveDesarrollo);

            titulo.setText(desarrollo.getTitulo());
            des1.setText(desarrollo.getDes1());

            SliderAdapter adapter = new SliderAdapter(getApplicationContext(),desarrollo.getImagenes());
            imagenes.setSliderAdapter(adapter);
            imagenes.setIndicatorAnimation(IndicatorAnimationType.WORM);
            imagenes.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
            imagenes.startAutoCycle();

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
            ir.setOnClickListener(v -> {
                Uri uri = Uri.parse(desarrollo.getEnlace());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            });
        }
    }
}