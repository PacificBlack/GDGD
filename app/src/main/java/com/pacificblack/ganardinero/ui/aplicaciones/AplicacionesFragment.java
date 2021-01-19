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

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pacificblack.ganardinero.R;
import com.pacificblack.ganardinero.actividades.aplicaciones.AplicacionDetalle;
import com.pacificblack.ganardinero.clases.aplicaciones.Aplicaciones;

import java.security.acl.Permission;
import java.util.ArrayList;

import static android.Manifest.permission.INTERNET;
import static com.pacificblack.ganardinero.constatnes.Constantes.DBAplicaciones;
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
import static com.pacificblack.ganardinero.constatnes.Constantes.IdAnuncioAplicaciones;
import static com.pacificblack.ganardinero.metodos.Claves.IdClaveAplicaciones;


public class AplicacionesFragment extends Fragment {
    private RecyclerView recyclerView;
    private AplicacionesAdaptador aplicacionesAdaptador;
    private ArrayList<Aplicaciones> listaAplicaciones;
    private InterstitialAd AnuncioAplicacion;
    private FirebaseDatabase bd = FirebaseDatabase.getInstance();
    private DatabaseReference referencebd = bd.getReference().child(DBAplicaciones);

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
                    String imagen1 = dataSnapshot.child(Fimagen1).getValue(String.class);
                    String imagen2 = dataSnapshot.child(Fimagen2).getValue(String.class);
                    String imagen3 = dataSnapshot.child(Fimagen3).getValue(String.class);
                    String imagen4 = dataSnapshot.child(Fimagen4).getValue(String.class);
                    String imagen5 = dataSnapshot.child(Fimagen5).getValue(String.class);

                    listaAplicaciones.add(new Aplicaciones(titulo, desc1, desc2, desc3, desc4, desc5, fecha, imagen1, imagen2, imagen3, imagen4, imagen5));


                }


                aplicacionesAdaptador = new AplicacionesAdaptador(listaAplicaciones);
                recyclerView.setAdapter(aplicacionesAdaptador);
                aplicacionesAdaptador.notifyDataSetChanged();

                aplicacionesAdaptador.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (AnuncioAplicacion.isLoaded()) {

                            final int tiempo = 3;
                            Toast.makeText(getContext(),"Espera un momento mientras se carga la guía",tiempo).show();
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


                        } else if (!AnuncioAplicacion.isLoaded()){


                            Aplicaciones aplicaciones = listaAplicaciones.get(recyclerView.getChildAdapterPosition(v));

                            Intent cambio = new Intent(getContext(), AplicacionDetalle.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(IdClaveAplicaciones, aplicaciones);
                            cambio.putExtras(bundle);
                            startActivity(cambio);

                        }else {
                            Toast.makeText(getContext(), "Espera un momento a que se carguen los archivos y presiona de nuevo", Toast.LENGTH_LONG).show();


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