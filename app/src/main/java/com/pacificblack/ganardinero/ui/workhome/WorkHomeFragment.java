package com.pacificblack.ganardinero.ui.workhome;

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
import com.google.android.gms.ads.AdListener;
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
import com.pacificblack.ganardinero.ui.desarrollo.DesarrolloFragment;
import com.pacificblack.ganardinero.ui.desarrollo.actividad.DesarrolloDetalle;
import com.pacificblack.ganardinero.ui.desarrollo.clase.Desarrollo;
import com.pacificblack.ganardinero.ui.workhome.actividad.WorkHomeDetalle;
import com.pacificblack.ganardinero.ui.workhome.clase.WorkHome;

import java.util.ArrayList;
import java.util.Objects;

import static com.pacificblack.ganardinero.constatnes.Constantes.DBWorkHome;
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
import static com.pacificblack.ganardinero.constatnes.Constantes.IdAnuncioWorkHome;
import static com.pacificblack.ganardinero.constatnes.Constantes.IdAnuncioWorkHome;
import static com.pacificblack.ganardinero.metodos.Claves.IdClaveDesarrollo;
import static com.pacificblack.ganardinero.metodos.Claves.IdClaveWorkHome;

public class WorkHomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private WorkHomeAdaptador workHomeAdaptador;
    private ArrayList<WorkHome> listaWorkHome;
    private FirebaseDatabase bd = FirebaseDatabase.getInstance();
    private DatabaseReference referencebd = bd.getReference().child(DBWorkHome);

    private InterstitialAd AnuncioWorkHome;
    AdRequest adRequest;
    View vc = null;

    public WorkHomeFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_work_home, container, false);

        recyclerView = root.findViewById(R.id.recycler_workhome);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        llenar();
        cargaranuncio();

        return root;

    }

    private void MostrarAnuncio() {
        if (AnuncioWorkHome != null) {
            AnuncioWorkHome.show(getActivity());
        } else {
            cargaranuncio();
            traer();
            Log.i("Anuncio", "Se ejecutó todo pero no salio el anuncio");
            //Aqui se pone todo cuando el anuncio no carga
        }
    }

    private void traer() {
        WorkHome workHome = listaWorkHome.get(recyclerView.getChildAdapterPosition(vc));
        Intent cambio = new Intent(getContext(), WorkHomeDetalle.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(IdClaveWorkHome, workHome);
        cambio.putExtras(bundle);
        startActivity(cambio);
    }

    private void cargaranuncio() {

        adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(getContext(),
                IdAnuncioWorkHome,
                adRequest, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        super.onAdLoaded(interstitialAd);
                        WorkHomeFragment.this.AnuncioWorkHome = interstitialAd;
                        Log.i("Anuncio", "Cargado");

                        AnuncioWorkHome.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                super.onAdFailedToShowFullScreenContent(adError);
                                WorkHomeFragment.this.AnuncioWorkHome = null;
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
                                WorkHomeFragment.this.AnuncioWorkHome = null;
                                Log.i("Anuncio", "Le undio en la equis mi compa");

                                WorkHome workHome = listaWorkHome.get(recyclerView.getChildAdapterPosition(vc));
                                Intent cambio = new Intent(getContext(), WorkHomeDetalle.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable(IdClaveWorkHome, workHome);
                                cambio.putExtras(bundle);
                                startActivity(cambio);
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                        AnuncioWorkHome = null;
                        Log.i("Anuncio", "Fallo al cargar" + loadAdError.toString());

                    }
                });
    }

    private void llenar() {

        referencebd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                listaWorkHome = new ArrayList<>();

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
                        String imagen = dataSnapshot.child(Fimagenes + (i+1)).getValue(String.class);
                        imagenes[i] = imagen;
                    }

                    listaWorkHome.add(new WorkHome(titulo, desc1, desc2, desc3, desc4, desc5, fecha,enlace,cantidad,imagenes));
                }
                workHomeAdaptador = new WorkHomeAdaptador(listaWorkHome);
                recyclerView.setAdapter(workHomeAdaptador);
                workHomeAdaptador.notifyDataSetChanged();

                workHomeAdaptador.setOnClickListener(v -> {
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