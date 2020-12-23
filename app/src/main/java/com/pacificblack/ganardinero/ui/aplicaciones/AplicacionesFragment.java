package com.pacificblack.ganardinero.ui.aplicaciones;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pacificblack.ganardinero.R;
import com.pacificblack.ganardinero.clases.aplicaciones.Aplicaciones;

import java.util.ArrayList;

public class AplicacionesFragment extends Fragment {
    private RecyclerView recyclerView;
    private AplicacionesAdaptador aplicacionesAdaptador;
    private ArrayList<Aplicaciones> listaAplicaciones ;


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


        listaAplicaciones = new ArrayList<>();
        listaAplicaciones.add(new Aplicaciones("Ensayo","orem ipsum dolor sit amet, consectetur adipiscing elit. Nunc sagittis tempor efficitur. Cras mollis urna aliquet augue consectetur malesuada. In hac habitasse platea dictumst. Nullam ac nibh porta, maximus est vehicula, iaculis enim. Vivamus in dictum lectus, vel dapibus enim. Morbi id","Desc2","Desc2","Desc2","Desc2","Desc2","Desc2","Desc2","Desc2","Desc2","Desc2"));


        aplicacionesAdaptador = new AplicacionesAdaptador(listaAplicaciones);
        recyclerView.setAdapter(aplicacionesAdaptador);
        aplicacionesAdaptador.notifyDataSetChanged();

        return root;
    }


}