package com.pacificblack.ganardinero.ui.aplicaciones;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.pacificblack.ganardinero.R;
import com.pacificblack.ganardinero.actividades.aplicaciones.AplicacionDetalle;
import com.pacificblack.ganardinero.clases.aplicaciones.Aplicaciones;

import java.util.ArrayList;

import static com.pacificblack.ganardinero.constatnes.Constantes.IdAnuncioAplicaciones;
import static com.pacificblack.ganardinero.metodos.Claves.IdClaveAplicaciones;

public class AplicacionesFragment extends Fragment {
    private RecyclerView recyclerView;
    private AplicacionesAdaptador aplicacionesAdaptador;
    private ArrayList<Aplicaciones> listaAplicaciones;
    private InterstitialAd AnuncioAplicacion;


    public AplicacionesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_aplicaciones, container, false);

        AnuncioAplicacion = new InterstitialAd(getActivity());
        AnuncioAplicacion.setAdUnitId(IdAnuncioAplicaciones);
        AnuncioAplicacion.loadAd(new AdRequest.Builder().build());


        recyclerView = root.findViewById(R.id.recycler_aplicaciones);
        recyclerView.setHasFixedSize(true);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        llenar();


        return root;
    }


    private void llenar() {
        listaAplicaciones = new ArrayList<>();
        listaAplicaciones.add(new Aplicaciones("Ensayo", "orem ipsum dolor sit amet, consectetur adipiscing elit. Nunc sagittis tempor efficitur. Cras mollis urna aliquet augue consectetur malesuada. In hac habitasse platea dictumst. Nullam ac nibh porta, maximus est vehicula, iaculis enim. Vivamus in dictum lectus, vel dapibus enim. Morbi id", "Desc2", "Vacio", "Desc2", "Desc2", "Desc2", "Desc2", "Desc2", "Desc2", "Vacio", "Vacio"));
        listaAplicaciones.add(new Aplicaciones("Ensayo", "orem ipsum dolor sit amet, consectetur adipiscing elit. Nunc sagittis tempor efficitur. Cras mollis urna aliquet augue consectetur malesuada. In hac habitasse platea dictumst. Nullam ac nibh porta, maximus est vehicula, iaculis enim. Vivamus in dictum lectus, vel dapibus enim. Morbi id", "Desc2", "Desc2", "Desc2", "Desc2", "Desc2", "Desc2", "Desc2", "Desc2", "Desc2", "Desc2"));


        aplicacionesAdaptador = new AplicacionesAdaptador(listaAplicaciones);
        recyclerView.setAdapter(aplicacionesAdaptador);
        aplicacionesAdaptador.notifyDataSetChanged();

        aplicacionesAdaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (AnuncioAplicacion.isLoaded()) {

                    AnuncioAplicacion.show();

                    AnuncioAplicacion.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            super.onAdClosed();

                            AnuncioAplicacion.loadAd(new AdRequest.Builder().build());

                            Aplicaciones aplicaciones = listaAplicaciones.get(recyclerView.getChildAdapterPosition(v));

                            Intent cambio = new Intent(getContext(), AplicacionDetalle.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(IdClaveAplicaciones, aplicaciones);
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

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.buscadora, menu);
        MenuItem searchItem = menu.findItem(R.id.buscar);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Ingrese la aplicacion que desea buscar");

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                aplicacionesAdaptador.getFilter().filter(newText);
                return false;
            }
        });
    }


}