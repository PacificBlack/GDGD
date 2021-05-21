package com.pacificblack.ganardinero.ui.vendiendo.actividad;

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
import com.pacificblack.ganardinero.ui.vendiendo.clase.Vender;
import com.pacificblack.ganardinero.metodos.Abrir;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import static com.pacificblack.ganardinero.constatnes.Constantes.Imagen;
import static com.pacificblack.ganardinero.constatnes.Constantes.Vacio;
import static com.pacificblack.ganardinero.metodos.Claves.IdClaveVender;

public class VenderDetalle extends AppCompatActivity {
    TextView titulo, des1, des2, des3, des4, des5;
    SliderView imagenes;
    AdView ban1, ban2, ban3, ban4;
    MaterialButton ir;

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
        imagenes = findViewById(R.id.imagenes_detalle_vender);
        ir = findViewById(R.id.ir_vender);

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

        Vender vender;

        if (objetovender != null) {

            vender = (Vender) objetovender.getSerializable(IdClaveVender);

            titulo.setText(vender.getTitulo());
            des1.setText(vender.getDes1());

            SliderAdapter adapter = new SliderAdapter(getApplicationContext(),vender.getImagenes());
            imagenes.setSliderAdapter(adapter);
            imagenes.setIndicatorAnimation(IndicatorAnimationType.WORM);
            imagenes.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
            imagenes.startAutoCycle();

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

            ir.setOnClickListener(v -> {
                Uri uri = Uri.parse(vender.getEnlace());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            });
        }

    }
}