package com.pacificblack.ganardinero.ui.publicidad;

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

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pacificblack.ganardinero.R;
import com.pacificblack.ganardinero.actividades.publicidad.PublicidadDetalle;
import com.pacificblack.ganardinero.clases.publicidad.Publicidad;

import java.util.ArrayList;

import static com.pacificblack.ganardinero.constatnes.Constantes.DBPublicidad;
import static com.pacificblack.ganardinero.constatnes.Constantes.Fdescripcion1;
import static com.pacificblack.ganardinero.constatnes.Constantes.Fdescripcion2;
import static com.pacificblack.ganardinero.constatnes.Constantes.Fdescripcion3;
import static com.pacificblack.ganardinero.constatnes.Constantes.Fdescripcion4;
import static com.pacificblack.ganardinero.constatnes.Constantes.Fdescripcion5;
import static com.pacificblack.ganardinero.constatnes.Constantes.Ffecha;
import static com.pacificblack.ganardinero.constatnes.Constantes.Fimagen1;
import static com.pacificblack.ganardinero.constatnes.Constantes.Fimagen2;
import static com.pacificblack.ganardinero.constatnes.Constantes.Fimagen3;
import static com.pacificblack.ganardinero.constatnes.Constantes.Fimagen4;
import static com.pacificblack.ganardinero.constatnes.Constantes.Fimagen5;
import static com.pacificblack.ganardinero.constatnes.Constantes.Ftitulo;
import static com.pacificblack.ganardinero.constatnes.Constantes.IdAnuncioPublicidad;
import static com.pacificblack.ganardinero.metodos.Claves.IdClavePublicidad;


public class PublicidadFragment extends Fragment {
    private RecyclerView recyclerView;
    private PublicidadAdaptador publicidadAdaptador;
    private ArrayList<Publicidad> listaPublicidad;
    private InterstitialAd AnuncioPublicidad;
    private FirebaseDatabase bd = FirebaseDatabase.getInstance();
    private DatabaseReference referencebd = bd.getReference().child(DBPublicidad);

    public PublicidadFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_publicidad, container, false);

        AnuncioPublicidad = new InterstitialAd(getActivity());
        AnuncioPublicidad.setAdUnitId(IdAnuncioPublicidad);
        AnuncioPublicidad.loadAd(new AdRequest.Builder().build());


        recyclerView = root.findViewById(R.id.recycler_publicidad);
        recyclerView.setHasFixedSize(true);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        llenar();


        return root;
    }

    private void llenar() {

        referencebd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                listaPublicidad = new ArrayList<>();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    String titulo = dataSnapshot.child(Ftitulo).getValue(String.class);
                    String desc1 = dataSnapshot.child(Fdescripcion1).getValue(String.class);
                    String desc2 = dataSnapshot.child(Fdescripcion2).getValue(String.class);
                    String desc3 = dataSnapshot.child(Fdescripcion3).getValue(String.class);
                    String desc4 = dataSnapshot.child(Fdescripcion4).getValue(String.class);
                    String desc5 = dataSnapshot.child(Fdescripcion5).getValue(String.class);
                    String fecha = dataSnapshot.child(Ffecha).getValue(String.class);
                    String imagen1 = dataSnapshot.child(Fimagen1).getValue(String.class);
                    String imagen2 = dataSnapshot.child(Fimagen2).getValue(String.class);
                    String imagen3 = dataSnapshot.child(Fimagen3).getValue(String.class);
                    String imagen4 = dataSnapshot.child(Fimagen4).getValue(String.class);
                    String imagen5 = dataSnapshot.child(Fimagen5).getValue(String.class);

                    listaPublicidad.add(new Publicidad(titulo, desc1, desc2, desc3, desc4, desc5, fecha, imagen1, imagen2, imagen3, imagen4, imagen5));


                }


                publicidadAdaptador = new PublicidadAdaptador(listaPublicidad);
                recyclerView.setAdapter(publicidadAdaptador);
                publicidadAdaptador.notifyDataSetChanged();

                publicidadAdaptador.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (AnuncioPublicidad.isLoaded()) {

                            AnuncioPublicidad.show();

                            AnuncioPublicidad.setAdListener(new AdListener() {
                                @Override
                                public void onAdClosed() {
                                    super.onAdClosed();

                                    AnuncioPublicidad.loadAd(new AdRequest.Builder().build());

                                    Publicidad Publicidad = listaPublicidad.get(recyclerView.getChildAdapterPosition(v));

                                    Intent cambio = new Intent(getContext(), PublicidadDetalle.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable(IdClavePublicidad, Publicidad);
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
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Error al traer la base de datos" + error, Toast.LENGTH_LONG).show();

            }
        });


    }
}