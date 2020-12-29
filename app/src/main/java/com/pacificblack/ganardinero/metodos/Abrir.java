package com.pacificblack.ganardinero.metodos;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.pacificblack.ganardinero.R;
import com.squareup.picasso.Picasso;

import uk.co.senab.photoview.PhotoViewAttacher;

import static com.pacificblack.ganardinero.constatnes.Constantes.Imagen;

public class Abrir extends AppCompatActivity {
    ImageView imagengrande;
    PhotoViewAttacher mAtacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abrir);

        imagengrande = findViewById(R.id.imagengrande);


        Bundle recibido = getIntent().getExtras();


        if (recibido != null) {
            String valor = getIntent().getExtras().getString(Imagen);
            Picasso.get().load(valor)
                    .placeholder(R.drawable.sample)
                    .error(R.drawable.sample)
                    .into(imagengrande);

            mAtacher = new PhotoViewAttacher(imagengrande);
        }
    }
}