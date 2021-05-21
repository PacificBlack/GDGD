package com.pacificblack.ganardinero.Slider;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback;
import com.pacificblack.ganardinero.R;
import com.pacificblack.ganardinero.metodos.Abrir;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import static com.pacificblack.ganardinero.constatnes.Constantes.IdAnuncioBonificado;

public class SliderApaterInicio extends SliderViewAdapter<SliderApaterInicio.SliderVH> implements OnUserEarnedRewardListener {
    private final Context context;
    private final String[] mSliderItems;
    private final String[] enlace;
    AdRequest adRequest;
    RewardedInterstitialAd AnuncioBoni;
    String salida;


    public SliderApaterInicio(Context context, String[] mSliderItems, String[] enlace) {
        this.context = context;
        this.mSliderItems = mSliderItems;
        this.enlace = enlace;
    }

    @Override
    public SliderVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_inicio, null);
        adRequest = new AdRequest.Builder().build();
        cargaranuncio();
        return new SliderVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderVH viewHolder, int position) {
        String url = mSliderItems[position];
        Picasso.get().load(url).error(R.drawable.bg_overlay).into(viewHolder.imageViewBackground);

        viewHolder.itemView.setOnClickListener(v -> {
            Intent intentimagen = new Intent(context, Abrir.class);
            Bundle envioimg = new Bundle();
            envioimg.putString("imagen", url);
            intentimagen.putExtras(envioimg);
            context.startActivity(intentimagen);
        });

        viewHolder.ir.setOnClickListener(v -> {
            if (AnuncioBoni == null) {
                cargaranuncio();
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Para desbloquear el enlace debera ver un anuncio")
                    .setTitle("Desbloquea el enlace")
                    .setPositiveButton("Ver Anuncio", (dialog, which) -> {
                        salida = enlace[position];
                        AnuncioBoni.show((Activity) context, SliderApaterInicio.this);
                    })
                    .setNegativeButton("Cancelar", null);

            AlertDialog dialog = builder.create();
            dialog.show();


        });
    }

    @Override
    public int getCount() {
        return mSliderItems.length;
    }

    public class SliderVH extends ViewHolder {
        View itemView;
        ImageView imageViewBackground;
        Button ir;

        public SliderVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider_inicio);
            ir = itemView.findViewById(R.id.ir_principal_inicio);
            this.itemView = itemView;
        }
    }


    private void cargaranuncio() {
        RewardedInterstitialAd.load(context, IdAnuncioBonificado, adRequest, new RewardedInterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull @NotNull RewardedInterstitialAd rewardedInterstitialAd) {
                super.onAdLoaded(rewardedInterstitialAd);
                AnuncioBoni = rewardedInterstitialAd;
                Log.i("Anuncio", "Cargo el bonificado");

                AnuncioBoni.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull @NotNull AdError adError) {
                        super.onAdFailedToShowFullScreenContent(adError);
                        AnuncioBoni = null;
                        Toast.makeText(context, "Ocurrio un error al mostrar el anuncio, espere un momento e intente de nuevo", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent();
                        Log.i("Anuncio", "Se esta mostrando en la pantalla");
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent();
                        AnuncioBoni = null;
                        Log.i("Anuncio", "Se salio antes");

                    }

                    @Override
                    public void onAdImpression() {
                        super.onAdImpression();
                    }
                });
            }

            @Override
            public void onAdFailedToLoad(@NonNull @NotNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Log.i("Anuncio", "No Cargo el bonificado");
                AnuncioBoni = null;
            }
        });
    }

    @Override
    public void onUserEarnedReward(@NonNull @NotNull RewardItem rewardItem) {
        Log.i("Anuncio", "Ganó su recompensa");
        Uri uri = Uri.parse(salida);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }
}
