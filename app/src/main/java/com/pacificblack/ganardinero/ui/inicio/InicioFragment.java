package com.pacificblack.ganardinero.ui.inicio;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pacificblack.ganardinero.R;
import com.pacificblack.ganardinero.Slider.SliderApaterInicio;
import com.pacificblack.ganardinero.ui.aplicaciones.AplicacionesFragment;
import com.pacificblack.ganardinero.ui.creandovideos.VideosFragment;
import com.pacificblack.ganardinero.ui.criptomining.MineriaFragment;
import com.pacificblack.ganardinero.ui.desarrollo.DesarrolloFragment;
import com.pacificblack.ganardinero.ui.publicidad.PublicidadFragment;
import com.pacificblack.ganardinero.ui.vendiendo.VenderFragment;
import com.pacificblack.ganardinero.ui.workhome.WorkHomeFragment;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.pacificblack.ganardinero.constatnes.Constantes.IdAnuncioBonificado;

public class InicioFragment extends Fragment  {

    CardView work, app, cripto, publi, desa, vid, vendi;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    SliderView sliderView;

    private RewardedInterstitialAd AnuncioBoni;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_inicio, container, false);
        work = root.findViewById(R.id.card_freelance);
        app = root.findViewById(R.id.card_aplicaciones);
        cripto = root.findViewById(R.id.card_criptomonedas);
        publi = root.findViewById(R.id.card_publicidad);
        desa = root.findViewById(R.id.card_desarrollando);
        vid = root.findViewById(R.id.card_videos);
        vendi = root.findViewById(R.id.card_vendiendo);
        sliderView = root.findViewById(R.id.imagenes_inicio);


        work.setOnClickListener(v -> workhome());
        app.setOnClickListener(v -> aplicaciones());
        cripto.setOnClickListener(v -> mineria());
        publi.setOnClickListener(v -> publicidad());
        desa.setOnClickListener(v -> desarrollo());
        vid.setOnClickListener(v -> videos());
        vendi.setOnClickListener(v -> vender());

        slider();

        return root;
    }





    private void slider(){
        DatabaseReference referencedb_imagenes = db.getReference().child("inicio");
        List<String> imagenes = new ArrayList<>();
        List<String> enlaces = new ArrayList<>();

        referencedb_imagenes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String imagenurl = dataSnapshot.child("imagen").getValue(String.class);
                    String enlace = dataSnapshot.child("enlace").getValue(String.class);
                    imagenes.add(imagenurl);
                    enlaces.add(enlace);
                }

                String[] car = new String[imagenes.size()];
                for (int i = 0; i < imagenes.size(); i++) {
                    car[i] = imagenes.get(i);
                }

                String[] en = new String[enlaces.size()];
                for (int i = 0; i < enlaces.size(); i++) {
                    en[i] = enlaces.get(i);
                }

                SliderApaterInicio adapter = new SliderApaterInicio(getActivity(), car, en);
                sliderView.setSliderAdapter(adapter);

                sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
                sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                sliderView.startAutoCycle();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Error al traer la base de datos" + error, Toast.LENGTH_LONG).show();
            }
        });

    }
    private void workhome() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        WorkHomeFragment workHomeFragment = new WorkHomeFragment();
        assert getFragmentManager() != null;
        transaction.replace(R.id.nav_host_fragment, workHomeFragment).addToBackStack(null).commit();
    }

    private void aplicaciones() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        AplicacionesFragment aplicacionesFragment = new AplicacionesFragment();
        assert getFragmentManager() != null;
        transaction.replace(R.id.nav_host_fragment, aplicacionesFragment).addToBackStack(null).commit();
    }

    private void mineria() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        MineriaFragment mineriaFragment = new MineriaFragment();
        assert getFragmentManager() != null;
        transaction.replace(R.id.nav_host_fragment, mineriaFragment).addToBackStack(null).commit();
    }

    private void vender() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        VenderFragment venderFragment = new VenderFragment();
        assert getFragmentManager() != null;
        transaction.replace(R.id.nav_host_fragment, venderFragment).addToBackStack(null).commit();
    }

    private void videos() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        VideosFragment videosFragment = new VideosFragment();
        assert getFragmentManager() != null;
        transaction.replace(R.id.nav_host_fragment, videosFragment).addToBackStack(null).commit();
    }

    private void desarrollo() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        DesarrolloFragment desarrolloFragment = new DesarrolloFragment();
        assert getFragmentManager() != null;
        transaction.replace(R.id.nav_host_fragment, desarrolloFragment).addToBackStack(null).commit();
    }

    private void publicidad() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        PublicidadFragment publicidadFragment = new PublicidadFragment();
        assert getFragmentManager() != null;
        transaction.replace(R.id.nav_host_fragment, publicidadFragment).addToBackStack(null).commit();
    }


}