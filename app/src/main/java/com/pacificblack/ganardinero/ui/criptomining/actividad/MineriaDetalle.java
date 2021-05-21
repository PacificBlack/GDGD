package com.pacificblack.ganardinero.ui.criptomining.actividad;

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
import com.pacificblack.ganardinero.ui.criptomining.clase.Mineria;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import static com.pacificblack.ganardinero.constatnes.Constantes.Vacio;
import static com.pacificblack.ganardinero.metodos.Claves.IdClaveMineria;

public class MineriaDetalle extends AppCompatActivity {

    TextView titulo, des1, des2, des3, des4, des5;
    SliderView imagenes;
    AdView ban1, ban2, ban3, ban4;
    MaterialButton ir;

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
        imagenes = findViewById(R.id.imagenes_detalle_mineria);
        ir = findViewById(R.id.ir_mineria);

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

        Mineria mineria;

        if (objetomineria != null) {

            mineria = (Mineria) objetomineria.getSerializable(IdClaveMineria);

            titulo.setText(mineria.getTitulo());
            des1.setText(mineria.getDes1());

            SliderAdapter adapter = new SliderAdapter(getApplicationContext(), mineria.getImagenes());
            imagenes.setSliderAdapter(adapter);
            imagenes.setIndicatorAnimation(IndicatorAnimationType.WORM);
            imagenes.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
            imagenes.startAutoCycle();

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

            ir.setOnClickListener(v -> {
                Uri uri = Uri.parse(mineria.getEnlace());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            });

        }

    }
}