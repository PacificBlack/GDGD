package com.pacificblack.ganardinero.ui.aplicaciones;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pacificblack.ganardinero.R;
import com.pacificblack.ganardinero.ui.aplicaciones.actividad.AplicacionDetalle;
import com.pacificblack.ganardinero.ui.aplicaciones.adaptador.AplicacionesAdaptador;
import com.pacificblack.ganardinero.ui.aplicaciones.clases.Aplicaciones;

import java.util.ArrayList;

import static com.pacificblack.ganardinero.constatnes.Constantes.DBAplicaciones;
import static com.pacificblack.ganardinero.constatnes.Constantes.Fcantidad;
import static com.pacificblack.ganardinero.constatnes.Constantes.Fdescripcion1;
import static com.pacificblack.ganardinero.constatnes.Constantes.Fdescripcion2;
import static com.pacificblack.ganardinero.constatnes.Constantes.Fdescripcion3;
import static com.pacificblack.ganardinero.constatnes.Constantes.Fdescripcion4;
import static com.pacificblack.ganardinero.constatnes.Constantes.Fdescripcion5;
import static com.pacificblack.ganardinero.constatnes.Constantes.Fenlace;
import static com.pacificblack.ganardinero.constatnes.Constantes.Ffecha;
import static com.pacificblack.ganardinero.constatnes.Constantes.Fimagenes;
import static com.pacificblack.ganardinero.constatnes.Constantes.Ftitulo;
import static com.pacificblack.ganardinero.constatnes.Constantes.IdAnuncioAplicaciones;
import static com.pacificblack.ganardinero.metodos.Claves.IdClaveAplicaciones;


public class AplicacionesFragment extends Fragment {
    private RecyclerView recyclerView;
    private AplicacionesAdaptador aplicacionesAdaptador;
    private ArrayList<Aplicaciones> listaAplicaciones;
    private final FirebaseDatabase bd = FirebaseDatabase.getInstance();
    private final DatabaseReference referencebd = bd.getReference().child(DBAplicaciones);

    private InterstitialAd AnuncioAplicacion;
    AdRequest adRequest;
    View vc = null;

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

        recyclerView = root.findViewById(R.id.recycler_aplicaciones);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        llenar();
        cargaranuncio();

        return root;
    }

    private void MostrarAnuncio() {
        if (AnuncioAplicacion != null) {
            AnuncioAplicacion.show(getActivity());
        } else {
            cargaranuncio();
            traer();
            Log.i("Anuncio", "Se ejecutó todo pero no salio el anuncio");
            //Aqui se pone todo cuando el anuncio no carga
        }
    }

    private void traer() {
        Aplicaciones aplicaciones = listaAplicaciones.get(recyclerView.getChildAdapterPosition(vc));
        Intent cambio = new Intent(getContext(), AplicacionDetalle.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(IdClaveAplicaciones, aplicaciones);
        cambio.putExtras(bundle);
        startActivity(cambio);
    }

    private void cargaranuncio() {

        adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(getContext(),
                IdAnuncioAplicaciones,
                adRequest, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        super.onAdLoaded(interstitialAd);
                        AplicacionesFragment.this.AnuncioAplicacion = interstitialAd;
                        Log.i("Anuncio", "Cargado");

                        AnuncioAplicacion.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                super.onAdFailedToShowFullScreenContent(adError);
                                AplicacionesFragment.this.AnuncioAplicacion = null;
                                Log.i("Anuncio", "Fallo al mostrar" + adError.toString());

                                //Cuando el anuncio no carga
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                super.onAdShowedFullScreenContent();
                                Log.i("Anuncio", "Se esta mostrando en la pantalla");


                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                super.onAdDismissedFullScreenContent();
                                AplicacionesFragment.this.AnuncioAplicacion = null;
                                Log.i("Anuncio", "Le undio en la equis mi compa");

                                Aplicaciones aplicaciones = listaAplicaciones.get(recyclerView.getChildAdapterPosition(vc));
                                Intent cambio = new Intent(getContext(), AplicacionDetalle.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable(IdClaveAplicaciones, aplicaciones);
                                cambio.putExtras(bundle);
                                startActivity(cambio);

                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                        AnuncioAplicacion = null;
                        Log.i("Anuncio", "Fallo al cargar" + loadAdError.toString());

                    }
                });
    }

    private void llenar() {
        referencebd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                listaAplicaciones = new ArrayList<>();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    String titulo = dataSnapshot.child(Ftitulo).getValue(String.class);
                    String desc1 = dataSnapshot.child(Fdescripcion1).getValue(String.class);
                    String desc2 = dataSnapshot.child(Fdescripcion2).getValue(String.class);
                    String desc3 = dataSnapshot.child(Fdescripcion3).getValue(String.class);
                    String desc4 = dataSnapshot.child(Fdescripcion4).getValue(String.class);
                    String desc5 = dataSnapshot.child(Fdescripcion5).getValue(String.class);
                    String fecha = dataSnapshot.child(Ffecha).getValue(String.class);
                    String enlace = dataSnapshot.child(Fenlace).getValue(String.class);
                    int cantidad = Integer.parseInt(dataSnapshot.child(Fcantidad).getValue(String.class));
                    String[] imagenes = new String[cantidad];

                    for (int i = 0; i < cantidad; i++) {
                        String imagen = dataSnapshot.child(Fimagenes + (i + 1)).getValue(String.class);
                        imagenes[i] = imagen;
                    }
                    listaAplicaciones.add(new Aplicaciones(titulo, desc1, desc2, desc3, desc4, desc5, fecha, enlace, cantidad, imagenes));
                }

                aplicacionesAdaptador = new AplicacionesAdaptador(listaAplicaciones);
                recyclerView.setAdapter(aplicacionesAdaptador);
                aplicacionesAdaptador.notifyDataSetChanged();

                aplicacionesAdaptador.setOnClickListener(v -> {
                    vc = v;
                    MostrarAnuncio();
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Error al traer la base de datos" + error, Toast.LENGTH_LONG).show();
            }
        });
    }
}