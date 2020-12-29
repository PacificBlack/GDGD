package com.pacificblack.ganardinero.ui.creandovideos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pacificblack.ganardinero.R;
import com.pacificblack.ganardinero.clases.creandovideos.Videos;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class VideosAdaptador extends RecyclerView.Adapter<VideosAdaptador.VideosHolder> implements View.OnClickListener, Filterable {
    private List<Videos> listaVideos;
    private List<Videos> listaVideosFull;
    public Filter listaVideosFiltro = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Videos> filtroListaVideo = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filtroListaVideo.addAll(listaVideosFull);

            } else {
                String filtroparametro = constraint.toString().toLowerCase().trim();
                for (Videos itemVideo : listaVideosFull) {
                    if (itemVideo.getTitulo().toLowerCase().contains(filtroparametro) || itemVideo.getDes1().toLowerCase().contains(filtroparametro)) {
                        filtroListaVideo.add(itemVideo);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filtroListaVideo;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listaVideos.clear();
            listaVideos.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
    private View.OnClickListener listener;

    public VideosAdaptador(List<Videos> listaVideos) {
        this.listaVideos = listaVideos;
        listaVideosFull = new ArrayList<>(listaVideos);
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public VideosAdaptador.VideosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_videos, null, false);
        view.setOnClickListener(this);
        return new VideosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideosAdaptador.VideosHolder holder, int position) {
        if (listaVideos.get(position).getImagen1() != null) {

            Picasso.get().load(listaVideos.get(position).getImagen1())
                    .placeholder(R.drawable.sample)
                    .error(R.drawable.sample)
                    .into(holder.imagen);
        } else {
            holder.imagen.setImageResource(R.drawable.sample);
        }

        holder.titulo.setText(listaVideos.get(position).getTitulo());
        holder.descripcion.setText(listaVideos.get(position).getDes1());
        holder.fecha.setText(listaVideos.get(position).getFecha());
    }

    @Override
    public int getItemCount() {
        return listaVideos.size();
    }

    @Override
    public Filter getFilter() {
        return listaVideosFiltro;
    }

    public class VideosHolder extends RecyclerView.ViewHolder {
        TextView titulo, descripcion, fecha;
        ImageView imagen;

        public VideosHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.titulo_row_videos);
            descripcion = itemView.findViewById(R.id.descripcion_row_videos);
            fecha = itemView.findViewById(R.id.fecha_row_videos);
            imagen = itemView.findViewById(R.id.imagen_row_videos);
        }
    }

}
