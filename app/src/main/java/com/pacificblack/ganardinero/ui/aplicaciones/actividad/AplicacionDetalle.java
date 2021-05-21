package com.pacificblack.ganardinero.ui.aplicaciones.actividad;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.button.MaterialButton;
import com.pacificblack.ganardinero.R;
import com.pacificblack.ganardinero.Slider.SliderAdapter;
import com.pacificblack.ganardinero.ui.aplicaciones.clases.Aplicaciones;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import static com.pacificblack.ganardinero.constatnes.Constantes.Vacio;
import static com.pacificblack.ganardinero.metodos.Claves.IdClaveAplicaciones;

public class AplicacionDetalle extends AppCompatActivity {

    TextView titulo, des1, des2, des3, des4, des5;
    SliderView imagenes;
    AdView ban1, ban2, ban3, ban4;
    MaterialButton ir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_aplicacion);

        titulo = findViewById(R.id.titulo_detalle_aplicaciones);
        des1 = findViewById(R.id.descripcion1_detalle_aplicaciones);
        des2 = findViewById(R.id.descripcion2_detalle_aplicaciones);
        des3 = findViewById(R.id.descripcion3_detalle_aplicaciones);
        des4 = findViewById(R.id.descripcion4_detalle_aplicaciones);
        des5 = findViewById(R.id.descripcion5_detalle_aplicaciones);
        imagenes = findViewById(R.id.imagenes_detalle_aplicaciones);
        ir = findViewById(R.id.ir_aplicaciones);

        AdRequest adRequest = new AdRequest.Builder().build();
        ban1 = findViewById(R.id.banner1_aplicaciones);
        ban2 = findViewById(R.id.banner2_aplicaciones);
        ban3 = findViewById(R.id.banner3_aplicaciones);
        ban4 = findViewById(R.id.banner4_aplicaciones);

        ban1.loadAd(adRequest);
        ban2.loadAd(adRequest);
        ban3.loadAd(adRequest);
        ban4.loadAd(adRequest);

        Bundle objetoAplicaciones = getIntent().getExtras();

        Aplicaciones aplicaciones;

        if (objetoAplicaciones != null) {

            aplicaciones = (Aplicaciones) objetoAplicaciones.getSerializable(IdClaveAplicaciones);

            SliderAdapter adapter = new SliderAdapter(getApplicationContext(),aplicaciones.getImagenes());
            imagenes.setSliderAdapter(adapter);
            imagenes.setIndicatorAnimation(IndicatorAnimationType.WORM);
            imagenes.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
            imagenes.startAutoCycle();

            titulo.setText(aplicaciones.getTitulo());
            des1.setText(aplicaciones.getDes1());
            if (aplicaciones.getDes2().equals(Vacio)) {
                des2.setVisibility(View.GONE);
            } else {
                des2.setText(aplicaciones.getDes2());
            }
            if (aplicaciones.getDes3().equals(Vacio)) {
                des3.setVisibility(View.GONE);
            } else {
                des3.setText(aplicaciones.getDes3());
            }
            if (aplicaciones.getDes4().equals(Vacio)) {
                des4.setVisibility(View.GONE);
            } else {
                des4.setText(aplicaciones.getDes4());
            }
            if (aplicaciones.getDes5().equals(Vacio)) {
                des5.setVisibility(View.GONE);
            } else {
                des5.setText(aplicaciones.getDes5());
            }

            ir.setOnClickListener(v -> {
                Uri uri = Uri.parse(aplicaciones.getEnlace());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            });

        }

    }
}