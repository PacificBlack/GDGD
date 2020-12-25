package com.pacificblack.ganardinero.ui.creandovideos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.pacificblack.ganardinero.R;
import com.pacificblack.ganardinero.actividades.creandovideos.VideosDetalle;
import com.pacificblack.ganardinero.clases.creandovideos.Videos;

import java.util.ArrayList;

import static com.pacificblack.ganardinero.constatnes.Constantes.IdAnuncioVideos;
import static com.pacificblack.ganardinero.metodos.Claves.IdClaveVideos;

public class VideosFragment extends Fragment {
    private RecyclerView recyclerView;
    private VideosAdaptador VideosAdaptador;
    private ArrayList<Videos> listaVideos;
    private InterstitialAd AnuncioVideos;

    public VideosFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_videos, container, false);

        AnuncioVideos = new InterstitialAd(getActivity());
        AnuncioVideos.setAdUnitId(IdAnuncioVideos);
        AnuncioVideos.loadAd(new AdRequest.Builder().build());

        recyclerView = root.findViewById(R.id.recycler_videos);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        llenar();

        return root;
    }

    private void llenar() {
        listaVideos = new ArrayList<>();
        listaVideos.add(new Videos("Ensayo", "orem ipsum dolor sit amet, consectetur adipiscing elit. Nunc sagittis tempor efficitur. Cras mollis urna aliquet augue consectetur malesuada. In hac habitasse platea dictumst. Nullam ac nibh porta, maximus est vehicula, iaculis enim. Vivamus in dictum lectus, vel dapibus enim. Morbi id", "Desc2", "Vacio", "Desc2", "Desc2", "Desc2", "Desc2", "Desc2", "Desc2", "Vacio", "Vacio"));
        listaVideos.add(new Videos("Ensayo", "orem ipsum dolor sit amet, consectetur adipiscing elit. Nunc sagittis tempor efficitur. Cras mollis urna aliquet augue consectetur malesuada. In hac habitasse platea dictumst. Nullam ac nibh porta, maximus est vehicula, iaculis enim. Vivamus in dictum lectus, vel dapibus enim. Morbi id", "Desc2", "Desc2", "Desc2", "Desc2", "Desc2", "Desc2", "Desc2", "Desc2", "Desc2", "Desc2"));

        VideosAdaptador = new VideosAdaptador(listaVideos);
        recyclerView.setAdapter(VideosAdaptador);
        VideosAdaptador.notifyDataSetChanged();

        VideosAdaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (AnuncioVideos.isLoaded()) {

                    AnuncioVideos.show();

                    AnuncioVideos.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            super.onAdClosed();

                            AnuncioVideos.loadAd(new AdRequest.Builder().build());

                            Videos Videos = listaVideos.get(recyclerView.getChildAdapterPosition(v));

                            Intent cambio = new Intent(getContext(), VideosDetalle.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(IdClaveVideos, Videos);
                            cambio.putExtras(bundle);
                            startActivity(cambio);
                        }
                    });


                } else {
                    Log.d("Anuncio", "No se pudo cargar el anuncio.");
                }


            }
        });

    }
}