package com.pacificblack.ganardinero.ui.inicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.pacificblack.ganardinero.R;
import com.pacificblack.ganardinero.ui.aplicaciones.AplicacionesFragment;
import com.pacificblack.ganardinero.ui.creandovideos.VideosFragment;
import com.pacificblack.ganardinero.ui.criptomining.MineriaFragment;
import com.pacificblack.ganardinero.ui.desarrollo.DesarrolloFragment;
import com.pacificblack.ganardinero.ui.publicidad.PublicidadFragment;
import com.pacificblack.ganardinero.ui.vendiendo.VenderFragment;
import com.pacificblack.ganardinero.ui.workhome.WorkHomeFragment;

public class InicioFragment extends Fragment {

    ImageView workhome, aplicaciones, criptomonedas, publicidad, desarrollo, video, vendiendo;

    CardView work, app, cripto, publi, desa, vid, vendi;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_inicio, container, false);

        workhome = root.findViewById(R.id.imagen_card_workhome);
        workhome.setImageResource(R.drawable.workhome);
        aplicaciones = root.findViewById(R.id.imagen_card_aplicaciones);
        aplicaciones.setImageResource(R.drawable.aplicaciones);
        criptomonedas = root.findViewById(R.id.imagen_card_criptomonedas);
        criptomonedas.setImageResource(R.drawable.criptomonedas);
        publicidad = root.findViewById(R.id.imagen_card_publicidad);
        publicidad.setImageResource(R.drawable.publicidad);
        desarrollo = root.findViewById(R.id.imagen_card_desarrollo);
        desarrollo.setImageResource(R.drawable.desarrollo);
        video = root.findViewById(R.id.imagen_card_videos);
        video.setImageResource(R.drawable.video);
        vendiendo = root.findViewById(R.id.imagen_card_vendiendo);
        vendiendo.setImageResource(R.drawable.vendiendo);


        work = root.findViewById(R.id.card_freelance);

        work.setOnClickListener(v -> {
            WorkHomeFragment workHomeFragment = new WorkHomeFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, workHomeFragment)
                    .addToBackStack(null)
                    .commit();
        });

        app = root.findViewById(R.id.card_aplicaciones);

        app.setOnClickListener(v -> {
            AplicacionesFragment aplicacionesFragment = new AplicacionesFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, aplicacionesFragment)
                    .addToBackStack(null)
                    .commit();
        });

        cripto = root.findViewById(R.id.card_criptomonedas);

        cripto.setOnClickListener(v -> {
            MineriaFragment mineriaFragment = new MineriaFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, mineriaFragment)
                    .addToBackStack(null)
                    .commit();
        });

        publi = root.findViewById(R.id.card_publicidad);
        publi.setOnClickListener(v -> {
            PublicidadFragment publicidadFragment = new PublicidadFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, publicidadFragment)
                    .addToBackStack(null)
                    .commit();
        });

        desa = root.findViewById(R.id.card_desarrollando);
        desa.setOnClickListener(v -> {
            DesarrolloFragment desarrolloFragment = new DesarrolloFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, desarrolloFragment)
                    .addToBackStack(null)
                    .commit();
        });

        vid = root.findViewById(R.id.card_videos);
        vid.setOnClickListener(v -> {
            VideosFragment videosFragment = new VideosFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, videosFragment)
                    .addToBackStack(null)
                    .commit();
        });

        vendi = root.findViewById(R.id.card_vendiendo);
        vendi.setOnClickListener(v -> {
            VenderFragment venderFragment = new VenderFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, venderFragment)
                    .addToBackStack(null)
                    .commit();
        });


        return root;
    }
}