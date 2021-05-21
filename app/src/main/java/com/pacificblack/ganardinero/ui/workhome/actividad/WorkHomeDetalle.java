package com.pacificblack.ganardinero.ui.workhome.actividad;

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
import com.pacificblack.ganardinero.ui.workhome.clase.WorkHome;
import com.pacificblack.ganardinero.metodos.Abrir;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import static com.pacificblack.ganardinero.constatnes.Constantes.Imagen;
import static com.pacificblack.ganardinero.constatnes.Constantes.Vacio;
import static com.pacificblack.ganardinero.metodos.Claves.IdClaveWorkHome;

public class WorkHomeDetalle extends AppCompatActivity {
    TextView titulo, des1, des2, des3, des4, des5;
    SliderView imagenes;
    AdView ban1, ban2, ban3, ban4;
    MaterialButton ir;

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
        imagenes = findViewById(R.id.imagenes_detalle_workhome);
        ir = findViewById(R.id.ir_workhome);


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

        WorkHome workhome;

        if (objetoworkhome != null) {

            workhome = (WorkHome) objetoworkhome.getSerializable(IdClaveWorkHome);

            titulo.setText(workhome.getTitulo());
            des1.setText(workhome.getDes1());
            SliderAdapter adapter = new SliderAdapter(getApplicationContext(),workhome.getImagenes());
            imagenes.setSliderAdapter(adapter);
            imagenes.setIndicatorAnimation(IndicatorAnimationType.WORM);
            imagenes.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
            imagenes.startAutoCycle();

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

            ir.setOnClickListener(v -> {
                Uri uri = Uri.parse(workhome.getEnlace());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            });
        }
    }
}