package com.pacificblack.ganardinero.ui.creandovideos.actividad;

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
import com.pacificblack.ganardinero.ui.creandovideos.clase.Videos;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import static com.pacificblack.ganardinero.constatnes.Constantes.Vacio;
import static com.pacificblack.ganardinero.metodos.Claves.IdClaveVideos;

public class VideosDetalle extends AppCompatActivity {

    TextView titulo, des1, des2, des3, des4, des5;
    SliderView imagenes;
    AdView ban1, ban2, ban3, ban4;
    MaterialButton ir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_videos);

        titulo = findViewById(R.id.titulo_detalle_videos);
        des1 = findViewById(R.id.descripcion1_detalle_videos);
        des2 = findViewById(R.id.descripcion2_detalle_videos);
        des3 = findViewById(R.id.descripcion3_detalle_videos);
        des4 = findViewById(R.id.descripcion4_detalle_videos);
        des5 = findViewById(R.id.descripcion5_detalle_videos);
        imagenes = findViewById(R.id.imagenes_detalle_videos);
        ir = findViewById(R.id.ir_videos);

        AdRequest adRequest = new AdRequest.Builder().build();
        ban1 = findViewById(R.id.banner1_videos);
        ban2 = findViewById(R.id.banner2_videos);
        ban3 = findViewById(R.id.banner3_videos);
        ban4 = findViewById(R.id.banner4_videos);

        ban1.loadAd(adRequest);
        ban2.loadAd(adRequest);
        ban3.loadAd(adRequest);
        ban4.loadAd(adRequest);

        Bundle objetovideos = getIntent().getExtras();

        Videos videos;

        if (objetovideos != null) {

            videos = (Videos) objetovideos.getSerializable(IdClaveVideos);
            SliderAdapter adapter = new SliderAdapter(getApplicationContext(), videos.getImagenes());
            imagenes.setSliderAdapter(adapter);
            imagenes.setIndicatorAnimation(IndicatorAnimationType.WORM);
            imagenes.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
            imagenes.startAutoCycle();

            titulo.setText(videos.getTitulo());
            des1.setText(videos.getDes1());
            if (videos.getDes2().equals(Vacio)) {
                des2.setVisibility(View.GONE);
            } else {
                des2.setText(videos.getDes2());
            }
            if (videos.getDes3().equals(Vacio)) {
                des3.setVisibility(View.GONE);
            } else {
                des3.setText(videos.getDes3());
            }
            if (videos.getDes4().equals(Vacio)) {
                des4.setVisibility(View.GONE);
            } else {
                des4.setText(videos.getDes4());
            }
            if (videos.getDes5().equals(Vacio)) {
                des5.setVisibility(View.GONE);
            } else {
                des5.setText(videos.getDes5());
            }
            ir.setOnClickListener(v -> {
                Uri uri = Uri.parse(videos.getEnlace());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            });
        }

    }
}