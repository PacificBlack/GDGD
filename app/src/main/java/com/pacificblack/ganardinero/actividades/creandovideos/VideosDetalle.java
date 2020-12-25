package com.pacificblack.ganardinero.actividades.creandovideos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.pacificblack.ganardinero.R;
import com.pacificblack.ganardinero.clases.creandovideos.Videos;
import com.squareup.picasso.Picasso;

import static com.pacificblack.ganardinero.constatnes.Constantes.Vacio;
import static com.pacificblack.ganardinero.metodos.Claves.IdClaveVideos;

public class VideosDetalle extends AppCompatActivity {

    TextView titulo, des1, des2, des3, des4, des5;
    ImageView imagen1, imagen2, imagen3, imagen4, imagen5;
    AdView ban1,ban2,ban3,ban4;

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
        imagen1 = findViewById(R.id.imagen1_detalle_videos);
        imagen2 = findViewById(R.id.imagen2_detalle_videos);
        imagen3 = findViewById(R.id.imagen3_detalle_videos);
        imagen4 = findViewById(R.id.imagen4_detalle_videos);
        imagen5 = findViewById(R.id.imagen5_detalle_videos);

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

        Videos videos = null;

        if (objetovideos != null) {

            videos = (Videos) objetovideos.getSerializable(IdClaveVideos);

            titulo.setText(videos.getTitulo());
            des1.setText(videos.getDes1());
            Picasso.get().load(videos.getImagen1()).placeholder(R.drawable.sample).error(R.drawable.sample).into(imagen1);

            if (videos.getDes2().equals(Vacio)){des2.setVisibility(View.GONE);}else {des2.setText(videos.getDes2());}
            if (videos.getDes3().equals(Vacio)){des3.setVisibility(View.GONE);}else {des3.setText(videos.getDes3());}
            if (videos.getDes4().equals(Vacio)){des4.setVisibility(View.GONE);}else {des4.setText(videos.getDes4());}
            if (videos.getDes5().equals(Vacio)){des5.setVisibility(View.GONE);}else {des5.setText(videos.getDes5());}

            if (videos.getImagen2().equals(Vacio)){imagen2.setVisibility(View.GONE);}else {Picasso.get().load(videos.getImagen2()).placeholder(R.drawable.sample).error(R.drawable.sample).into(imagen2); }
            if (videos.getImagen3().equals(Vacio)){imagen3.setVisibility(View.GONE);}else {Picasso.get().load(videos.getImagen3()).placeholder(R.drawable.sample).error(R.drawable.sample).into(imagen3); }
            if (videos.getImagen4().equals(Vacio)){imagen4.setVisibility(View.GONE);}else {Picasso.get().load(videos.getImagen4()).placeholder(R.drawable.sample).error(R.drawable.sample).into(imagen4); }
            if (videos.getImagen5().equals(Vacio)){imagen5.setVisibility(View.GONE);}else {Picasso.get().load(videos.getImagen5()).placeholder(R.drawable.sample).error(R.drawable.sample).into(imagen5); }

        }

    }
}