package com.pacificblack.ganardinero.ui.publicidad;

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
import com.pacificblack.ganardinero.clases.publicidad.Publicidad;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PublicidadAdaptador extends RecyclerView.Adapter<PublicidadAdaptador.PublicidadHolder> implements View.OnClickListener, Filterable {
    private List<Publicidad> listaPublicidad;
    private List<Publicidad> listaPublicidadFull;
    public Filter listaPublicidadFiltro = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Publicidad> filtroListaPublicidad = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filtroListaPublicidad.addAll(listaPublicidadFull);

            } else {
                String filtroparametro = constraint.toString().toLowerCase().trim();
                for (Publicidad itemPublicidad : listaPublicidadFull) {
                    if (itemPublicidad.getTitulo().toLowerCase().contains(filtroparametro) || itemPublicidad.getDes1().toLowerCase().contains(filtroparametro)) {
                        filtroListaPublicidad.add(itemPublicidad);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filtroListaPublicidad;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listaPublicidad.clear();
            listaPublicidad.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
    private View.OnClickListener listener;

    public PublicidadAdaptador(List<Publicidad> listaPublicidad) {
        this.listaPublicidad = listaPublicidad;
        listaPublicidadFull = new ArrayList<>(listaPublicidad);
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

    @Override
    public Filter getFilter() {
        return listaPublicidadFiltro;
    }

    @NonNull
    @Override
    public PublicidadAdaptador.PublicidadHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_publicidad, null, false);
        view.setOnClickListener(this);
        return new PublicidadHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PublicidadAdaptador.PublicidadHolder holder, int position) {
        if (listaPublicidad.get(position).getImagen1() != null) {

            Picasso.get().load(listaPublicidad.get(position).getImagen1())
                    .placeholder(R.drawable.sample)
                    .error(R.drawable.sample)
                    .into(holder.imagen);
        } else {
            holder.imagen.setImageResource(R.drawable.sample);
        }

        holder.titulo.setText(listaPublicidad.get(position).getTitulo());
        holder.descripcion.setText(listaPublicidad.get(position).getDes1());
        holder.fecha.setText(listaPublicidad.get(position).getFecha());

    }

    @Override
    public int getItemCount() {
        return listaPublicidad.size();
    }

    public class PublicidadHolder extends RecyclerView.ViewHolder {
        TextView titulo, descripcion, fecha;
        ImageView imagen;

        public PublicidadHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.titulo_row_publicidad);
            descripcion = itemView.findViewById(R.id.descripcion_row_publicidad);
            fecha = itemView.findViewById(R.id.fecha_row_publicidad);
            imagen = itemView.findViewById(R.id.imagen_row_publicidad);
        }
    }
}
