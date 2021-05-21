package com.pacificblack.ganardinero.Slider;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.pacificblack.ganardinero.R;
import com.pacificblack.ganardinero.metodos.Abrir;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH>  {
    private final Context context;
    private final String[] mSliderItems;

    public SliderAdapter(Context context, String[] mSliderItems) {
        this.context = context;
        this.mSliderItems = mSliderItems;
    }


    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }


    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {

        String url = mSliderItems[position];
        Picasso.get().load(url).error(R.drawable.bg_overlay).into(viewHolder.imageViewBackground);

        viewHolder.itemView.setOnClickListener(v -> {
            Intent intentimagen = new Intent(viewHolder.itemView.getContext(), Abrir.class);
            Bundle envioimg = new Bundle();
            envioimg.putString("imagen", url);
            intentimagen.putExtras(envioimg);
            viewHolder.itemView.getContext().startActivity(intentimagen);
        });

    }

    @Override
    public int getCount() {
        return mSliderItems.length;
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            this.itemView = itemView;
        }
    }
}
